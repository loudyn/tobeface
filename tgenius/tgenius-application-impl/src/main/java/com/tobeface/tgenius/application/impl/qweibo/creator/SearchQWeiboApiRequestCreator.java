package com.tobeface.tgenius.application.impl.qweibo.creator;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.domain.WeiboTalking;
import com.tobeface.tgenius.domain.appkey.WeiboAppKey;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest.WeiboApiRequestVerb;

/**
 * 
 * @author loudyn
 * 
 */
class SearchQWeiboApiRequestCreator extends AbstractQWeiboApiRequestCreator {

	private final WeiboTalking talking;
	private final int page;

	/**
	 * 
	 * @param appKey
	 * @param talking
	 * @param page
	 */
	SearchQWeiboApiRequestCreator(WeiboAppKey appKey, WeiboTalking talking, int page) {
		super(appKey);

		Preconditions.notNull(talking);
		Preconditions.isTrue(page > 0);
		this.talking = talking;
		this.page = page;
	}

	@Override
	protected void initParams(WeiboApiRequest req) {
		req.param("format", "json").param("keyword", talking.getKeyword()).param("pageSize", 20);
		req.param("page", page).param("contenttype", 0).param("sorttype", 0).param("msgtype", 0).param("searchtype", 0);

		if (talking.hasAvaliableLocation()) {
			req.param("longitude", talking.getLocLongitude());
			req.param("latitude", talking.getLocLatitude());
			req.param("radius", talking.getRadius());
		}

		if (talking.hasAvaliableTalkingTime()) {
			req.param("starttime", talking.getTalkingStartTime()).param("endtime", talking.getTalkingEndTime());
		}
	}

	@Override
	protected WeiboApiRequestVerb getVerb() {
		return WeiboApiRequestVerb.GET;
	}

	@Override
	protected String getUrl() {
		return "http://open.t.qq.com/api/search/t";
	}
}
