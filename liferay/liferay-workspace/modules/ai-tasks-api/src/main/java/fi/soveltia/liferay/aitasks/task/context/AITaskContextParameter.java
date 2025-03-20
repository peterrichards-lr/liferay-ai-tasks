package fi.soveltia.liferay.aitasks.task.context;

/**
 * @author Petteri Karttunen
 */
public class AITaskContextParameter {

	public AITaskContextParameter(String name, String stringValue) {
		_name = name;
		_stringValue = stringValue;

		_value = stringValue;
	}

	public AITaskContextParameter(
		String name, String stringValue, Object value) {

		_name = name;
		_stringValue = stringValue;
		_value = value;
	}

	public String getName() {
		return _name;
	}

	public String getStringValue() {
		return _stringValue;
	}

	public Object getValue() {
		return _value;
	}

	private final String _name;
	private final String _stringValue;
	private final Object _value;

}