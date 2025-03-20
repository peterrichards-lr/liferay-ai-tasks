package fi.soveltia.liferay.aitasks.internal.task.tool;

import com.liferay.object.constants.ObjectDefinitionConstants;
import com.liferay.object.constants.ObjectFieldConstants;
import com.liferay.object.constants.ObjectRelationshipConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectField;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectDefinitionService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.object.service.ObjectFieldService;
import com.liferay.object.service.ObjectRelationshipService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.util.LocalizedMapUtil;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;

import fi.soveltia.liferay.aitasks.spi.task.tool.AITaskTool;

import java.io.Serializable;

import java.text.ParseException;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Fabian Bouché
 * @author Petteri Karttunen
 */
@Component(property = "ai.task.tool.name=object", service = AITaskTool.class)
public class ObjectAITools implements AITaskTool {

	@Override
	public Object getExecutor(JSONObject configurationJSONObject) {
		return new Executor();
	}

	public class Executor {

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Adds a date field to a Liferay Object definition. Returns the Object Field Id or -1 if it fails to create the object field."
		)
		public long addDateFieldToObjectDefinition(
				@P(
					"The External Reference Code of the Field to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P("The label of the Field to be created, human readable")
					String fieldLabel,
				@P(
					"The key of the Field to be created, it uses only letters, camelCase"
				)
				String fieldName,
				@P(
					"The Object Definition ID of the Object Definition, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionId,
				@P("Should the field be required or not") boolean required)
			throws PortalException {

			try {
				ObjectField objectField =
					objectFieldService.addCustomObjectField(
						externalReferenceCode, 0L, objectDefinitionId,
						ObjectFieldConstants.BUSINESS_TYPE_DATE,
						ObjectFieldConstants.DB_TYPE_DATE, true, false, null,
						LocalizedMapUtil.getLocalizedMap(fieldLabel), false,
						fieldName, ObjectFieldConstants.READ_ONLY_FALSE, null,
						required, false, Collections.emptyList());

				return objectField.getObjectFieldId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create object field", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Adds an integer field to a Liferay Object definition. Returns the Object Field Id or -1 if it fails to create the object field."
		)
		public long addIntegerFieldToObjectDefinition(
			@P("The label of the Field to be created, human readable") String
				fieldLabel,
			@P(
				"The key of the Field to be created, it uses only letters, camelCase"
			)
			String fieldName,
			@P(
				"The External Reference Code of the Field to be created, it uses only letters, SCREAMING_SNAKE_CASE"
			)
			String externalReferenceCode,
			@P(
				"The Object Definition ID of the Object Definition, use the getObjectDefinitions tool if you don't know it"
			)
			long objectDefinitionId,
			@P("Should the field be required or not") boolean required) {

			try {
				ObjectField objectField =
					objectFieldService.addCustomObjectField(
						externalReferenceCode, 0L, objectDefinitionId,
						ObjectFieldConstants.BUSINESS_TYPE_INTEGER,
						ObjectFieldConstants.DB_TYPE_INTEGER, true, false, null,
						LocalizedMapUtil.getLocalizedMap(fieldLabel), false,
						fieldName, ObjectFieldConstants.READ_ONLY_FALSE, null,
						required, false, Collections.emptyList());

				return objectField.getObjectFieldId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create object field", portalException);
			}

			return -1;
		}

		@Tool(
			"Adds a new Object Entry (a record) to an existing Object Definition. Each record you pass should include all the required fields. Returns the Object Entry ID or -1 if it failed."
		)
		public long addObjectEntry(
				@P(
					"The Object Definition ID of the Object Definition we should add an Object Entry to, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionId,
				@P(
					"A map of values tuples (pass all the values as String), with one tuple for each Object Field you'd like to insert, eg: [{projectName: 'Appolo X'}, {projectDate: '1967-07-31'}, {projectDescription: 'A secret attempt to go to Mars'}], always using yyyy-MM-dd format for dates"
				)
				Map
					<String, String> stringValues)
			throws ParseException, PortalException {

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			Map<String, Serializable> values = new HashMap<>();

			try {
				for (Map.Entry<String, String> entry :
						stringValues.entrySet()) {

					String key = entry.getKey();
					String value = entry.getValue();

					ObjectField field = objectFieldLocalService.getObjectField(
						objectDefinitionId, key);

					if (ObjectFieldConstants.BUSINESS_TYPE_INTEGER.equals(
							field.getBusinessType())) {

						values.put(key, Integer.valueOf(value));
					}
					else if (ObjectFieldConstants.BUSINESS_TYPE_TEXT.equals(
								field.getBusinessType())) {

						values.put(key, value);
					}
					else if (ObjectFieldConstants.BUSINESS_TYPE_PICKLIST.equals(
								field.getBusinessType())) {

						values.put(key, value);
					}
					else if (ObjectFieldConstants.BUSINESS_TYPE_DATE.equals(
								field.getBusinessType())) {

						try {
							Date dateValue = DateUtil.parseDate(
								"yyyy-MM-dd", value,
								serviceContext.getLocale());

							values.put(key, dateValue);
						}
						catch (ParseException parseException) {
							log.error(
								"Failed to parse " + value + " " + key,
								parseException);

							throw parseException;
						}
					}
				}

				ObjectEntry objectEntry = objectEntryService.addObjectEntry(
					0L, objectDefinitionId, values, serviceContext);

				return objectEntry.getObjectEntryId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create object entry", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Adds an picklist field to a Liferay Object definition (enumeration of values). Returns the Object Field Id or -1 if it fails to create the object field."
		)
		public long addPicklistFieldToObjectDefinition(
				@P(
					"The External Reference Code of the Field to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P("The label of the Field to be created, human readable")
					String fieldLabel,
				@P(
					"The key of the Field to be created, it uses only letters, camelCase"
				)
				String fieldName,
				@P(
					"The Object Definition ID of the Object Definition, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionId,
				@P(
					"The Picklist ID of the Picklist enumerating the authorized values"
				)
				long picklistId,
				@P("Should the field be required or not") boolean required)
			throws PortalException {

			try {
				ObjectField objectField =
					objectFieldService.addCustomObjectField(
						externalReferenceCode, picklistId, objectDefinitionId,
						ObjectFieldConstants.BUSINESS_TYPE_PICKLIST,
						ObjectFieldConstants.DB_TYPE_STRING, true, true, null,
						LocalizedMapUtil.getLocalizedMap(fieldLabel), false,
						fieldName, ObjectFieldConstants.READ_ONLY_FALSE, null,
						required, false, Collections.emptyList());

				return objectField.getObjectFieldId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create object field", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Adds a text field to a Liferay Object definition. Returns the Object Field Id or -1 if it fails to create the object field."
		)
		public long addTextFieldToObjectDefinition(
				@P(
					"The External Reference Code of the Field to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P("The label of the Field to be created, human readable")
					String fieldLabel,
				@P(
					"The key of the Field to be created, it uses only letters, camelCase"
				)
				String fieldName,
				@P(
					"The Object Definition ID of the Object Definition, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionId,
				@P("Should the field be required or not") boolean required)
			throws PortalException {

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			try {
				ObjectField objectField =
					objectFieldService.addCustomObjectField(
						externalReferenceCode, 0L, objectDefinitionId,
						ObjectFieldConstants.BUSINESS_TYPE_TEXT,
						ObjectFieldConstants.DB_TYPE_STRING, true, false,
						serviceContext.getLanguageId(),
						LocalizedMapUtil.getLocalizedMap(fieldLabel), false,
						fieldName, ObjectFieldConstants.READ_ONLY_FALSE, null,
						required, false, Collections.emptyList());

				return objectField.getObjectFieldId();
			}
			catch (PortalException portalException) {
				log.error("Failed to create object field", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Use this tool only if you know the Object Definitions' IDs. Create a many to many relationship between two object definitions. Many Object Entries A are bound to many Object Entries B."
		)
		public long createManyToManyObjectDefinitionRelationship(
				@P(
					"The External Reference Code of the Relationship to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P("A label to designate the relationship") String label,
				@P(
					"The Object Definition ID of object definition A, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionAId,
				@P(
					"The Object Definition ID of object definition B, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionBId,
				@P(
					"The key for the relationship to be created, it uses only letters, camelCase"
				)
				String relationshipName)
			throws PortalException {

			try {
				ObjectRelationship relationship =
					objectRelationshipService.addObjectRelationship(
						externalReferenceCode, objectDefinitionAId,
						objectDefinitionBId, 0L,
						ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE,
						true, LocalizedMapUtil.getLocalizedMap(label),
						relationshipName, false,
						ObjectRelationshipConstants.TYPE_MANY_TO_MANY, null);

				return relationship.getObjectRelationshipId();
			}
			catch (PortalException portalException) {
				log.error(
					"Failed to create object relationship", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Creates an empty Liferay Object definition draft (hint: use this tool before starting to add fields to the object definition). Returns the Object Definition Id associated to the newly created Object Definition. Returns -1 if it failed to create the object definition."
		)
		public long createObjectDefinitionDraft(
				@P(
					"The External Reference Code of the Object Definition to create, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P(
					"The label of the Object Definition to be created, human readable, it should be singular"
				)
				String label,
				@P(
					"The name of the Object Definition to be created, it uses only letters, PascalCase"
				)
				String name,
				@P(
					"The plural of the label of the Object Definition to be created"
				)
				String pluralLabel)
			throws PortalException {

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			try {
				ObjectDefinition objectDefinition =
					objectDefinitionService.addCustomObjectDefinition(
						0, false, false, true, false,
						HashMapBuilder.put(
							serviceContext.getLocale(), label
						).build(),
						name, null, null,
						HashMapBuilder.put(
							serviceContext.getLocale(), pluralLabel
						).build(),
						true, ObjectDefinitionConstants.SCOPE_COMPANY,
						ObjectDefinitionConstants.STORAGE_TYPE_DEFAULT,
						Collections.emptyList());

				objectDefinitionService.updateExternalReferenceCode(
					objectDefinition.getObjectDefinitionId(),
					externalReferenceCode);

				return objectDefinition.getObjectDefinitionId();
			}
			catch (PortalException portalException) {
				log.error(
					"Failed to create object definition", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Use this tool only if you know the Object Definitions' IDs. Create a one to many relationship between two object definitions. One Object Entry A is bound to many Object Entries B. Use this tool if you need a one to one relationship, using Object Definition A as the most important / central object in the relationship."
		)
		public long createOneToManyObjectDefinitionRelationship(
				@P(
					"The External Reference Code of the Relationship to be created, it uses only letters, SCREAMING_SNAKE_CASE"
				)
				String externalReferenceCode,
				@P("A label to designate the relationship") String label,
				@P(
					"The Object Definition ID of object definition A, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionAId,
				@P(
					"The Object Definition ID of object definition B, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionBId,
				@P(
					"The key for the relationship to be created, it uses only letters, camelCase"
				)
				String relationshipName)
			throws PortalException {

			try {
				ObjectRelationship relationship =
					objectRelationshipService.addObjectRelationship(
						externalReferenceCode, objectDefinitionAId,
						objectDefinitionBId, 0L,
						ObjectRelationshipConstants.DELETION_TYPE_DISASSOCIATE,
						true, LocalizedMapUtil.getLocalizedMap(label),
						relationshipName, false,
						ObjectRelationshipConstants.TYPE_ONE_TO_MANY, null);

				return relationship.getObjectRelationshipId();
			}
			catch (PortalException portalException) {
				log.error(
					"Failed to create object relationship", portalException);

				throw portalException;
			}
		}

		@Tool(
			"Returns the list of Liferay Object definitions (Object definition Id: Label - Status)"
		)
		public List<String> getObjectDefinitions() {
			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			return objectDefinitionService.getObjectDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS
			).stream(
			).map(
				objectDefinition -> StringBundler.concat(
					objectDefinition.getLabel(serviceContext.getLocale()), ": ",
					objectDefinition.getObjectDefinitionId(), " - ",
					(objectDefinition.getStatus() == 0) ? "published" : "draft")
			).collect(
				Collectors.toList()
			);
		}

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Returns the list of Object Fields for a given Object Definition (Field key: Label; Type)"
		)
		public List<String> getObjectFields(
			@P(
				"The Object Definition ID, use the getObjectDefinitions tool if you don't know it"
			)
			long objectDefinitionId) {

			ServiceContext serviceContext =
				ServiceContextThreadLocal.getServiceContext();

			return objectFieldLocalService.getObjectFields(
				objectDefinitionId
			).stream(
			).map(
				objectField ->
					objectField.getName() + ": " +
						objectField.getLabel(serviceContext.getLocale()) +
							"; " + objectField.getBusinessType()
			).collect(
				Collectors.toList()
			);
		}

		@Tool(
			"Use this tool only if you know the Object Definition's ID. Publish the Object Definition. This is required before users can start to interact with it."
		)
		public ObjectDefinition publishObjectDefinition(
				@P(
					"The Object Definition ID, use the getObjectDefinitions tool if you don't know it"
				)
				long objectDefinitionId)
			throws PortalException {

			return objectDefinitionService.publishCustomObjectDefinition(
				objectDefinitionId);
		}

	}

	protected static final Log log = LogFactoryUtil.getLog(ObjectAITools.class);

	@Reference
	protected ObjectDefinitionService objectDefinitionService;

	@Reference
	protected ObjectEntryService objectEntryService;

	@Reference
	protected ObjectFieldLocalService objectFieldLocalService;

	@Reference
	protected ObjectFieldService objectFieldService;

	@Reference
	protected ObjectRelationshipService objectRelationshipService;

}