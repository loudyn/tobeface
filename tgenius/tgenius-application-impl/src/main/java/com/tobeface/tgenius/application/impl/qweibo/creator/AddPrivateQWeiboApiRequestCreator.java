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
class AddPrivateQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final String which;
	private final String content;

	/**
	 * 
	 * @param appKey
	 */
	AddPrivateQWeiboApiRequestCreator(WeiboAppKey appKey, String which, String content) {
		super(appKey);

		Preconditions.hasText(which);
		Preconditions.hasText(content);

		this.which = which;
		this.content = content;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("content", content).param("name", which);
		req.param("clientip", IPs.nextIp()).param("contentflag", "1");
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.POST;
	}

	@Override
	protected String getUrl() {
		return "http://open.t.qq.com/api/private/add";
	}
}
