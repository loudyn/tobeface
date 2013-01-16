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
class UserInfoQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final String name;

	/**
	 * 
	 * @param appKey
	 * @param name
	 */
	UserInfoQWeiboApiRequestCreator(WeiboAppKey appKey, String name) {
		super(appKey);

		Preconditions.hasText(name);
		this.name = name;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("name", name);
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.GET;
	}

	@Override
	protected String getUrl() {
		return "https://open.t.qq.com/api/user/other_info";
	}
}
