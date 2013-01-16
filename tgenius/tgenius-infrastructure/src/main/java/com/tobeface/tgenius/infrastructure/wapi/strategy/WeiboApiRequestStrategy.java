package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;


/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiRequestStrategy {

	/**
	 * 
	 * @param req
	 * @param prevResp
	 * @return
	 */
	WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse prevResp);
}
