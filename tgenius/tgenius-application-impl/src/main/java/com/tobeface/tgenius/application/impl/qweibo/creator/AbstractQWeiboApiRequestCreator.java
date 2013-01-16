package com.tobeface.tgenius.application.impl.qweibo.creator;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest.WeiboApiRequestVerb;

/**
 * 
 * @author loudyn
 * 
 */
public abstract class AbstractQWeiboApiRequestCreator {

	private final WeiboAppKey appKey;

	/**
	 * 
	 * @param appKey
	 */
	protected AbstractQWeiboApiRequestCreator(WeiboAppKey appKey) {
		Preconditions.notNull(appKey);
		this.appKey = appKey;
	}

	public WeiboApiRequest create() {
		WeiboApiRequest req = new WeiboApiRequest(getUrl(), getVerb());
		initAppKey(req);
		initParams(req);
		return req;
	}

	protected WeiboAppKey getAppKey() {
		return appKey;
	}

	protected void initAppKey(WeiboApiRequest req) {
		req.appKey(getAppKey().getApiKey()).accessToken(getAppKey().getAccessToken()).params(getAppKey().getOtherParamsAsMap());
	}

	/**
	 * 
	 * @param req
	 */
	protected void initParams(WeiboApiRequest req) {}

	/**
	 * 
	 * @return
	 */
	protected abstract WeiboApiRequestVerb getVerb();

	/**
	 * 
	 * @return
	 */
	protected abstract String getUrl();
}
