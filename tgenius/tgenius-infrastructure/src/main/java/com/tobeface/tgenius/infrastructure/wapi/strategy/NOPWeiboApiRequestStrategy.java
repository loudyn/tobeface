package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;

/**
 * 
 * @author loudyn
 * 
 */
final class NOPWeiboApiRequestStrategy implements WeiboApiRequestStrategy {

	@Override
	public WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp) {
		return preResp;
	}
}
