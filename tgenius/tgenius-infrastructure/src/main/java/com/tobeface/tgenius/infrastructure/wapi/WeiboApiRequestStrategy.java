package com.tobeface.tgenius.infrastructure.wapi;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiRequestStrategy {

	/**
	 * 
	 * @param req
	 * @param preResp
	 * @return
	 */
	WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp);
}
