package com.tobeface.tgenius.infrastructure.wapi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.tobeface.modules.helper.CodeHelper;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.strategy.WeiboApiRequestStrategies;
import com.tobeface.tgenius.infrastructure.wapi.strategy.WeiboApiRequestStrategy;

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
		return WeiboApiRequestPerformer.perform(this, WeiboApiRequestStrategies.newNOP());
	}

	/**
	 * 
	 * @param strategy
	 * @return
	 */
	public WeiboApiResponse execute(WeiboApiRequestStrategy strategy) {
		return WeiboApiRequestPerformer.perform(this, strategy);
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

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getUrl()).append(getParams()).append(getVerb()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (null == obj || getClass() != obj.getClass()) {
			return false;
		}

		WeiboApiRequest other = (WeiboApiRequest) obj;
		return new EqualsBuilder().append(getUrl(), other.getUrl())
									.append(getParams(), other.getParams())
									.append(getVerb(), other.getVerb()).isEquals();
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("url", getUrl()).add("verb", getVerb()).add("params", getParams()).toString();
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
