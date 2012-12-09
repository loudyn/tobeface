package com.tobeface.tgenius.infrastructure.wapi;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiRequestPolicy {

	/**
	 * 
	 * @param req
	 * @param preResp
	 * @return
	 */
	WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp);
}
