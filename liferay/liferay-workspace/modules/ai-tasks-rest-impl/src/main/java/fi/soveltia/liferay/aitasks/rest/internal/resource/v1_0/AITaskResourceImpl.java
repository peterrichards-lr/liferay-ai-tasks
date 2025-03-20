package fi.soveltia.liferay.aitasks.rest.internal.resource.v1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import fi.soveltia.liferay.aitasks.constants.AITaskActionKeys;
import fi.soveltia.liferay.aitasks.constants.AITaskConstants;
import fi.soveltia.liferay.aitasks.exception.DuplicateAITaskExternalReferenceCodeException;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITask;
import fi.soveltia.liferay.aitasks.rest.internal.odata.entity.v1_0.AITaskEntityModel;
import fi.soveltia.liferay.aitasks.rest.internal.util.SearchUtil;
import fi.soveltia.liferay.aitasks.rest.internal.util.TitleMapUtil;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResource;
import fi.soveltia.liferay.aitasks.service.AITaskService;
import fi.soveltia.liferay.aitasks.task.chat.memory.ChatMemoryManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Petteri Karttunen
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/ai-task.properties",
	scope = ServiceScope.PROTOTYPE, service = AITaskResource.class
)
public class AITaskResourceImpl extends BaseAITaskResourceImpl {

	@Override
	public void deleteAITask(Long aiTaskId) throws Exception {
		_aiTaskService.deleteAITask(aiTaskId);
	}

