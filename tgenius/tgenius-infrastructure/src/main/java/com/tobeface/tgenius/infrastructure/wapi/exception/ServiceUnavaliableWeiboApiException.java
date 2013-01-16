package com.tobeface.tgenius.infrastructure.wapi.exception;


/**
 * 
 * @author loudyn
 * 
 */
@SuppressWarnings("serial")
class ServiceUnavaliableWeiboApiException extends WeiboApiException {

	public ServiceUnavaliableWeiboApiException(Exception e) {
		super(e);
	}
}
