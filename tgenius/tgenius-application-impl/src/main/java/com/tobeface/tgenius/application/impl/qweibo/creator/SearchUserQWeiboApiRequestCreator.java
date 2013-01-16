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
class SearchUserQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final String keyword;
	private final int page;

	/**
	 * 
	 * @param appKey
	 * @param keyword
	 * @param page
	 */
	SearchUserQWeiboApiRequestCreator(WeiboAppKey appKey, String keyword, int page) {
		super(appKey);

		Preconditions.hasText(keyword);
		Preconditions.isTrue(page > 0);
		this.keyword = keyword;
		this.page = page;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("keyword", keyword);
		req.param("pagesize", 15).param("page", page);
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.GET;
	}

	@Override
	protected String getUrl() {
		return "http://open.t.qq.com/api/search/user";
	}
}
