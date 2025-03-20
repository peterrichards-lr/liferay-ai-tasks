package fi.soveltia.liferay.aitasks.rest.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITask;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITaskRequest;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.AITaskResponse;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResource;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResponseResource;

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
public class Mutation {

	public static void setAITaskResourceComponentServiceObjects(
		ComponentServiceObjects<AITaskResource>
			aiTaskResourceComponentServiceObjects) {

		_aiTaskResourceComponentServiceObjects =
			aiTaskResourceComponentServiceObjects;
	}

	public static void setAITaskResponseResourceComponentServiceObjects(
		ComponentServiceObjects<AITaskResponseResource>
			aiTaskResponseResourceComponentServiceObjects) {

		_aiTaskResponseResourceComponentServiceObjects =
			aiTaskResponseResourceComponentServiceObjects;
	}

	@GraphQLField
	public AITask createAITask(@GraphQLName("aiTask") AITask aiTask)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.postAITask(aiTask));
	}

	@GraphQLField
	public Response createAITaskBatch(
			@GraphQLName("aiTask") AITask aiTask,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.postAITaskBatch(
				aiTask, callbackURL, object));
	}

	@GraphQLField
	public AITask updateAITaskByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("aiTask") AITask aiTask)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.putAITaskByExternalReferenceCode(
				externalReferenceCode, aiTask));
	}

	@GraphQLField
	public boolean createAITaskByExternalReferenceCodeClear(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("nodeId") String nodeId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource ->
				aiTaskResource.postAITaskByExternalReferenceCodeClear(
					externalReferenceCode, nodeId));

		return true;
	}

	@GraphQLField
	public AITask createAITaskValidate(@GraphQLName("string") String string)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.postAITaskValidate(string));
	}

	@GraphQLField
	public boolean deleteAITask(@GraphQLName("aiTaskId") Long aiTaskId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.deleteAITask(aiTaskId));

		return true;
	}

	@GraphQLField
	public Response deleteAITaskBatch(
			@GraphQLName("aiTaskId") Long aiTaskId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.deleteAITaskBatch(
				aiTaskId, callbackURL, object));
	}

	@GraphQLField
	public AITask patchAITask(
			@GraphQLName("aiTaskId") Long aiTaskId,
			@GraphQLName("aiTask") AITask aiTask)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.patchAITask(aiTaskId, aiTask));
	}

	@GraphQLField
	public AITask updateAITask(
			@GraphQLName("aiTaskId") Long aiTaskId,
			@GraphQLName("aiTask") AITask aiTask)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.putAITask(aiTaskId, aiTask));
	}

	@GraphQLField
	public Response updateAITaskBatch(
			@GraphQLName("aiTaskId") Long aiTaskId,
			@GraphQLName("aiTask") AITask aiTask,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.putAITaskBatch(
				aiTaskId, aiTask, callbackURL, object));
	}

	@GraphQLField
	public AITask createAITaskCopy(@GraphQLName("aiTaskId") Long aiTaskId)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResource -> aiTaskResource.postAITaskCopy(aiTaskId));
	}

	@GraphQLField(description = "TBD")
	public AITaskResponse createGenerateExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("aiTaskRequest") AITaskRequest aiTaskRequest)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiTaskResponseResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiTaskResponseResource ->
				aiTaskResponseResource.postGenerateExternalReferenceCode(
					externalReferenceCode, aiTaskRequest));
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
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

		aiTaskResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private void _populateResourceContext(
			AITaskResponseResource aiTaskResponseResource)
		throws Exception {

		aiTaskResponseResource.setContextAcceptLanguage(_acceptLanguage);
		aiTaskResponseResource.setContextCompany(_company);
		aiTaskResponseResource.setContextHttpServletRequest(
			_httpServletRequest);
		aiTaskResponseResource.setContextHttpServletResponse(
			_httpServletResponse);
		aiTaskResponseResource.setContextUriInfo(_uriInfo);
		aiTaskResponseResource.setContextUser(_user);
		aiTaskResponseResource.setGroupLocalService(_groupLocalService);
		aiTaskResponseResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AITaskResource>
		_aiTaskResourceComponentServiceObjects;
	private static ComponentServiceObjects<AITaskResponseResource>
		_aiTaskResponseResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}