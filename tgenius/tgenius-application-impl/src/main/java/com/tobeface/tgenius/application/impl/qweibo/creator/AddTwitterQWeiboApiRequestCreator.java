package com.tobeface.tgenius.application.impl.qweibo.creator;

import com.tobeface.modules.lang.IPs;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest.WeiboApiRequestVerb;

/**
 * 
 * @author loudyn
 * 
 */
class AddTwitterQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final String content;

	/**
	 * 
	 * @param appKey
	 * @param content
	 */
	AddTwitterQWeiboApiRequestCreator(WeiboAppKey appKey, String content) {
		super(appKey);

		Preconditions.hasText(content);
		this.content = content;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("content", content).param("clientip", IPs.nextIp());
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.POST;
	}

	@Override
	protected String getUrl() {
		return "https://open.t.qq.com/api/t/add";
	}

}
