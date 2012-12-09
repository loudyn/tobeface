package com.tobeface.tgenius.infrastructure.wapi.exception;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiException;


/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboApiExceptions {

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newAccessLimit() {
		return new AccessLimitWeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isAccessLimit(Exception e) {
		return e instanceof AccessLimitWeiboApiException;
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newBadCredentials() {
		return new BadCredentialsWeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isBadCredentials(Exception e) {
		return e instanceof BadCredentialsWeiboApiException;
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newBadParams() {
		return new BadParamsWeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isBadParams(Exception e) {
		return e instanceof BadParamsWeiboApiException;
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newServiceUnavaliable() {
		return new ServiceUnavaliableWeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isServiceUnavaliable(Exception e) {
		return e instanceof ServiceUnavaliableWeiboApiException;
	}
	
	private WeiboApiExceptions() {}
}
