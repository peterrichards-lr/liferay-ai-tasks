
package fi.soveltia.liferay.aitasks.internal.task.node;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.constants.SearchContextAttributes;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;

import fi.soveltia.liferay.aitasks.internal.task.util.TaskContextUtil;
import fi.soveltia.liferay.aitasks.spi.task.node.AITaskNode;
import fi.soveltia.liferay.aitasks.task.context.AITaskContext;
import fi.soveltia.liferay.aitasks.task.context.AITaskContextParameter;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeInformation;
import fi.soveltia.liferay.aitasks.task.node.AITaskNodeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Does a local search. Can be used for example for RAG.
 * Blueprints supported.
 *
 * @author Petteri Karttunen
 */
@Component(
	property = "ai.task.node.type=liferaySearch", service = AITaskNode.class
)
public class LiferaySearchAITaskNode
	extends BaseAITaskNode implements AITaskNode {

	@Override
	public AITaskNodeResponse execute(
		AITaskContext aiTaskContext, boolean debug, String id,
		Map<String, Object> input, JSONObject jsonObject) {

		SearchResponse searchResponse = _searcher.search(
			_getSearchRequest(
				jsonObject,
				_createSearchContext(aiTaskContext, input, jsonObject),
				jsonObject.getInt("topK", 3)));

		return toAITaskNodeResponse(
			aiTaskContext, debug,
			_getDebugInfo(debug, searchResponse.getSearchHits()), jsonObject,
			_getTexts(
				aiTaskContext, jsonObject, searchResponse.getSearchHits()));
	}

	@Override
	public AITaskNodeInformation getAITaskNodeInformation() {
		return new AITaskNodeInformation("localDocumentRetrieval", "input");
	}

	@Override
	protected String toStringValue(Object value) {
		if (value == null) {
			return null;
		}

		List<String> list = (List<String>)value;

		if (ListUtil.isEmpty(list)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		for (String s : list) {
			sb.append(s);
			sb.append(" \n");
		}

		return sb.toString();
	}

	private SearchContext _createSearchContext(
		AITaskContext aiTaskContext, Map<String, Object> input,
		JSONObject jsonObject) {

		SearchContext searchContext = new SearchContext();

		AITaskContextParameter remoteIPAddressAITaskContextParameter =
			aiTaskContext.getAITaskContextParameter("remoteIPAddress");

		if (remoteIPAddressAITaskContextParameter != null) {
			searchContext.setAttribute(
				"search.experiences.ip.address",
				remoteIPAddressAITaskContextParameter.getStringValue());
		}

		searchContext.setCompanyId(aiTaskContext.getCompanyId());
		//searchContext.setGroupIds(new long[] {groupId});
		searchContext.setKeywords(
			MapUtil.getString(
				input, jsonObject.getString("inputParameterName", "text")));
		searchContext.setLocale(aiTaskContext.getLocale());

		AITaskContextParameter timeZoneAITaskContextParameter =
			aiTaskContext.getAITaskContextParameter("userTimeZone");

		if (timeZoneAITaskContextParameter != null) {
			searchContext.setTimeZone(
				(TimeZone)timeZoneAITaskContextParameter.getValue());
		}

		searchContext.setUserId(aiTaskContext.getUserId());

		return searchContext;
	}

	private Map<String, Object> _getDebugInfo(
		boolean debug, SearchHits searchHits) {

		if (!debug) {
			return null;
		}

		return HashMapBuilder.<String, Object>put(
			"totalHits", searchHits.getTotalHits()
		).build();
	}

	private SearchRequest _getSearchRequest(
		JSONObject jsonObject, SearchContext searchContext1, int size) {

		SearchRequestBuilder searchRequestBuilder =
			_searchRequestBuilderFactory.builder();

		searchRequestBuilder.from(
			0
		).queryString(
			searchContext1.getKeywords()
		).size(
			size
		).withSearchContext(
			searchContext2 -> {
				_setSearchExperiencesSearchContextAttributes(
					jsonObject, searchContext1, searchContext2);

				searchContext2.setAttribute(
					SearchContextAttributes.
						ATTRIBUTE_KEY_CONTRIBUTE_TUNING_RANKINGS,
					Boolean.TRUE);
				searchContext2.setCompanyId(searchContext1.getCompanyId());
				//searchContext2.setGroupIds(searchContext1.getGroupIds());
				searchContext2.setKeywords(searchContext1.getKeywords());
				searchContext2.setLocale(searchContext1.getLocale());
				searchContext2.setTimeZone(searchContext1.getTimeZone());
				searchContext2.setUserId(searchContext1.getUserId());
			}
		);

		return searchRequestBuilder.build();
	}

	private List<String> _getTexts(
		AITaskContext aiTaskContext, JSONObject jsonObject,
		SearchHits searchHits) {

		if (searchHits.getTotalHits() == 0) {
			return null;
		}

		String resultField = TaskContextUtil.replacePlaceHolderVariables(
			aiTaskContext,
			jsonObject.getString(
				"documentResultField",
				"content_{{taskContext.currentLanguageId}}"));

		List<SearchHit> searchHitList = searchHits.getSearchHits();

		List<String> texts = new ArrayList<>();

		ListUtil.isNotEmptyForEach(
			searchHitList,
			searchHit -> {
				Document document = searchHit.getDocument();

				texts.add(document.getString(resultField));
			});

		return texts;
	}

	private void _setSearchExperiencesSearchContextAttributes(
		JSONObject jsonObject, SearchContext sourceSearchContext,
		SearchContext targetSearchContext) {

		MapUtil.isNotEmptyForEach(
			sourceSearchContext.getAttributes(),
			targetSearchContext::setAttribute);

		String sxpBlueprintExternalReferenceCode = jsonObject.getString(
			"sxpBlueprintExternalReferenceCode");

		if (!Validator.isBlank(sxpBlueprintExternalReferenceCode)) {
			targetSearchContext.setAttribute(
				"search.experiences.blueprint.external.reference.code",
				sxpBlueprintExternalReferenceCode);
		}

		String sxpBlueprintId = jsonObject.getString("sxpBlueprintId");

		if (!Validator.isBlank(sxpBlueprintId)) {
			targetSearchContext.setAttribute(
				"search.experiences.blueprint.id", sxpBlueprintId);
		}
	}

	@Reference
	private Searcher _searcher;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}