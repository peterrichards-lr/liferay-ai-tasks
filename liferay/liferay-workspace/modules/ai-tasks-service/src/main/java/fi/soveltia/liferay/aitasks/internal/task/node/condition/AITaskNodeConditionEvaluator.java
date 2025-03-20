
package fi.soveltia.liferay.aitasks.internal.task.node.condition;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Condition;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Contains;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Equals;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Exists;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.In;
import fi.soveltia.liferay.aitasks.rest.dto.v1_0.Range;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.context.AITaskContextParameter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Petteri Karttunen
 */
public class AITaskNodeConditionEvaluator {

	public AITaskNodeConditionEvaluator(
		AITaskContext aiTaskContext, boolean debug, Map<String, Object> input) {

		_aiTaskContext = aiTaskContext;
		_debug = debug;
		_input = input;
	}

	public AITaskNodeConditionEvaluationResponse evaluate(Condition condition) {
		if (!_evaluate(condition)) {
			return new AITaskNodeConditionEvaluationResponse(_debugInfo, false);
		}

		return new AITaskNodeConditionEvaluationResponse(_debugInfo, true);
	}

	private boolean _contains(
		Object field, String fieldName, String id, Object value) {

		if (field == null) {
			return false;
		}

		if (field instanceof double[]) {
			double doubleValue = GetterUtil.getDouble(value);

			for (double d : (double[])field) {
				if (d == doubleValue) {
					return _withDebugInfo(id, true, fieldName, value);
				}
			}
		}
		else if (field instanceof float[]) {
			float floatValue = GetterUtil.getFloat(value);

			for (float f : (float[])field) {
				if (f == floatValue) {
					return _withDebugInfo(id, true, fieldName, value);
				}
			}
		}
		else if (field instanceof int[]) {
			int intValue = GetterUtil.getInteger(value);

			for (int i : (int[])field) {
				if (i == intValue) {
					return _withDebugInfo(id, true, fieldName, value);
				}
			}
		}
		else if (field instanceof long[]) {
			long longValue = GetterUtil.getLong(value);

			for (long l : (long[])field) {
				if (l == longValue) {
					return _withDebugInfo(id, true, fieldName, value);
				}
			}
		}
		else if (field instanceof String[]) {
			String stringValue = GetterUtil.getString(value);

			for (String s : (String[])field) {
				if (Objects.equals(s, stringValue)) {
					return _withDebugInfo(id, true, fieldName, value);
				}
			}
		}
		else if (field instanceof String) {
			String stringField = (String)field;

			return _withDebugInfo(
				id, stringField.contains(GetterUtil.getString(value)),
				fieldName, value);
		}

		return _withDebugInfo(id, false, fieldName, value);
	}

	private boolean _evaluate(Condition condition) {
		if (!_evaluateAllConditions(condition.getAllConditions()) ||
			!_evaluateAnyConditions(condition.getAnyConditions()) ||
			!_evaluateContains(condition.getContains()) ||
			!_evaluateEquals(condition.getEquals()) ||
			!_evaluateExists(condition.getExists()) ||
			!_evaluateIn(condition.getIn()) ||
			!_evaluateNot(condition.getNot()) ||
			!_evaluateRange(condition.getRange())) {

			return false;
		}

		return true;
	}

	private boolean _evaluateAllConditions(Condition[] conditions) {
		if (conditions == null) {
			return true;
		}

		for (Condition condition : conditions) {
			if (!_evaluate(condition)) {
				return false;
			}
		}

		return true;
	}

	private boolean _evaluateAnyConditions(Condition[] conditions) {
		if (conditions == null) {
			return true;
		}

		for (Condition condition : conditions) {
			if (_evaluate(condition)) {
				return true;
			}
		}

		return false;
	}

	private boolean _evaluateContains(Contains contains) {
		if (contains == null) {
			return true;
		}

		if (_contains(
				_getField(contains.getField()), contains.getField(),
				contains.getId(), contains.getValue())) {

			return _withDebugInfo(
				contains.getId(), true, contains.getField(),
				contains.getValue());
		}

		return _withDebugInfo(
			contains.getId(), false, contains.getField(), contains.getValue());
	}

