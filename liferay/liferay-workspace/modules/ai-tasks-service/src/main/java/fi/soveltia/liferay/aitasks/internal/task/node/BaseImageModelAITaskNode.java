package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HashMapBuilder;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;

import fi.soveltia.liferay.aitasks.internal.util.PromptUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public abstract class BaseImageModelAITaskNode
	extends BaseAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeResponse execute(
		AITaskContext aiTaskContext, boolean debug, String id,
		Map<String, Object> input, JSONObject jsonObject) {

		return toImageAITaskNodeResponse(
			aiTaskContext, debug, getImageModel(jsonObject), input, jsonObject);
	}

	protected abstract ImageModel getImageModel(JSONObject jsonObject);

	protected AITaskNodeResponse toImageAITaskNodeResponse(
		AITaskContext aiTaskContext, boolean debug, ImageModel imageModel,
		Map<String, Object> input, JSONObject jsonObject) {

		int numberOfImages = jsonObject.getInt("numberOfImages", 1);

		if (numberOfImages > 1) {
			return _toMultiImageAITaskNodeResponse(
				aiTaskContext, debug, imageModel, input, jsonObject,
				numberOfImages);
		}

		return _toSingleImageAITaskNodeResponse(
			aiTaskContext, debug, imageModel, input, jsonObject);
	}

	private Map<String, Object> _getDebugInfo(
		boolean debug, Response<?> response) {

		if (!debug || (response == null)) {
			return null;
		}

		Map<String, Object> debugInfo = new HashMap<>();

		debugInfo.putAll(
			getCommonDebugInfo(response.finishReason(), response.tokenUsage()));

		if (response.content() instanceof List) {
			Map<String, Object> imageDebugInfos = new HashMap<>();

			List<Image> images = (List<Image>)response.content();

			for (int i = 0; i < images.size(); i++) {
				imageDebugInfos.put(
					String.valueOf(i), _getImageDebugInfo(images.get(i)));
			}

			debugInfo.put("images", imageDebugInfos);
		}
		else if (response.content() instanceof Image) {
			debugInfo.putAll(_getImageDebugInfo((Image)response.content()));
		}

		return debugInfo;
	}

	private Map<String, Object> _getImageDebugInfo(Image image) {
		Map<String, Object> debugInfo = new HashMap<>();

		debugInfo.put("mimeType", image.mimeType());
		debugInfo.put("revisedPrompt", image.revisedPrompt());

		return debugInfo;
	}

	private Map<String, Object> _toImageEntry(Image image) {
		if (image.url() != null) {
			return HashMapBuilder.<String, Object>put(
				"url", image.url()
			).build();
		}

		return HashMapBuilder.<String, Object>put(
			"base64Data", image.base64Data()
		).build();
	}

	private AITaskNodeResponse _toMultiImageAITaskNodeResponse(
		AITaskContext aiTaskContext, boolean debug, ImageModel imageModel,
		Map<String, Object> input, JSONObject jsonObject, int numberOfImages) {

		Response<List<Image>> response = imageModel.generate(
			PromptUtil.getUserMessageString(aiTaskContext, input, jsonObject),
			numberOfImages);

		List<Image> images = response.content();

		List<Map<String, Object>> output = new ArrayList<>();

		for (Image image : images) {
			output.add(_toImageEntry(image));
		}

		return toAITaskNodeResponse(
			aiTaskContext, debug, _getDebugInfo(debug, response), jsonObject,
			output);
	}

	private AITaskNodeResponse _toSingleImageAITaskNodeResponse(
		AITaskContext aiTaskContext, boolean debug, ImageModel imageModel,
		Map<String, Object> input, JSONObject jsonObject) {

		Response<Image> response = imageModel.generate(
			PromptUtil.getUserMessageString(aiTaskContext, input, jsonObject));

		return toAITaskNodeResponse(
			aiTaskContext, debug, _getDebugInfo(debug, response), jsonObject,
			_toImageEntry(response.content()));
	}

}