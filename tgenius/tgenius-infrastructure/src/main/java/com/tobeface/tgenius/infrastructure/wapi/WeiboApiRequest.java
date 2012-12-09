package com.tobeface.tgenius.infrastructure.wapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableMap;
import com.tobeface.modules.helper.CodeHelper;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.policy.WeiboApiRequestPolicies;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboApiRequest {

	private final String url;
	private final WeiboApiRequestVerb verb;

	private Map<String, Object> queryParams = new HashMap<String, Object>();
	private Map<String, Object> bodyParams = new HashMap<String, Object>();

	/**
	 * 
	 * @param url
	 * @param verb
	 */
	public WeiboApiRequest(String url, WeiboApiRequestVerb verb) {
		Preconditions.hasText(url);
		Preconditions.notNull(verb);
		this.url = url;
		this.verb = verb;
	}

	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		if (!isGetVerb()) {
			return url;
		}

		StringBuilder buf = new StringBuilder();
		String tempUrl = url;
		if (tempUrl.endsWith("?")) {
			tempUrl = tempUrl.substring(0, tempUrl.length() - 1);
		}

		for (Entry<String, Object> entry : queryParams.entrySet()) {
			buf.append(encodeURL(entry.getKey())).append("=").append(encodeURL(entry.getValue().toString())).append("&");
		}

		return String.format("%s?%s", tempUrl, buf.toString());
	}

	private Object encodeURL(String raw) {
		return CodeHelper.urlEncode(raw, "UTF-8");
	}

	/**
	 * 
	 * @return
	 */
	public WeiboApiRequestVerb getVerb() {
		return verb;
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		if (isGetVerb()) {
			return Collections.emptyMap();
		}

		return ImmutableMap.copyOf(bodyParams);
	}

	/**
	 * 
	 * @return
	 */
	public WeiboApiResponse execute() {
		return execute(WeiboApiRequestPolicies.newNop());
	}

	/**
	 * 
	 * @param policy
	 * @return
	 */
	public WeiboApiResponse execute(WeiboApiRequestPolicy policy) {
		return WeiboApiRequestPerformer.perform(this, policy);
	}

	/**
	 * 
	 * @param appKey
	 * @return
	 */
	public WeiboApiRequest appKey(String appKey) {
		return appKey("oauth_consumer_key", appKey);
	}

	/**
	 * 
	 * @param appKeyName
	 * @param appKey
	 * @return
	 */
	public WeiboApiRequest appKey(String appKeyName, String appKey) {
		param(appKeyName, appKey);
		return this;
	}

	/**
	 * 
	 * @param accessToken
	 * @return
	 */
	public WeiboApiRequest accessToken(String accessToken) {
		return accessToken("access_token", accessToken);
	}

	/**
	 * 
	 * @param accessTokenName
	 * @param accessToken
	 * @return
	 */
	public WeiboApiRequest accessToken(String accessTokenName, String accessToken) {
		param(accessTokenName, accessToken);
		return this;
	}

	/**
	 * 
	 * @param paramName
	 * @param paramValue
	 * @return
	 */
	public WeiboApiRequest param(String paramName, Object paramValue) {
		Preconditions.hasText(paramName);
		Preconditions.notNull(paramValue);

		if (isGetVerb()) {
			queryParams.put(paramName, paramValue);
			return this;
		}

		bodyParams.put(paramName, paramValue);
		return this;
	}

	/**
	 * 
	 * @param params
	 * @return
	 */
	public WeiboApiRequest params(Map<String, Object> params) {
		Preconditions.notNull(params);

		if (isGetVerb()) {
			queryParams.putAll(params);
			return this;
		}

		bodyParams.putAll(params);
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isGetVerb() {
		return getVerb() == WeiboApiRequestVerb.GET;
	}

	/**
	 * 
	 * @author loudyn
	 * 
	 */
	public static enum WeiboApiRequestVerb {
		GET, POST;
	}
}
