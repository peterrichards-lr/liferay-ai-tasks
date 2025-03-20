/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.model.impl;

import fi.soveltia.liferay.aitasks.model.AITask;
import fi.soveltia.liferay.aitasks.service.AITaskLocalServiceUtil;

/**
 * The extended model base implementation for the AITask service. Represents a row in the &quot;AITask&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AITaskImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AITaskImpl
 * @see AITask
 * @generated
 */
public abstract class AITaskBaseImpl extends AITaskModelImpl implements AITask {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ai task model instance should use the <code>AITask</code> interface instead.
	 */
	@Override
	public void persist() {
		if (this.isNew()) {
			AITaskLocalServiceUtil.addAITask(this);
		}
		else {
			AITaskLocalServiceUtil.updateAITask(this);
		}
	}

}