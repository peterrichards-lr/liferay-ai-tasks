package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.list.type.service.ListTypeDefinitionService;
import com.liferay.list.type.service.ListTypeEntryService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Fabian Bouché
 * @author Petteri Karttunen
 */
@Component(property = "ai.task.tool.name=picklist", service = AITaskTool.class)
public class PickListAITaskTool implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject configurationJSONObject) {
		return new Executor();
	}

	public class Executor {

		@Tool(
			"Use this tool only if you know the Picklist's ID. Adds an entry a Liferay Picklist. Returns the Picklist Entry Id or -1 if it fails to create the Picklist entry."
		)
		public long addPicklistEntryToPicklistDefinition(
				@P(
					"The Picklist ID of the Picklist, use the getPicklists tool if you don't know it"
				)
				long picklistId,
				@P(
					"The value of the Picklist entry to be created, it uses only letters, camelCase"
				)
				String picklistEntryName,
				@P(
					"The label of the Picklist entry to be created, human readable"
				)
				String picklistEntryLabel,
				@P(
					"The External Reference Code of the Picklist entry to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode)
			throws PortalException {

			try {
				ListTypeEntry listTypeEntry =
					listTypeEntryService.addListTypeEntry(
						externalReferenceCode, picklistId, picklistEntryName,
						LocalizedMapUtil.getLocalizedMap(picklistEntryLabel));

				return listTypeEntry.getListTypeEntryId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create picklist entry", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Creates an empty Liferay Picklist (hint: use this tool before starting to add entries to the Picklist). Returns the Picklist Id associated to the newly created Picklist. Returns -1 if it failed to create the object definition."
		)
		public long createPicklist(
				@P(
					"The External Reference Code of the Picklist to create, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P(
					"The name of the Picklist to be created, it uses only letters, PascalCase"
				)
				String name)
			throws PortalException {

			try {
				ListTypeDefinition listTypeDefinition =
					listTypeDefinitionService.addListTypeDefinition(
						externalReferenceCode,
						LocalizedMapUtil.getLocalizedMap(name), false,
						Collections.emptyList());

				return listTypeDefinition.getListTypeDefinitionId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create pick list", portalException);

				throw portalException;
			}
		}

		@Tool("Returns the list of Liferay Picklists (Picklist Id: Label)")
		public List<String> getPicklists() {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			return ListTypeDefinitionLocalServiceUtil.getListTypeDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS
			).stream(
			).filter(
				listTypeDefinition -> !listTypeDefinition.isSystem()
			).map(
				listTypeDefinition ->
					listTypeDefinition.getListTypeDefinitionId() + ": " +
						listTypeDefinition.getName(serviceContext.getLocale())
			).collect(
				Collectors.toList()
			);
		}

	}

	protected static final Log log = LogFactoryUtil.getLog(
		PickListAITaskTool.class);

	@Reference
	protected ListTypeDefinitionService listTypeDefinitionService;

	@Reference
	protected ListTypeEntryService listTypeEntryService;

}