package com.tobeface.tgenius.domain.appkey;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.DomainObj;
import com.tobeface.modules.domain.annotation.AggregateRoot;
import com.tobeface.modules.helper.JsonHelper;
import com.tobeface.tgenius.domain.WeiboPlatform;

/**
 * 
 * @author loudyn
 * 
 */
@AggregateRoot
public class WeiboAppKey implements DomainObj<WeiboAppKey>, Serializable {

	private WeiboPlatform platform;
	private String authorizationUrl;
	private String refreshAccessTokenUrl;
	private String callbackUrl;
	private String apiKey;
	private String apiSecret;
	private String accessToken;

	private String otherParams;

	private boolean enablePrivateLetter;

	public WeiboPlatform getPlatform() {
		return platform;
	}

	public void setPlatform(WeiboPlatform platform) {
		this.platform = platform;
	}

	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	public String getRefreshAccessTokenUrl() {
		return refreshAccessTokenUrl;
	}

	public void setRefreshAccessTokenUrl(String refreshAccessTokenUrl) {
		this.refreshAccessTokenUrl = refreshAccessTokenUrl;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOtherParams() {
		return otherParams;
	}

	public void setOtherParams(String otherParams) {
		this.otherParams = otherParams;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getOtherParamsAsMap() {
		return JsonHelper.fromJsonString(getOtherParams(), Map.class);
	}

	public boolean isEnablePrivateLetter() {
		return enablePrivateLetter;
	}

	public void setEnablePrivateLetter(boolean enablePrivateLetter) {
		this.enablePrivateLetter = enablePrivateLetter;
	}

	@Override
	public boolean sameIdentityAs(WeiboAppKey other) {
		return new EqualsBuilder().append(getApiKey(), other.getApiKey()).isEquals();
	}

	private static final long serialVersionUID = 1L;
}
