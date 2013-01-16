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
class SearchUserByTagsQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final String tags;
	private final int page;

	SearchUserByTagsQWeiboApiRequestCreator(WeiboAppKey appKey, String tags, int page) {
		super(appKey);

		Preconditions.hasText(tags);
		Preconditions.isTrue(page > 0);
		
		this.tags = tags;
		this.page = page;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("keyword", tags);
		req.param("pagesize", 15).param("page", page);
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.GET;
	}

	@Override
	protected String getUrl() {
		return "http://open.t.qq.com/api/search/userbytag";
	}
}
