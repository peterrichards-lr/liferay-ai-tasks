package fi.soveltia.liferay.aitasks.internal.security.permission;

import com.liferay.portal.kernel.security.permission.PermissionUpdateHandler;
import com.liferay.portal.kernel.util.GetterUtil;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	property = "model.class.name=fi.soveltia.liferay.aitasks.model.AITask",
	service = PermissionUpdateHandler.class
)
public class AITaskPermissionUpdateHandler implements PermissionUpdateHandler {

	@Override
	public void updatedPermission(String primKey) {
		AITask aiTask = _aiTaskLocalService.fetchAITask(
			GetterUtil.getLong(primKey));

		if (aiTask == null) {
			return;
		}

		_aiTaskLocalService.updateAITask(aiTask);
	}

	@Reference
	private AITaskLocalService _aiTaskLocalService;

}