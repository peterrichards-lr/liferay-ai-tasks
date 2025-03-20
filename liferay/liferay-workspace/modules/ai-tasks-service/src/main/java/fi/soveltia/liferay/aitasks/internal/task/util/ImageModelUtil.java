package fi.soveltia.liferay.aitasks.internal.task.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.vertexai.VertexAiImageModel;

import fi.soveltia.liferay.aitasks.internal.util.SetterUtil;

import java.net.URI;

/**
 * @author Petteri Karttunen
 */
public class ImageModelUtil {

	public static String generateImage(
		JSONObject jsonObject, String text, String type) {

		if (type == null) {
			throw new IllegalArgumentException(
				"Image model type is not defined");
		}

		if (StringUtil.equals(type, "googleImagen")) {
			return generateVertexAIImageModelImage(jsonObject, text);
		}
		else if (StringUtil.equals(type, "openAIImageModel")) {
			return generateOpenAIImageModelImage(jsonObject, text);
		}

		throw new IllegalArgumentException("Unknown image model");
	}

	public static String generateOpenAIImageModelImage(
		JSONObject jsonObject, String text) {

		return _generateImage(getOpenAIImageModel(jsonObject), text);
	}

	public static String generateVertexAIImageModelImage(
		JSONObject jsonObject, String text) {

		return _generateImage(getVertexAiImageModel(jsonObject), text);
	}

	public static ImageModel getOpenAIImageModel(JSONObject jsonObject) {
		OpenAiImageModel.OpenAiImageModelBuilder builder =
			OpenAiImageModel.builder();

		SetterUtil.setNotBlankString(
			builder::apiKey, jsonObject.getString("apiKey"));

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("model"));
		SetterUtil.setNotBlankString(
			builder::quality, jsonObject.getString("quality"));
		SetterUtil.setNotBlankString(
			builder::responseFormat, jsonObject.getString("responseFormat"));
		SetterUtil.setNotBlankString(
			builder::size, jsonObject.getString("size"));
		SetterUtil.setNotBlankString(
			builder::style, jsonObject.getString("style"));
		SetterUtil.setNotBlankString(
			builder::user, jsonObject.getString("user"));

		return builder.build();
	}

	public static VertexAiImageModel getVertexAiImageModel(
		JSONObject jsonObject) {

		VertexAiImageModel.Builder builder = VertexAiImageModel.builder();

		SetterUtil.setNotBlankString(
			builder::endpoint, jsonObject.getString("endpoint"));
		SetterUtil.setNotNullInteger(
			builder::guidanceScale, jsonObject, "guidanceScale");
		SetterUtil.setNotBlankString(
			builder::language, jsonObject.getString("language"));
		SetterUtil.setNotBlankString(
			builder::location, jsonObject.getString("location"));

		builder.logRequests(jsonObject.getBoolean("logRequests"));
		builder.logResponses(jsonObject.getBoolean("logResponses"));

		SetterUtil.setNotNullInteger(
			builder::maxRetries, jsonObject, "maxRetries");
		SetterUtil.setNotBlankString(
			builder::modelName, jsonObject.getString("model"));
		SetterUtil.setNotBlankString(
			builder::negativePrompt, jsonObject.getString("negativePrompt"));
		SetterUtil.setNotBlankString(
			builder::project, jsonObject.getString("project"));
		SetterUtil.setNotBlankString(
			builder::publisher, jsonObject.getString("publisher"));
		SetterUtil.setNotNullInteger(
			builder::sampleImageSize, jsonObject, "sampleImageSize");

		VertexAiImageModel.ImageStyle imageStyle = _getVertexAiImageStyle(
			jsonObject);

		if (imageStyle != null) {
			builder.sampleImageStyle(imageStyle);
		}

		SetterUtil.setNotNullLong(builder::seed, jsonObject, "seed");

		return builder.build();
	}

	private static String _generateImage(ImageModel imageModel, String text) {
		Response<Image> response = imageModel.generate(text);

		Image image = response.content();

		if (image.url() != null) {
			URI uri = image.url();

			return uri.toString();
		}

		return image.base64Data();
	}

	private static VertexAiImageModel.ImageStyle _getVertexAiImageStyle(
		JSONObject jsonObject) {

		String imageStyle = jsonObject.getString("imageStyle");

		if (Validator.isBlank(imageStyle)) {
			return null;
		}

		if (imageStyle.equals("cyberpunk")) {
			return VertexAiImageModel.ImageStyle.CYBERPUNK;
		}
		else if (imageStyle.equals("digitalArt")) {
			return VertexAiImageModel.ImageStyle.DIGITAL_ART;
		}
		else if (imageStyle.equals("landscape")) {
			return VertexAiImageModel.ImageStyle.LANDSCAPE;
		}
		else if (imageStyle.equals("photograph")) {
			return VertexAiImageModel.ImageStyle.PHOTOGRAPH;
		}
		else if (imageStyle.equals("popArt")) {
			return VertexAiImageModel.ImageStyle.POP_ART;
		}
		else if (imageStyle.equals("sketch")) {
			return VertexAiImageModel.ImageStyle.SKETCH;
		}
		else if (imageStyle.equals("watercolor")) {
			return VertexAiImageModel.ImageStyle.WATERCOLOR;
		}

		throw new IllegalArgumentException("Invalid image style " + imageStyle);
	}

}