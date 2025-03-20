/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fi.soveltia.liferay.aitasks.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import fi.soveltia.liferay.aitasks.model.AITask;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AITask in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AITaskCacheModel
	implements CacheModel<AITask>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AITaskCacheModel)) {
			return false;
		}

		AITaskCacheModel aiTaskCacheModel = (AITaskCacheModel)object;

		if ((aiTaskId == aiTaskCacheModel.aiTaskId) &&
			(mvccVersion == aiTaskCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, aiTaskId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", aiTaskId=");
		sb.append(aiTaskId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", configurationJSON=");
		sb.append(configurationJSON);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", description=");
		sb.append(description);
		sb.append(", readOnly=");
		sb.append(readOnly);
		sb.append(", schemaVersion=");
		sb.append(schemaVersion);
		sb.append(", title=");
		sb.append(title);
		sb.append(", version=");
		sb.append(version);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AITask toEntityModel() {
		AITaskImpl aiTaskImpl = new AITaskImpl();

		aiTaskImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			aiTaskImpl.setUuid("");
		}
		else {
			aiTaskImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			aiTaskImpl.setExternalReferenceCode("");
		}
		else {
			aiTaskImpl.setExternalReferenceCode(externalReferenceCode);
		}

		aiTaskImpl.setAITaskId(aiTaskId);
		aiTaskImpl.setCompanyId(companyId);
		aiTaskImpl.setUserId(userId);

		if (userName == null) {
			aiTaskImpl.setUserName("");
		}
		else {
			aiTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			aiTaskImpl.setCreateDate(null);
		}
		else {
			aiTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			aiTaskImpl.setModifiedDate(null);
		}
		else {
			aiTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (configurationJSON == null) {
			aiTaskImpl.setConfigurationJSON("");
		}
		else {
			aiTaskImpl.setConfigurationJSON(configurationJSON);
		}

		aiTaskImpl.setEnabled(enabled);

		if (description == null) {
			aiTaskImpl.setDescription("");
		}
		else {
			aiTaskImpl.setDescription(description);
		}

		aiTaskImpl.setReadOnly(readOnly);

		if (schemaVersion == null) {
			aiTaskImpl.setSchemaVersion("");
		}
		else {
			aiTaskImpl.setSchemaVersion(schemaVersion);
		}

		if (title == null) {
			aiTaskImpl.setTitle("");
		}
		else {
			aiTaskImpl.setTitle(title);
		}

		if (version == null) {
			aiTaskImpl.setVersion("");
		}
		else {
			aiTaskImpl.setVersion(version);
		}

		aiTaskImpl.setStatus(status);
		aiTaskImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			aiTaskImpl.setStatusByUserName("");
		}
		else {
			aiTaskImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			aiTaskImpl.setStatusDate(null);
		}
		else {
			aiTaskImpl.setStatusDate(new Date(statusDate));
		}

		aiTaskImpl.resetOriginalValues();

		return aiTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();
		externalReferenceCode = objectInput.readUTF();

		aiTaskId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		configurationJSON = (String)objectInput.readObject();

		enabled = objectInput.readBoolean();
		description = objectInput.readUTF();

		readOnly = objectInput.readBoolean();
		schemaVersion = objectInput.readUTF();
		title = objectInput.readUTF();
		version = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(aiTaskId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (configurationJSON == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(configurationJSON);
		}

		objectOutput.writeBoolean(enabled);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeBoolean(readOnly);

		if (schemaVersion == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(schemaVersion);
		}

		if (title == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (version == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(version);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public long mvccVersion;
	public String uuid;
	public String externalReferenceCode;
	public long aiTaskId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String configurationJSON;
	public boolean enabled;
	public String description;
	public boolean readOnly;
	public String schemaVersion;
	public String title;
	public String version;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}