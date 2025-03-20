package fi.soveltia.liferay.aitasks.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import fi.soveltia.liferay.aitasks.rest.internal.graphql.mutation.v1_0.Mutation;
import fi.soveltia.liferay.aitasks.rest.internal.graphql.query.v1_0.Query;
import fi.soveltia.liferay.aitasks.rest.internal.resource.v1_0.AITaskResourceImpl;
import fi.soveltia.liferay.aitasks.rest.internal.resource.v1_0.AITaskResponseResourceImpl;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResource;
import fi.soveltia.liferay.aitasks.rest.resource.v1_0.AITaskResponseResource;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Petteri Karttunen
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAITaskResourceComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects);
		Mutation.setAITaskResponseResourceComponentServiceObjects(
			_aiTaskResponseResourceComponentServiceObjects);

		Query.setAITaskResourceComponentServiceObjects(
			_aiTaskResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "AiTasksRest";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/ai-tasks-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createAITask",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "postAITask"));
					put(
						"mutation#createAITaskBatch",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "postAITaskBatch"));
					put(
						"mutation#updateAITaskByExternalReferenceCode",
						new ObjectValuePair<>(
							AITaskResourceImpl.class,
							"putAITaskByExternalReferenceCode"));
					put(
						"mutation#createAITaskByExternalReferenceCodeClear",
						new ObjectValuePair<>(
							AITaskResourceImpl.class,
							"postAITaskByExternalReferenceCodeClear"));
					put(
						"mutation#createAITaskValidate",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "postAITaskValidate"));
					put(
						"mutation#deleteAITask",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "deleteAITask"));
					put(
						"mutation#deleteAITaskBatch",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "deleteAITaskBatch"));
					put(
						"mutation#patchAITask",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "patchAITask"));
					put(
						"mutation#updateAITask",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "putAITask"));
					put(
						"mutation#updateAITaskBatch",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "putAITaskBatch"));
					put(
						"mutation#createAITaskCopy",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "postAITaskCopy"));
					put(
						"mutation#createGenerateExternalReferenceCode",
						new ObjectValuePair<>(
							AITaskResponseResourceImpl.class,
							"postGenerateExternalReferenceCode"));

					put(
						"query#aITasks",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "getAITasksPage"));
					put(
						"query#aITaskByExternalReferenceCode",
						new ObjectValuePair<>(
							AITaskResourceImpl.class,
							"getAITaskByExternalReferenceCode"));
					put(
						"query#aITask",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "getAITask"));
					put(
						"query#aITaskExport",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "getAITaskExport"));

					put(
						"query#AITask.export",
						new ObjectValuePair<>(
							AITaskResourceImpl.class, "getAITaskExport"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AITaskResource>
		_aiTaskResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AITaskResponseResource>
		_aiTaskResponseResourceComponentServiceObjects;

}