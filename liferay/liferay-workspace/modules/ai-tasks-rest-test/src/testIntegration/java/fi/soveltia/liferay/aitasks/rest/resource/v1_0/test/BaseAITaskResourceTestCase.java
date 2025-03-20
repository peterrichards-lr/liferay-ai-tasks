package fi.soveltia.liferay.aitasks.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.search.test.rule.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.TransformUtil;

import fi.soveltia.liferay.aitasks.rest.client.dto.v1_0.AITask;
import fi.soveltia.liferay.aitasks.rest.client.http.HttpInvoker;
import fi.soveltia.liferay.aitasks.rest.client.pagination.Page;
import fi.soveltia.liferay.aitasks.rest.client.pagination.Pagination;
import fi.soveltia.liferay.aitasks.rest.client.resource.v1_0.AITaskResource;
import fi.soveltia.liferay.aitasks.rest.client.serdes.v1_0.AITaskSerDes;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public abstract class BaseAITaskResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_aiTaskResource.setContextCompany(testCompany);

		AITaskResource.Builder builder = AITaskResource.builder();

		aiTaskResource = builder.authentication(
			"test@liferay.com", PropsValues.DEFAULT_ADMIN_PASSWORD
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		AITask aiTask1 = randomAITask();

		String json = objectMapper.writeValueAsString(aiTask1);

		AITask aiTask2 = AITaskSerDes.toDTO(json);

		Assert.assertTrue(equals(aiTask1, aiTask2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		AITask aiTask = randomAITask();

		String json1 = objectMapper.writeValueAsString(aiTask);
		String json2 = AITaskSerDes.toJSON(aiTask);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	protected ObjectMapper getClientSerDesObjectMapper() {
		return new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		AITask aiTask = randomAITask();

		aiTask.setDescription(regex);
		aiTask.setExternalReferenceCode(regex);
		aiTask.setSchemaVersion(regex);
		aiTask.setTitle(regex);
		aiTask.setVersion(regex);

		String json = AITaskSerDes.toJSON(aiTask);

		Assert.assertFalse(json.contains(regex));

		aiTask = AITaskSerDes.toDTO(json);

		Assert.assertEquals(regex, aiTask.getDescription());
		Assert.assertEquals(regex, aiTask.getExternalReferenceCode());
		Assert.assertEquals(regex, aiTask.getSchemaVersion());
		Assert.assertEquals(regex, aiTask.getTitle());
		Assert.assertEquals(regex, aiTask.getVersion());
	}

	@Test
	public void testGetAITasksPage() throws Exception {
		Page<AITask> page = aiTaskResource.getAITasksPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		AITask aiTask1 = testGetAITasksPage_addAITask(randomAITask());

		AITask aiTask2 = testGetAITasksPage_addAITask(randomAITask());

		page = aiTaskResource.getAITasksPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(aiTask1, (List<AITask>)page.getItems());
		assertContains(aiTask2, (List<AITask>)page.getItems());
		assertValid(page, testGetAITasksPage_getExpectedActions());

		aiTaskResource.deleteAITask(aiTask1.getId());

		aiTaskResource.deleteAITask(aiTask2.getId());
	}

	protected Map<String, Map<String, String>>
			testGetAITasksPage_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetAITasksPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		AITask aiTask1 = randomAITask();

		aiTask1 = testGetAITasksPage_addAITask(aiTask1);

		for (EntityField entityField : entityFields) {
			Page<AITask> page = aiTaskResource.getAITasksPage(
				null, getFilterString(entityField, "between", aiTask1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(aiTask1),
				(List<AITask>)page.getItems());
		}
	}

	@Test
	public void testGetAITasksPageWithFilterDoubleEquals() throws Exception {
		testGetAITasksPageWithFilter("eq", EntityField.Type.DOUBLE);
	}

	@Test
	public void testGetAITasksPageWithFilterStringContains() throws Exception {
		testGetAITasksPageWithFilter("contains", EntityField.Type.STRING);
	}

	@Test
	public void testGetAITasksPageWithFilterStringEquals() throws Exception {
		testGetAITasksPageWithFilter("eq", EntityField.Type.STRING);
	}

	@Test
	public void testGetAITasksPageWithFilterStringStartsWith()
		throws Exception {

		testGetAITasksPageWithFilter("startswith", EntityField.Type.STRING);
	}

	protected void testGetAITasksPageWithFilter(
			String operator, EntityField.Type type)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		AITask aiTask1 = testGetAITasksPage_addAITask(randomAITask());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		AITask aiTask2 = testGetAITasksPage_addAITask(randomAITask());

		for (EntityField entityField : entityFields) {
			Page<AITask> page = aiTaskResource.getAITasksPage(
				null, getFilterString(entityField, operator, aiTask1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(aiTask1),
				(List<AITask>)page.getItems());
		}
	}

	@Test
	public void testGetAITasksPageWithPagination() throws Exception {
		Page<AITask> aiTaskPage = aiTaskResource.getAITasksPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(aiTaskPage.getTotalCount());

		AITask aiTask1 = testGetAITasksPage_addAITask(randomAITask());

		AITask aiTask2 = testGetAITasksPage_addAITask(randomAITask());

		AITask aiTask3 = testGetAITasksPage_addAITask(randomAITask());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<AITask> page1 = aiTaskResource.getAITasksPage(
				null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
					pageSizeLimit),
				null);

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(aiTask1, (List<AITask>)page1.getItems());

			Page<AITask> page2 = aiTaskResource.getAITasksPage(
				null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
					pageSizeLimit),
				null);

			assertContains(aiTask2, (List<AITask>)page2.getItems());

			Page<AITask> page3 = aiTaskResource.getAITasksPage(
				null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
					pageSizeLimit),
				null);

			assertContains(aiTask3, (List<AITask>)page3.getItems());
		}
		else {
			Page<AITask> page1 = aiTaskResource.getAITasksPage(
				null, null, Pagination.of(1, totalCount + 2), null);

			List<AITask> aiTasks1 = (List<AITask>)page1.getItems();

			Assert.assertEquals(
				aiTasks1.toString(), totalCount + 2, aiTasks1.size());

			Page<AITask> page2 = aiTaskResource.getAITasksPage(
				null, null, Pagination.of(2, totalCount + 2), null);

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<AITask> aiTasks2 = (List<AITask>)page2.getItems();

			Assert.assertEquals(aiTasks2.toString(), 1, aiTasks2.size());

			Page<AITask> page3 = aiTaskResource.getAITasksPage(
				null, null, Pagination.of(1, (int)totalCount + 3), null);

			assertContains(aiTask1, (List<AITask>)page3.getItems());
			assertContains(aiTask2, (List<AITask>)page3.getItems());
			assertContains(aiTask3, (List<AITask>)page3.getItems());
		}
	}

	@Test
	public void testGetAITasksPageWithSortDateTime() throws Exception {
		testGetAITasksPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, aiTask1, aiTask2) -> {
				BeanTestUtil.setProperty(
					aiTask1, entityField.getName(),
					new Date(System.currentTimeMillis() - (2 * Time.MINUTE)));
			});
	}

	@Test
	public void testGetAITasksPageWithSortDouble() throws Exception {
		testGetAITasksPageWithSort(
			EntityField.Type.DOUBLE,
			(entityField, aiTask1, aiTask2) -> {
				BeanTestUtil.setProperty(aiTask1, entityField.getName(), 0.1);
				BeanTestUtil.setProperty(aiTask2, entityField.getName(), 0.5);
			});
	}

	@Test
	public void testGetAITasksPageWithSortInteger() throws Exception {
		testGetAITasksPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, aiTask1, aiTask2) -> {
				BeanTestUtil.setProperty(aiTask1, entityField.getName(), 0);
				BeanTestUtil.setProperty(aiTask2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetAITasksPageWithSortString() throws Exception {
		testGetAITasksPageWithSort(
			EntityField.Type.STRING,
			(entityField, aiTask1, aiTask2) -> {
				Class<?> clazz = aiTask1.getClass();

				String entityFieldName = entityField.getName();

				Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanTestUtil.setProperty(
						aiTask1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanTestUtil.setProperty(
						aiTask2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanTestUtil.setProperty(
						aiTask1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanTestUtil.setProperty(
						aiTask2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanTestUtil.setProperty(
						aiTask1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanTestUtil.setProperty(
						aiTask2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetAITasksPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, AITask, AITask, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		AITask aiTask1 = randomAITask();
		AITask aiTask2 = randomAITask();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, aiTask1, aiTask2);
		}

		aiTask1 = testGetAITasksPage_addAITask(aiTask1);

		aiTask2 = testGetAITasksPage_addAITask(aiTask2);

		Page<AITask> page = aiTaskResource.getAITasksPage(
			null, null, null, null);

		for (EntityField entityField : entityFields) {
			Page<AITask> ascPage = aiTaskResource.getAITasksPage(
				null, null, Pagination.of(1, (int)page.getTotalCount() + 1),
				entityField.getName() + ":asc");

			assertContains(aiTask1, (List<AITask>)ascPage.getItems());
			assertContains(aiTask2, (List<AITask>)ascPage.getItems());

			Page<AITask> descPage = aiTaskResource.getAITasksPage(
				null, null, Pagination.of(1, (int)page.getTotalCount() + 1),
				entityField.getName() + ":desc");

			assertContains(aiTask2, (List<AITask>)descPage.getItems());
			assertContains(aiTask1, (List<AITask>)descPage.getItems());
		}
	}

	protected AITask testGetAITasksPage_addAITask(AITask aiTask)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostAITask() throws Exception {
		AITask randomAITask = randomAITask();

		AITask postAITask = testPostAITask_addAITask(randomAITask);

		assertEquals(randomAITask, postAITask);
		assertValid(postAITask);
	}

	protected AITask testPostAITask_addAITask(AITask aiTask) throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetAITaskByExternalReferenceCode() throws Exception {
		AITask postAITask = testGetAITaskByExternalReferenceCode_addAITask();

		AITask getAITask = aiTaskResource.getAITaskByExternalReferenceCode(
			postAITask.getExternalReferenceCode());

		assertEquals(postAITask, getAITask);
		assertValid(getAITask);
	}

	protected AITask testGetAITaskByExternalReferenceCode_addAITask()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetAITaskByExternalReferenceCode() throws Exception {
		AITask aiTask = testGraphQLGetAITaskByExternalReferenceCode_addAITask();

		// No namespace

		Assert.assertTrue(
			equals(
				aiTask,
				AITaskSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"aITaskByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"externalReferenceCode",
											"\"" +
												aiTask.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/aITaskByExternalReferenceCode"))));
	}

	@Test
	public void testGraphQLGetAITaskByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"aITaskByExternalReferenceCode",
						new HashMap<String, Object>() {
							{
								put(
									"externalReferenceCode",
									irrelevantExternalReferenceCode);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected AITask testGraphQLGetAITaskByExternalReferenceCode_addAITask()
		throws Exception {

		return testGraphQLAITask_addAITask();
	}

	@Test
	public void testPutAITaskByExternalReferenceCode() throws Exception {
		AITask postAITask = testPutAITaskByExternalReferenceCode_addAITask();

		AITask randomAITask = randomAITask();

		AITask putAITask = aiTaskResource.putAITaskByExternalReferenceCode(
			postAITask.getExternalReferenceCode(), randomAITask);

		assertEquals(randomAITask, putAITask);
		assertValid(putAITask);

		AITask getAITask = aiTaskResource.getAITaskByExternalReferenceCode(
			putAITask.getExternalReferenceCode());

		assertEquals(randomAITask, getAITask);
		assertValid(getAITask);

		AITask newAITask = testPutAITaskByExternalReferenceCode_createAITask();

		putAITask = aiTaskResource.putAITaskByExternalReferenceCode(
			newAITask.getExternalReferenceCode(), newAITask);

		assertEquals(newAITask, putAITask);
		assertValid(putAITask);

		getAITask = aiTaskResource.getAITaskByExternalReferenceCode(
			putAITask.getExternalReferenceCode());

		assertEquals(newAITask, getAITask);

		Assert.assertEquals(
			newAITask.getExternalReferenceCode(),
			putAITask.getExternalReferenceCode());
	}

	protected AITask testPutAITaskByExternalReferenceCode_createAITask()
		throws Exception {

		return randomAITask();
	}

	protected AITask testPutAITaskByExternalReferenceCode_addAITask()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostAITaskByExternalReferenceCodeClear() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		AITask aiTask = testPostAITaskByExternalReferenceCodeClear_addAITask();

		assertHttpResponseStatusCode(
			204,
			aiTaskResource.postAITaskByExternalReferenceCodeClearHttpResponse(
				aiTask.getExternalReferenceCode(), null));

		assertHttpResponseStatusCode(
			404,
			aiTaskResource.postAITaskByExternalReferenceCodeClearHttpResponse(
				aiTask.getExternalReferenceCode(), null));
	}

	protected AITask testPostAITaskByExternalReferenceCodeClear_addAITask()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostAITaskValidate() throws Exception {
		AITask randomAITask = randomAITask();

		AITask postAITask = testPostAITaskValidate_addAITask(randomAITask);

		assertEquals(randomAITask, postAITask);
		assertValid(postAITask);
	}

	protected AITask testPostAITaskValidate_addAITask(AITask aiTask)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteAITask() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		AITask aiTask = testDeleteAITask_addAITask();

		assertHttpResponseStatusCode(
			204, aiTaskResource.deleteAITaskHttpResponse(aiTask.getId()));

		assertHttpResponseStatusCode(
			404, aiTaskResource.getAITaskHttpResponse(aiTask.getId()));

		assertHttpResponseStatusCode(
			404, aiTaskResource.getAITaskHttpResponse(0L));
	}

	protected AITask testDeleteAITask_addAITask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteAITask() throws Exception {

		// No namespace

		AITask aiTask1 = testGraphQLDeleteAITask_addAITask();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteAITask",
						new HashMap<String, Object>() {
							{
								put("aiTaskId", aiTask1.getId());
							}
						})),
				"JSONObject/data", "Object/deleteAITask"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"aITask",
					new HashMap<String, Object>() {
						{
							put("aiTaskId", aiTask1.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);
	}

	protected AITask testGraphQLDeleteAITask_addAITask() throws Exception {
		return testGraphQLAITask_addAITask();
	}

	@Test
	public void testGetAITask() throws Exception {
		AITask postAITask = testGetAITask_addAITask();

		AITask getAITask = aiTaskResource.getAITask(postAITask.getId());

		assertEquals(postAITask, getAITask);
		assertValid(getAITask);
	}

	protected AITask testGetAITask_addAITask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetAITask() throws Exception {
		AITask aiTask = testGraphQLGetAITask_addAITask();

		// No namespace

		Assert.assertTrue(
			equals(
				aiTask,
				AITaskSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"aITask",
								new HashMap<String, Object>() {
									{
										put("aiTaskId", aiTask.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/aITask"))));
	}

	@Test
	public void testGraphQLGetAITaskNotFound() throws Exception {
		Long irrelevantAiTaskId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"aITask",
						new HashMap<String, Object>() {
							{
								put("aiTaskId", irrelevantAiTaskId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected AITask testGraphQLGetAITask_addAITask() throws Exception {
		return testGraphQLAITask_addAITask();
	}

	@Test
	public void testPatchAITask() throws Exception {
		AITask postAITask = testPatchAITask_addAITask();

		AITask randomPatchAITask = randomPatchAITask();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		AITask patchAITask = aiTaskResource.patchAITask(
			postAITask.getId(), randomPatchAITask);

		AITask expectedPatchAITask = postAITask.clone();

		BeanTestUtil.copyProperties(randomPatchAITask, expectedPatchAITask);

		AITask getAITask = aiTaskResource.getAITask(patchAITask.getId());

		assertEquals(expectedPatchAITask, getAITask);
		assertValid(getAITask);
	}

	protected AITask testPatchAITask_addAITask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutAITask() throws Exception {
		AITask postAITask = testPutAITask_addAITask();

		AITask randomAITask = randomAITask();

		AITask putAITask = aiTaskResource.putAITask(
			postAITask.getId(), randomAITask);

		assertEquals(randomAITask, putAITask);
		assertValid(putAITask);

		AITask getAITask = aiTaskResource.getAITask(putAITask.getId());

		assertEquals(randomAITask, getAITask);
		assertValid(getAITask);
	}

	protected AITask testPutAITask_addAITask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostAITaskCopy() throws Exception {
		AITask randomAITask = randomAITask();

		AITask postAITask = testPostAITaskCopy_addAITask(randomAITask);

		assertEquals(randomAITask, postAITask);
		assertValid(postAITask);
	}

	protected AITask testPostAITaskCopy_addAITask(AITask aiTask)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetAITaskExport() throws Exception {
		Assert.assertTrue(false);
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected AITask testGraphQLAITask_addAITask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(AITask aiTask, List<AITask> aiTasks) {
		boolean contains = false;

		for (AITask item : aiTasks) {
			if (equals(aiTask, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(aiTasks + " does not contain " + aiTask, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(AITask aiTask1, AITask aiTask2) {
		Assert.assertTrue(
			aiTask1 + " does not equal " + aiTask2, equals(aiTask1, aiTask2));
	}

	protected void assertEquals(List<AITask> aiTasks1, List<AITask> aiTasks2) {
		Assert.assertEquals(aiTasks1.size(), aiTasks2.size());

		for (int i = 0; i < aiTasks1.size(); i++) {
			AITask aiTask1 = aiTasks1.get(i);
			AITask aiTask2 = aiTasks2.get(i);

			assertEquals(aiTask1, aiTask2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<AITask> aiTasks1, List<AITask> aiTasks2) {

		Assert.assertEquals(aiTasks1.size(), aiTasks2.size());

		for (AITask aiTask1 : aiTasks1) {
			boolean contains = false;

			for (AITask aiTask2 : aiTasks2) {
				if (equals(aiTask1, aiTask2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				aiTasks2 + " does not contain " + aiTask1, contains);
		}
	}

	protected void assertValid(AITask aiTask) throws Exception {
		boolean valid = true;

		if (aiTask.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (aiTask.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("configuration", additionalAssertFieldName)) {
				if (aiTask.getConfiguration() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (aiTask.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (aiTask.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description_i18n", additionalAssertFieldName)) {
				if (aiTask.getDescription_i18n() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("enabled", additionalAssertFieldName)) {
				if (aiTask.getEnabled() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (aiTask.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (aiTask.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("readOnly", additionalAssertFieldName)) {
				if (aiTask.getReadOnly() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("schemaVersion", additionalAssertFieldName)) {
				if (aiTask.getSchemaVersion() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (aiTask.getTitle() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("title_i18n", additionalAssertFieldName)) {
				if (aiTask.getTitle_i18n() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("version", additionalAssertFieldName)) {
				if (aiTask.getVersion() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<AITask> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<AITask> page, Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<AITask> aiTasks = page.getItems();

		int size = aiTasks.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);

		assertValid(page.getActions(), expectedActions);
	}

	protected void assertValid(
		Map<String, Map<String, String>> actions1,
		Map<String, Map<String, String>> actions2) {

		for (String key : actions2.keySet()) {
			Map action = actions1.get(key);

			Assert.assertNotNull(key + " does not contain an action", action);

			Map<String, String> expectedAction = actions2.get(key);

			Assert.assertEquals(
				expectedAction.get("method"), action.get("method"));
			Assert.assertEquals(expectedAction.get("href"), action.get("href"));
		}
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITask.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(AITask aiTask1, AITask aiTask2) {
		if (aiTask1 == aiTask2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals(
						(Map)aiTask1.getActions(), (Map)aiTask2.getActions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("configuration", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getConfiguration(),
						aiTask2.getConfiguration())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getCreateDate(), aiTask2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getDescription(), aiTask2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description_i18n", additionalAssertFieldName)) {
				if (!equals(
						(Map)aiTask1.getDescription_i18n(),
						(Map)aiTask2.getDescription_i18n())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("enabled", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getEnabled(), aiTask2.getEnabled())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						aiTask1.getExternalReferenceCode(),
						aiTask2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(aiTask1.getId(), aiTask2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getModifiedDate(), aiTask2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("readOnly", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getReadOnly(), aiTask2.getReadOnly())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("schemaVersion", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getSchemaVersion(),
						aiTask2.getSchemaVersion())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getTitle(), aiTask2.getTitle())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("title_i18n", additionalAssertFieldName)) {
				if (!equals(
						(Map)aiTask1.getTitle_i18n(),
						(Map)aiTask2.getTitle_i18n())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("version", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						aiTask1.getVersion(), aiTask2.getVersion())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		if (clazz.getClassLoader() == null) {
			return new java.lang.reflect.Field[0];
		}

		return TransformUtil.transform(
			ReflectionUtil.getDeclaredFields(clazz),
			field -> {
				if (field.isSynthetic()) {
					return null;
				}

				return field;
			},
			java.lang.reflect.Field.class);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_aiTaskResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_aiTaskResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		if (entityModel == null) {
			return Collections.emptyList();
		}

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		return TransformUtil.transform(
			getEntityFields(),
			entityField -> {
				if (!Objects.equals(entityField.getType(), type) ||
					ArrayUtil.contains(
						getIgnoredEntityFieldNames(), entityField.getName())) {

					return null;
				}

				return entityField;
			});
	}

	protected String getFilterString(
		EntityField entityField, String operator, AITask aiTask) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("actions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("configuration")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				Date date = aiTask.getCreateDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(aiTask.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			Object object = aiTask.getDescription();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description_i18n")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("enabled")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			Object object = aiTask.getExternalReferenceCode();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				Date date = aiTask.getModifiedDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(aiTask.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("readOnly")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("schemaVersion")) {
			Object object = aiTask.getSchemaVersion();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("title")) {
			Object object = aiTask.getTitle();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("title_i18n")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("version")) {
			Object object = aiTask.getVersion();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword(
			"test@liferay.com:" + PropsValues.DEFAULT_ADMIN_PASSWORD);

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected AITask randomAITask() throws Exception {
		return new AITask() {
			{
				createDate = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				enabled = RandomTestUtil.randomBoolean();
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				modifiedDate = RandomTestUtil.nextDate();
				readOnly = RandomTestUtil.randomBoolean();
				schemaVersion = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				title = StringUtil.toLowerCase(RandomTestUtil.randomString());
				version = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected AITask randomIrrelevantAITask() throws Exception {
		AITask randomIrrelevantAITask = randomAITask();

		return randomIrrelevantAITask;
	}

	protected AITask randomPatchAITask() throws Exception {
		return randomAITask();
	}

	protected AITaskResource aiTaskResource;
	protected com.liferay.portal.kernel.model.Group irrelevantGroup;
	protected com.liferay.portal.kernel.model.Company testCompany;
	protected com.liferay.portal.kernel.model.Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = source.getClass();

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					_getAllDeclaredFields(sourceClass)) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				try {
					Method setMethod = _getMethod(
						targetClass, field.getName(), "set",
						getMethod.getReturnType());

					setMethod.invoke(target, getMethod.invoke(source));
				}
				catch (Exception e) {
					continue;
				}
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static List<java.lang.reflect.Field> _getAllDeclaredFields(
			Class<?> clazz) {

			List<java.lang.reflect.Field> fields = new ArrayList<>();

			while ((clazz != null) && (clazz != Object.class)) {
				for (java.lang.reflect.Field field :
						clazz.getDeclaredFields()) {

					fields.add(field);
				}

				clazz = clazz.getSuperclass();
			}

			return fields;
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseAITaskResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResource
		_aiTaskResource;

}