package fi.soveltia.liferay.aitasks.rest.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITask;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Generated("")
public class Query {

	public static void setAITaskResourceComponentServiceObjects(
		ComponentServiceObjects<AITaskResource>
			aiTaskResourceComponentServiceObjects) {

		_aiTaskResourceComponentServiceObjects =
			aiTaskResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {aITasks(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AITaskPage aITasks(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> new AITaskPage(
				aiTaskResource.getAITasksPage(
					search,
					_filterBiFunction.apply(aiTaskResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(aiTaskResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {aITaskByExternalReferenceCode(externalReferenceCode: ___){actions, configuration, createDate, description, description_i18n, enabled, externalReferenceCode, id, modifiedDate, readOnly, schemaVersion, title, title_i18n, version}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AITask aITaskByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.getAITaskByExternalReferenceCode(
				externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {aITask(aiTaskId: ___){actions, configuration, createDate, description, description_i18n, enabled, externalReferenceCode, id, modifiedDate, readOnly, schemaVersion, title, title_i18n, version}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AITask aITask(@GraphQLName("aiTaskId") Long aiTaskId)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.getAITask(aiTaskId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {aITaskExport(aiTaskId: ___){}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Response aITaskExport(@GraphQLName("aiTaskId") Long aiTaskId)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.getAITaskExport(aiTaskId));
	}

	@GraphQLTypeExtension(AITask.class)
	public class GetAITaskExportTypeExtension {

		public GetAITaskExportTypeExtension(AITask aITask) {
			_aITask = aITask;
		}

		@GraphQLField
		public Response export() throws Exception {
			return _applyComponentServiceObjects(
				_aiTaskResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				aiTaskResource -> aiTaskResource.getAITaskExport(
					_aITask.getId()));
		}

		private AITask _aITask;

	}

	@GraphQLName("AITaskPage")
	public class AITaskPage {

		public AITaskPage(Page aiTaskPage) {
			actions = aiTaskPage.getActions();

			items = aiTaskPage.getItems();
			lastPage = aiTaskPage.getLastPage();
			page = aiTaskPage.getPage();
			pageSize = aiTaskPage.getPageSize();
			totalCount = aiTaskPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<AITask> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AITaskResource aiTaskResource)
		throws Exception {

		aiTaskResource.setContextAcceptLanguage(_acceptLanguage);
		aiTaskResource.setContextCompany(_company);
		aiTaskResource.setContextHttpServletRequest(_httpServletRequest);
		aiTaskResource.setContextHttpServletResponse(_httpServletResponse);
		aiTaskResource.setContextUriInfo(_uriInfo);
		aiTaskResource.setContextUser(_user);
		aiTaskResource.setGroupLocalService(_groupLocalService);
		aiTaskResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AITaskResource>
		_aiTaskResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}