package com.tobeface.tgenius.infrastructure.wapi.exception;


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
	 * @param e
	 * @return
	 */
	public static WeiboApiException newServiceUnavaliable(Exception e) {
		return new ServiceUnavaliableWeiboApiException(e);
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isServiceUnavaliable(Exception e) {
		return e instanceof ServiceUnavaliableWeiboApiException;
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newInternalServiceUnavaliable() {
		return new InternalServiceUnavaliableWeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static boolean isInternalServiceUnavaliable(Exception e) {
		return e instanceof InternalServiceUnavaliableWeiboApiException;
	}

	/**
	 * 
	 * @return
	 */
	public static WeiboApiException newWeiboApiException() {
		return new WeiboApiException();
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static WeiboApiException newWeiboApiException(Exception e) {
		return new WeiboApiException(e);
	}

	private WeiboApiExceptions() {}

}
