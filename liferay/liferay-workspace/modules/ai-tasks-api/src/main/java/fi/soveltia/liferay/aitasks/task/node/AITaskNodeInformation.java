package fi.soveltia.liferay.aitasks.task.node;

/**
 * @author Petteri Karttunen
 */
public class AITaskNodeInformation {

	public AITaskNodeInformation(String label, String type) {
		_label = label;
		_type = type;
	}

	public String getLabel() {
		return _label;
	}

	public String getType() {
		return _type;
	}

	private final String _label;
	private final String _type;

}