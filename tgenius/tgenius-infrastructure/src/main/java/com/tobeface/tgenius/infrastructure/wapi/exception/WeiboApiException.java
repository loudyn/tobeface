package com.tobeface.tgenius.infrastructure.wapi.exception;

/**
 * 
 * @author loudyn
 * 
 */
@SuppressWarnings("serial")
public class WeiboApiException extends RuntimeException {

	public WeiboApiException() {}

	/**
	 * 
	 * @param msg
	 */
	public WeiboApiException(String msg) {
		super(msg);
	}

	/**
	 * 
	 * @param e
	 */
	public WeiboApiException(Throwable e) {
		super(e);
	}

	/**
	 * 
	 * @param msg
	 * @param e
	 */
	public WeiboApiException(String msg, Throwable e) {
		super(msg, e);
	}
}
