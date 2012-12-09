package com.tobeface.tgenius.infrastructure.wapi.policy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestPolicy;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;

/**
 * 
 * @author loudyn
 * 
 */
final class NopWeiboApiRequestPolicy implements WeiboApiRequestPolicy {

	@Override
	public WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp) {
		return preResp;
	}
}
