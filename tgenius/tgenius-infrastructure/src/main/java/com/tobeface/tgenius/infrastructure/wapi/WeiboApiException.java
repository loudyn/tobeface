package com.tobeface.tgenius.infrastructure.wapi;

/**
 * 
 * @author loudyn
 * 
 */
@SuppressWarnings("serial")
public class WeiboApiException extends RuntimeException {

	public WeiboApiException() {}

	public WeiboApiException(String msg) {
		super(msg);
	}

	public WeiboApiException(Throwable e) {
		super(e);
	}

	public WeiboApiException(String msg, Throwable e) {
		super(msg, e);
	}
}