	private boolean _evaluateEquals(Equals equals) {
		if (equals == null) {
			return true;
		}

		Object field = _getField(equals.getField());

		if (field == null) {
			return false;
		}

		Object value = equals.getValue();

		if (field instanceof Double) {
			if (_withDebugInfo(
					equals.getId(),
					GetterUtil.getDouble(field) == GetterUtil.getDouble(value),
					equals.getField(), value)) {

				return true;
			}

			return false;
		}
		else if (field instanceof Double[]) {
			return _withDebugInfo(
				equals.getId(),
				Arrays.equals(
					GetterUtil.getDoubleValues(field),
					GetterUtil.getDoubleValues(value)),
				equals.getField(), value);
		}
		else if (field instanceof Float) {
			if (_withDebugInfo(
					equals.getId(),
					GetterUtil.getFloat(field) == GetterUtil.getFloat(value),
					equals.getField(), value)) {

				return true;
			}

			return false;
		}
		else if (field instanceof Float[]) {
			return _withDebugInfo(
				equals.getId(),
				Arrays.equals(
					GetterUtil.getFloatValues(field),
					GetterUtil.getFloatValues(value)),
				equals.getField(), value);
		}
		else if (field instanceof Integer) {
			if (_withDebugInfo(
					equals.getId(),
					GetterUtil.getInteger(field) == GetterUtil.getInteger(
						value),
					equals.getField(), value)) {

				return true;
			}

			return false;
		}
		else if (field instanceof Integer[]) {
			return _withDebugInfo(
				equals.getId(),
				Arrays.equals(
					GetterUtil.getIntegerValues(field),
					GetterUtil.getIntegerValues(value)),
				equals.getField(), value);
		}
		else if (field instanceof Long) {
			if (_withDebugInfo(
					equals.getId(),
					GetterUtil.getLong(field) == GetterUtil.getLong(value),
					equals.getField(), value)) {

				return true;
			}

			return false;
		}
		else if (field instanceof Long[]) {
			return _withDebugInfo(
				equals.getId(),
				Arrays.equals(
					GetterUtil.getLongValues(field),
					GetterUtil.getLongValues(value)),
				equals.getField(), value);
		}
		else if (field instanceof String) {
			return _withDebugInfo(
				equals.getId(),
				StringUtil.equals(
					GetterUtil.getString(field), GetterUtil.getString(value)),
				equals.getField(), value);
		}
		else if (field instanceof String[]) {
			return _withDebugInfo(
				equals.getId(),
				Arrays.equals(
					GetterUtil.getStringValues(field),
					GetterUtil.getStringValues(value)),
				equals.getField(), value);
		}

		return _withDebugInfo(equals.getId(), false, equals.getField(), value);
	}

	private boolean _evaluateExists(Exists exists) {
		if (exists == null) {
			return true;
		}

		if (Objects.isNull(_getField(exists.getField()))) {
			return _withDebugInfo(
				exists.getId(), false, exists.getField(), null);
		}

		return _withDebugInfo(exists.getId(), true, exists.getField(), null);
	}

	private boolean _evaluateIn(In in) {
		if (in == null) {
			return true;
		}

		if (_contains(
				in.getValue(), in.getField(), in.getId(),
				_getField(in.getField()))) {

			return _withDebugInfo(
				in.getId(), true, in.getField(), in.getValue());
		}

		return _withDebugInfo(in.getId(), false, in.getField(), in.getValue());
	}

	private boolean _evaluateNot(Condition condition) {
		if (condition == null) {
			return true;
		}

		if (_evaluate(condition)) {
			return false;
		}

		return true;
	}

	private boolean _evaluateRange(Range range) {
		if (range == null) {
			return true;
		}

		// TODO

		throw new UnsupportedOperationException("Not implemented");
	}

	private Object _getField(String field) {
		if (Validator.isBlank(field)) {
			return true;
		}

		field = field.replaceAll("\\{\\{", "");
		field = field.replaceAll("\\}\\}", "");

		if (field.startsWith("input")) {
			return _input.get(field);
		}

		if (field.startsWith("taskContext")) {
			String fieldName = field.substring("taskContext".length() + 1);

			AITaskContextParameter aiTaskContextParameter =
				_aiTaskContext.getAITaskContextParameter(fieldName);

			if (aiTaskContextParameter != null) {
				return aiTaskContextParameter.getValue();
			}
		}

		return null;
	}

	private boolean _withDebugInfo(
		String id, boolean evaluationResult, String field, Object value) {

		if (_debug) {
			_debugInfo.put(
				id,
				HashMapBuilder.<String, Object>put(
					"field", field
				).put(
					"result", evaluationResult
				).put(
					"value", value
				).build());
		}

		return evaluationResult;
	}

	private AITaskContext _aiTaskContext;
	private boolean _debug;
	private Map<String, Object> _debugInfo = new HashMap<>();
	private Map<String, Object> _input;

}