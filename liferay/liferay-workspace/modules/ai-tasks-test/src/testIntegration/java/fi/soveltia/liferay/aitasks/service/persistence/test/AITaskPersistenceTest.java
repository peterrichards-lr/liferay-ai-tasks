/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import fi.soveltia.liferay.aitasks.exception.DuplicateAITaskExternalReferenceCodeException;
import fi.soveltia.liferay.aitasks.exception.NoSuchAITaskException;
import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalServiceUtil;
import fi.soveltia.liferay.aitasks.service.persistence.AITaskPersistence;
import fi.soveltia.liferay.aitasks.service.persistence.AITaskUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class AITaskPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "fi.soveltia.liferay.aitasks.service"));

	@Before
	public void setUp() {
		_persistence = AITaskUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<AITask> iterator = _aiTasks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AITask aiTask = _persistence.create(pk);

		Assert.assertNotNull(aiTask);

		Assert.assertEquals(aiTask.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		AITask newAITask = addAITask();

		_persistence.remove(newAITask);

		AITask existingAITask = _persistence.fetchByPrimaryKey(
			newAITask.getPrimaryKey());

		Assert.assertNull(existingAITask);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addAITask();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AITask newAITask = _persistence.create(pk);

		newAITask.setMvccVersion(RandomTestUtil.nextLong());

		newAITask.setUuid(RandomTestUtil.randomString());

		newAITask.setExternalReferenceCode(RandomTestUtil.randomString());

		newAITask.setCompanyId(RandomTestUtil.nextLong());

		newAITask.setUserId(RandomTestUtil.nextLong());

		newAITask.setUserName(RandomTestUtil.randomString());

		newAITask.setCreateDate(RandomTestUtil.nextDate());

		newAITask.setModifiedDate(RandomTestUtil.nextDate());

		newAITask.setConfigurationJSON(RandomTestUtil.randomString());

		newAITask.setEnabled(RandomTestUtil.randomBoolean());

		newAITask.setDescription(RandomTestUtil.randomString());

		newAITask.setReadOnly(RandomTestUtil.randomBoolean());

		newAITask.setSchemaVersion(RandomTestUtil.randomString());

		newAITask.setTitle(RandomTestUtil.randomString());

		newAITask.setVersion(RandomTestUtil.randomString());

		newAITask.setStatus(RandomTestUtil.nextInt());

		newAITask.setStatusByUserId(RandomTestUtil.nextLong());

		newAITask.setStatusByUserName(RandomTestUtil.randomString());

		newAITask.setStatusDate(RandomTestUtil.nextDate());

		_aiTasks.add(_persistence.update(newAITask));

		AITask existingAITask = _persistence.findByPrimaryKey(
			newAITask.getPrimaryKey());

		Assert.assertEquals(
			existingAITask.getMvccVersion(), newAITask.getMvccVersion());
		Assert.assertEquals(existingAITask.getUuid(), newAITask.getUuid());
		Assert.assertEquals(
			existingAITask.getExternalReferenceCode(),
			newAITask.getExternalReferenceCode());
		Assert.assertEquals(
			existingAITask.getAITaskId(), newAITask.getAITaskId());
		Assert.assertEquals(
			existingAITask.getCompanyId(), newAITask.getCompanyId());
		Assert.assertEquals(existingAITask.getUserId(), newAITask.getUserId());
		Assert.assertEquals(
			existingAITask.getUserName(), newAITask.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAITask.getCreateDate()),
			Time.getShortTimestamp(newAITask.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingAITask.getModifiedDate()),
			Time.getShortTimestamp(newAITask.getModifiedDate()));
		Assert.assertEquals(
			existingAITask.getConfigurationJSON(),
			newAITask.getConfigurationJSON());
		Assert.assertEquals(existingAITask.isEnabled(), newAITask.isEnabled());
		Assert.assertEquals(
			existingAITask.getDescription(), newAITask.getDescription());
		Assert.assertEquals(
			existingAITask.isReadOnly(), newAITask.isReadOnly());
		Assert.assertEquals(
			existingAITask.getSchemaVersion(), newAITask.getSchemaVersion());
		Assert.assertEquals(existingAITask.getTitle(), newAITask.getTitle());
		Assert.assertEquals(
			existingAITask.getVersion(), newAITask.getVersion());
		Assert.assertEquals(existingAITask.getStatus(), newAITask.getStatus());
		Assert.assertEquals(
			existingAITask.getStatusByUserId(), newAITask.getStatusByUserId());
		Assert.assertEquals(
			existingAITask.getStatusByUserName(),
			newAITask.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingAITask.getStatusDate()),
			Time.getShortTimestamp(newAITask.getStatusDate()));
	}

	@Test(expected = DuplicateAITaskExternalReferenceCodeException.class)
	public void testUpdateWithExistingExternalReferenceCode() throws Exception {
		AITask aiTask = addAITask();

		AITask newAITask = addAITask();

		newAITask.setCompanyId(aiTask.getCompanyId());

		newAITask = _persistence.update(newAITask);

		Session session = _persistence.getCurrentSession();

		session.evict(newAITask);

		newAITask.setExternalReferenceCode(aiTask.getExternalReferenceCode());

		_persistence.update(newAITask);
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(RandomTestUtil.nextLong());

		_persistence.countByCompanyId(0L);
	}

	@Test
	public void testCountByC_R() throws Exception {
		_persistence.countByC_R(
			RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

		_persistence.countByC_R(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByERC_C() throws Exception {
		_persistence.countByERC_C("", RandomTestUtil.nextLong());

		_persistence.countByERC_C("null", 0L);

		_persistence.countByERC_C((String)null, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		AITask newAITask = addAITask();

		AITask existingAITask = _persistence.findByPrimaryKey(
			newAITask.getPrimaryKey());

		Assert.assertEquals(existingAITask, newAITask);
	}

	@Test(expected = NoSuchAITaskException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<AITask> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"AITask", "mvccVersion", true, "uuid", true,
			"externalReferenceCode", true, "aiTaskId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "enabled", true, "description", true,
			"readOnly", true, "schemaVersion", true, "title", true, "version",
			true, "status", true, "statusByUserId", true, "statusByUserName",
			true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		AITask newAITask = addAITask();

		AITask existingAITask = _persistence.fetchByPrimaryKey(
			newAITask.getPrimaryKey());

		Assert.assertEquals(existingAITask, newAITask);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AITask missingAITask = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingAITask);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		AITask newAITask1 = addAITask();
		AITask newAITask2 = addAITask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAITask1.getPrimaryKey());
		primaryKeys.add(newAITask2.getPrimaryKey());

		Map<Serializable, AITask> aiTasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(2, aiTasks.size());
		Assert.assertEquals(
			newAITask1, aiTasks.get(newAITask1.getPrimaryKey()));
		Assert.assertEquals(
			newAITask2, aiTasks.get(newAITask2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, AITask> aiTasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(aiTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		AITask newAITask = addAITask();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAITask.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, AITask> aiTasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, aiTasks.size());
		Assert.assertEquals(newAITask, aiTasks.get(newAITask.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, AITask> aiTasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertTrue(aiTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		AITask newAITask = addAITask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newAITask.getPrimaryKey());

		Map<Serializable, AITask> aiTasks = _persistence.fetchByPrimaryKeys(
			primaryKeys);

		Assert.assertEquals(1, aiTasks.size());
		Assert.assertEquals(newAITask, aiTasks.get(newAITask.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			AITaskLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<AITask>() {

				@Override
				public void performAction(AITask aiTask) {
					Assert.assertNotNull(aiTask);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		AITask newAITask = addAITask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AITask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("aiTaskId", newAITask.getAITaskId()));

		List<AITask> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		AITask existingAITask = result.get(0);

		Assert.assertEquals(existingAITask, newAITask);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AITask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("aiTaskId", RandomTestUtil.nextLong()));

		List<AITask> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		AITask newAITask = addAITask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AITask.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("aiTaskId"));

		Object newAITaskId = newAITask.getAITaskId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in("aiTaskId", new Object[] {newAITaskId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAITaskId = result.get(0);

		Assert.assertEquals(existingAITaskId, newAITaskId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AITask.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("aiTaskId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"aiTaskId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		AITask newAITask = addAITask();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newAITask.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		AITask newAITask = addAITask();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AITask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("aiTaskId", newAITask.getAITaskId()));

		List<AITask> result = _persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(AITask aiTask) {
		Assert.assertEquals(
			aiTask.getExternalReferenceCode(),
			ReflectionTestUtil.invoke(
				aiTask, "getColumnOriginalValue", new Class<?>[] {String.class},
				"externalReferenceCode"));
		Assert.assertEquals(
			Long.valueOf(aiTask.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				aiTask, "getColumnOriginalValue", new Class<?>[] {String.class},
				"companyId"));
	}

	protected AITask addAITask() throws Exception {
		long pk = RandomTestUtil.nextLong();

		AITask aiTask = _persistence.create(pk);

		aiTask.setMvccVersion(RandomTestUtil.nextLong());

		aiTask.setUuid(RandomTestUtil.randomString());

		aiTask.setExternalReferenceCode(RandomTestUtil.randomString());

		aiTask.setCompanyId(RandomTestUtil.nextLong());

		aiTask.setUserId(RandomTestUtil.nextLong());

		aiTask.setUserName(RandomTestUtil.randomString());

		aiTask.setCreateDate(RandomTestUtil.nextDate());

		aiTask.setModifiedDate(RandomTestUtil.nextDate());

		aiTask.setConfigurationJSON(RandomTestUtil.randomString());

		aiTask.setEnabled(RandomTestUtil.randomBoolean());

		aiTask.setDescription(RandomTestUtil.randomString());

		aiTask.setReadOnly(RandomTestUtil.randomBoolean());

		aiTask.setSchemaVersion(RandomTestUtil.randomString());

		aiTask.setTitle(RandomTestUtil.randomString());

		aiTask.setVersion(RandomTestUtil.randomString());

		aiTask.setStatus(RandomTestUtil.nextInt());

		aiTask.setStatusByUserId(RandomTestUtil.nextLong());

		aiTask.setStatusByUserName(RandomTestUtil.randomString());

		aiTask.setStatusDate(RandomTestUtil.nextDate());

		_aiTasks.add(_persistence.update(aiTask));

		return aiTask;
	}

	private List<AITask> _aiTasks = new ArrayList<AITask>();
	private AITaskPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}