	@Override
	public AITask getAITask(Long aiTaskId) throws Exception {
		return _aiTaskDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				_dtoConverterRegistry, contextHttpServletRequest, aiTaskId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			_aiTaskService.getAITask(aiTaskId));
	}

	@Override
	public AITask getAITaskByExternalReferenceCode(String externalReferenceCode)
		throws Exception {

		fi.soveltia.liferay.aitasks.model.AITask aiTask =
			_aiTaskService.getAITaskByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		return _aiTaskDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				_dtoConverterRegistry, contextHttpServletRequest,
				aiTask.getAITaskId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			aiTask);
	}

	@Override
	public Response getAITaskExport(Long aiTaskId) throws Exception {
		fi.soveltia.liferay.aitasks.model.AITask aiTask =
			_aiTaskService.getAITask(aiTaskId);

		return Response.ok(
		).encoding(
			"UTF-8"
		).entity(
			JSONUtil.put(
				"configuration",
				_jsonFactory.createJSONObject(aiTask.getConfigurationJSON())
			).put(
				"description_i18n",
				_jsonFactory.createJSONObject(
					_jsonFactory.looseSerialize(aiTask.getDescriptionMap()))
			).put(
				"enabled", aiTask.getEnabled()
			).put(
				"externalReferenceCode", aiTask.getExternalReferenceCode()
			).put(
				"schemaVersion", aiTask.getSchemaVersion()
			).put(
				"title_i18n",
				_jsonFactory.createJSONObject(
					_jsonFactory.looseSerialize(aiTask.getTitleMap()))
			)
		).header(
			"Content-Disposition",
			StringBundler.concat(
				"attachment; filename=\"",
				aiTask.getTitle(
					contextAcceptLanguage.getPreferredLocale(), true),
				".json\"")
		).build();
	}

	@Override
	public Page<AITask> getAITasksPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		if (sorts == null) {
			sorts = new Sort[] {
				new Sort("modified_sortable", Sort.LONG_TYPE, true)
			};
		}

		return SearchUtil.search(
			Collections.emptyMap(),
			booleanQuery -> SearchUtil.processAITaskSearchBooleanQuery(
				contextAcceptLanguage, booleanQuery, search),
			filter, fi.soveltia.liferay.aitasks.model.AITask.class.getName(),
			search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			searchContext -> {
				searchContext.setCompanyId(contextCompany.getCompanyId());

				if (!Validator.isBlank(search)) {
					searchContext.setKeywords("");
				}
			},
			sorts,
			document -> {
				long aiTaskId = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));

				AITask aiTask = _aiTaskDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						contextAcceptLanguage.isAcceptAllLanguages(),
						new HashMap<>(), _dtoConverterRegistry,
						contextHttpServletRequest,
						document.get(Field.ENTRY_CLASS_PK),
						contextAcceptLanguage.getPreferredLocale(),
						contextUriInfo, contextUser),
					_aiTaskService.getAITask(aiTaskId));

				String permissionName =
					fi.soveltia.liferay.aitasks.model.AITask.class.getName();

				aiTask.setActions(
					() -> HashMapBuilder.put(
						"create",
						() -> addAction(
							AITaskActionKeys.ADD_AI_TASK, "postAITask",
							AITaskConstants.RESOURCE_NAME,
							contextCompany.getCompanyId())
					).put(
						"delete",
						() -> {
							if (aiTask.getReadOnly()) {
								return null;
							}

							return addAction(
								ActionKeys.DELETE, "deleteAITask",
								permissionName, aiTaskId);
						}
					).put(
						"get",
						() -> addAction(
							ActionKeys.VIEW, "getAITask", permissionName,
							aiTaskId)
					).put(
						"update",
						() -> {
							if (aiTask.getReadOnly()) {
								return null;
							}

							return addAction(
								ActionKeys.UPDATE, "putAITask", permissionName,
								aiTaskId);
						}
					).build());

				return aiTask;
			});
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap) {
		return _aiTaskEntityModel;
	}

	@Override
	public AITask postAITask(AITask aiTask) throws Exception {
		return _aiTaskDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				_dtoConverterRegistry, contextHttpServletRequest,
				aiTask.getId(), contextAcceptLanguage.getPreferredLocale(),
				contextUriInfo, contextUser),
			_aiTaskService.addAITask(
				_getConfigurationJSON(aiTask),
				LocalizedMapUtil.getLocalizedMap(
					contextAcceptLanguage.getPreferredLocale(),
					aiTask.getDescription(), aiTask.getDescription_i18n()),
				aiTask.getEnabled(), aiTask.getExternalReferenceCode(), false,
				_getSchemaVersion(),
				ServiceContextFactory.getInstance(contextHttpServletRequest),
				LocalizedMapUtil.getLocalizedMap(
					contextAcceptLanguage.getPreferredLocale(),
					aiTask.getTitle(), aiTask.getTitle_i18n())));
	}

	@Override
	public void postAITaskByExternalReferenceCodeClear(
		String externalReferenceCode, String nodeId) {

		_chatMemoryManager.clearChatMemory(
			contextCompany.getCompanyId(), externalReferenceCode, nodeId,
			contextUser.getUserId());
	}

	@Override
	public AITask postAITaskCopy(Long aiTaskId) throws Exception {
		fi.soveltia.liferay.aitasks.model.AITask aiTask =
			_aiTaskService.getAITask(aiTaskId);

		return _aiTaskDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				_dtoConverterRegistry, contextHttpServletRequest,
				aiTask.getAITaskId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser),
			_aiTaskService.addAITask(
				aiTask.getConfigurationJSON(), aiTask.getDescriptionMap(),
				aiTask.getEnabled(), null, false, aiTask.getSchemaVersion(),
				ServiceContextFactory.getInstance(contextHttpServletRequest),
				TitleMapUtil.copy(aiTask.getTitleMap())));
	}

	@Override
	public AITask postAITaskValidate(String json) throws Exception {
		AITask aiTask = AITask.unsafeToDTO(json);

		_validateAITaskExternalReferenceCode(aiTask);

		return aiTask;
	}

	@Override
	public AITask putAITask(Long aiTaskId, AITask aiTask) throws Exception {
		fi.soveltia.liferay.aitasks.model.AITask serviceBuilderAITask =
			_aiTaskService.fetchAITask(aiTaskId);

		if (serviceBuilderAITask == null) {
			return postAITask(aiTask);
		}

		if (!serviceBuilderAITask.isReadOnly()) {
			return _updateAITask(aiTaskId, aiTask);
		}

		return getAITask(serviceBuilderAITask.getAITaskId());
	}

	@Override
	public AITask putAITaskByExternalReferenceCode(
			String externalReferenceCode, AITask aiTask)
		throws Exception {

		fi.soveltia.liferay.aitasks.model.AITask serviceBuilderAITask =
			_aiTaskService.fetchAITaskByExternalReferenceCode(
				externalReferenceCode, contextCompany.getCompanyId());

		aiTask.setExternalReferenceCode(() -> externalReferenceCode);

		if (serviceBuilderAITask != null) {
			return _updateAITask(serviceBuilderAITask.getAITaskId(), aiTask);
		}

		return postAITask(aiTask);
	}

	private String _getConfigurationJSON(AITask aiTask) {
		if (aiTask.getConfiguration() == null) {
			return null;
		}

		return String.valueOf(aiTask.getConfiguration());
	}

	private String _getSchemaVersion() {
		return "1.0";
	}

	private AITask _updateAITask(Long aiTaskId, AITask aiTask)
		throws Exception {

		return _aiTaskDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(), new HashMap<>(),
				_dtoConverterRegistry, contextHttpServletRequest,
				aiTask.getId(), contextAcceptLanguage.getPreferredLocale(),
				contextUriInfo, contextUser),
			_aiTaskService.updateAITask(
				_getConfigurationJSON(aiTask),
				LocalizedMapUtil.getLocalizedMap(
					contextAcceptLanguage.getPreferredLocale(),
					aiTask.getDescription(), aiTask.getDescription_i18n()),
				aiTask.getEnabled(), aiTask.getExternalReferenceCode(),
				aiTaskId, _getSchemaVersion(),
				ServiceContextFactory.getInstance(contextHttpServletRequest),
				LocalizedMapUtil.getLocalizedMap(
					contextAcceptLanguage.getPreferredLocale(),
					aiTask.getTitle(), aiTask.getTitle_i18n())));
	}

	private void _validateAITaskExternalReferenceCode(AITask aiTask)
		throws Exception {

		if (Validator.isBlank(aiTask.getExternalReferenceCode())) {
			return;
		}

		fi.soveltia.liferay.aitasks.model.AITask serviceBuilderAITask =
			_aiTaskService.fetchAITaskByExternalReferenceCode(
				aiTask.getExternalReferenceCode(),
				contextCompany.getCompanyId());

		if ((serviceBuilderAITask != null) &&
			!Objects.equals(
				serviceBuilderAITask.getAITaskId(), aiTask.getId())) {

			throw new DuplicateAITaskExternalReferenceCodeException();
		}
	}

	@Reference(
		target = "(component.name=fi.soveltia.liferay.aitasks.rest.internal.dto.v1_0.converter.AITaskDTOConverter)"
	)
	private DTOConverter<fi.soveltia.liferay.aitasks.model.AITask, AITask>
		_aiTaskDTOConverter;

	private final AITaskEntityModel _aiTaskEntityModel =
		new AITaskEntityModel();

	@Reference
	private AITaskService _aiTaskService;

	@Reference
	private ChatMemoryManager _chatMemoryManager;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private JSONFactory _jsonFactory;

}