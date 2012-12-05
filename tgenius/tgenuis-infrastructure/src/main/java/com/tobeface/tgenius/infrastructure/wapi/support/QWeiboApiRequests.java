package com.tobeface.tgenius.infrastructure.wapi.support;

import java.util.Random;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.WeiboAppKeys;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestIpGenerator;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest.WeiboApiRequestVerb;

/**
 * 
 * @author loudyn
 * 
 */
final class QWeiboApiRequests {

	private static final String SEARCH_U = "http://open.t.qq.com/api/search/user";
	private static final String SEARCH_U_BY_TAGS = " http://open.t.qq.com/api/search/userbytag";
	private static final String SEARCH_T = "http://open.t.qq.com/api/search/t";
	private static final String TRENDS_T = "http://open.t.qq.com/api/trends/t";

	private static final String OTHER_INFO = "https://open.t.qq.com/api/user/other_info";
	private static final String PRIVATE_ADD = "http://open.t.qq.com/api/private/add";
	private static final String RE_ADD = "https://open.t.qq.com/api/t/re_add";
	private static final String T_ADD = "https://open.t.qq.com/api/t/add";

	/**
	 * 
	 * @param appKeys
	 * @param content
	 * @return
	 */
	static WeiboApiRequest newAddTwitter(WeiboAppKeys appKeys, String content) {
		WeiboApiRequest req = new WeiboApiRequest(T_ADD, WeiboApiRequestVerb.POST);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("content", content).param("clientip", WeiboApiRequestIpGenerator.next());
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @param which
	 * @param content
	 * @return
	 */
	static WeiboApiRequest newAddPrivate(WeiboAppKeys appKeys, String which, String content) {
		Preconditions.notNull(appKeys);
		Preconditions.hasText(which);

		WeiboApiRequest req = new WeiboApiRequest(PRIVATE_ADD, WeiboApiRequestVerb.POST);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("content", content).param("name", which);
		req.param("clientip", WeiboApiRequestIpGenerator.next()).param("contentflag", "1");
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @param relayId
	 * @param content
	 * @return
	 */
	static WeiboApiRequest newAddRelay(WeiboAppKeys appKeys, String relayId, String content) {
		Preconditions.notNull(appKeys);
		Preconditions.hasText(relayId);

		WeiboApiRequest req = new WeiboApiRequest(RE_ADD, WeiboApiRequestVerb.POST);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("reid", relayId);
		req.param("content", content).param("clientip", WeiboApiRequestIpGenerator.next());
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @return
	 */
	static WeiboApiRequest newTrendsTwitter(WeiboAppKeys appKeys) {
		Preconditions.notNull(appKeys);

		WeiboApiRequest req = new WeiboApiRequest(TRENDS_T, WeiboApiRequestVerb.GET);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("reqnum", 5);
		req.param("pos", createRandomPos()).param("type", createRandomType());
		return req;
	}

	private static int createRandomPos() {
		return new Random().nextInt(100);
	}

	private static int createRandomType() {
		int[] types = new int[] { 0x1, 0x2, 0x4, 0x8 };
		Random random = new Random();
		int one = types[random.nextInt(types.length)];
		int two = types[random.nextInt(types.length)];
		return one | two;
	}

	/**
	 * 
	 * @param appKeys
	 * @param name
	 * @return
	 */
	static WeiboApiRequest newOtherInfo(WeiboAppKeys appKeys, String name) {
		Preconditions.notNull(appKeys);
		Preconditions.hasText(name);

		WeiboApiRequest req = new WeiboApiRequest(OTHER_INFO, WeiboApiRequestVerb.GET);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("name", name);
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @param talking
	 * @param page
	 * @return
	 */
	static WeiboApiRequest newSearchTwitter(WeiboAppKeys appKeys, WeiboTalking talking, int page) {
		Preconditions.notNull(appKeys);
		Preconditions.notNull(talking);
		Preconditions.isTrue(page > 0);

		WeiboApiRequest req = new WeiboApiRequest(SEARCH_T, WeiboApiRequestVerb.GET);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("keyword", talking.getKeyword()).param("pageSize", 20);
		req.param("page", page).param("contenttype", 0).param("sorttype", 0).param("msgtype", 0).param("searchtype", 0);

		if (talking.hasAvaliableLocation()) {
			req.param("longitude", talking.getLocLongitude()).param("latitude", talking.getLocLatitude()).param("radius", talking.getRadius());
		}

		if (talking.hasAvaliableTalkingTime()) {
			req.param("starttime", talking.getTalkingStartTime()).param("endtime", talking.getTalkingEndTime());
		}
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @param keyword
	 * @param page
	 * @return
	 */
	static WeiboApiRequest newSearchUser(WeiboAppKeys appKeys, String keyword, int page) {
		Preconditions.notNull(appKeys);
		Preconditions.hasText(keyword);
		Preconditions.isTrue(page > 0);

		WeiboApiRequest req = new WeiboApiRequest(SEARCH_U, WeiboApiRequestVerb.GET);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("keyword", keyword);
		req.param("pagesize", 15).param("page", page);
		return req;
	}

	/**
	 * 
	 * @param appKeys
	 * @param tags
	 * @param page
	 * @return
	 */
	static WeiboApiRequest newSearchUserByTags(WeiboAppKeys appKeys, String tags, int page) {
		Preconditions.notNull(appKeys);
		Preconditions.hasText(tags);
		Preconditions.isTrue(page > 0);

		WeiboApiRequest req = new WeiboApiRequest(SEARCH_U_BY_TAGS, WeiboApiRequestVerb.GET);
		req.appKey(appKeys.getApiKey()).accessToken(appKeys.getAccessToken()).params(appKeys.getOtherParamsAsMap());
		req.param("format", "json").param("keyword", tags);
		req.param("pagesize", 15).param("page", page);
		return req;
	}

	private QWeiboApiRequests() {}
}
