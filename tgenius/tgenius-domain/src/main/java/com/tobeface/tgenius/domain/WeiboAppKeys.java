package com.tobeface.tgenius.domain;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.DomainObject;
import com.tobeface.modules.helper.JsonHelper;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboAppKeys implements DomainObject<WeiboAppKeys>, Serializable {
	private static final long serialVersionUID = 1L;

	private String authorizationUrl;
	private String refreshAccessTokenUrl;
	private String callbackUrl;
	private String apiKey;
	private String apiSecret;
	private String accessToken;
	private long expiresIn;

	private String otherParams;

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

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
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

	@Override
	public boolean sameIdentityAs(WeiboAppKeys other) {
		return new EqualsBuilder().append(getApiKey(), other.getApiKey()).isEquals();
	}
}
