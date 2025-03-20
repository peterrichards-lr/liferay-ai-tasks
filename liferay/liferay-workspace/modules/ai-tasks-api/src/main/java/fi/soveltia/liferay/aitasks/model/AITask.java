/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the AITask service. Represents a row in the &quot;AITask&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AITaskModel
 * @generated
 */
@ImplementationClassName("fi.soveltia.liferay.aitasks.model.impl.AITaskImpl")
@ProviderType
public interface AITask extends AITaskModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>fi.soveltia.liferay.aitasks.model.impl.AITaskImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AITask, Long> AI_TASK_ID_ACCESSOR =
		new Accessor<AITask, Long>() {

			@Override
			public Long get(AITask aiTask) {
				return aiTask.getAITaskId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AITask> getTypeClass() {
				return AITask.class;
			}

		};

}