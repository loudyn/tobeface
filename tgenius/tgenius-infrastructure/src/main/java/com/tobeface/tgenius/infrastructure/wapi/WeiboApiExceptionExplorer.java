package com.tobeface.tgenius.infrastructure.wapi;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiExceptionExplorer {

	/**
	 * 
	 * @param response
	 * @return
	 */
	WeiboApiException explore(WeiboApiResponse response);

	/**
	 * 
	 * @param response
	 * @return
	 */
	String getErrors(WeiboApiResponse response);
}
