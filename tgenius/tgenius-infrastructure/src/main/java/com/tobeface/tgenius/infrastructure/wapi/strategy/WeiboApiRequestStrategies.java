package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboApiRequestStrategies {

	/**
	 * 
	 * @return
	 */
	public static WeiboApiRequestStrategy newNOP() {
		return new NOPWeiboApiRequestStrategy();
	}

	/**
	 * 
	 * @param explorer
	 * @return
	 */
	public static WeiboApiRequestStrategy newFailFast(WeiboApiExceptionExplorer explorer) {
		return new FailFastWeiboApiRequestStrategy(explorer);
	}

	/**
	 * 
	 * @param explorer
	 * @return
	 */
	public static WeiboApiRequestStrategy newFailSafe(WeiboApiExceptionExplorer explorer) {
		return new FailSafeWeiboApiRequestStrategy(explorer);
	}

	/**
	 * 
	 * @param explorer
	 * @param sleepTime
	 * @return
	 */
	public static WeiboApiRequestStrategy newSleepAndRetry(WeiboApiExceptionExplorer explorer) {
		return new SleepAndRetryWeiboApiRequestStrategy(explorer);
	}

	/**
	 * 
	 * @param explorer
	 * @param sleepTime
	 * @return
	 */
	public static WeiboApiRequestStrategy newSleepAndRetry(WeiboApiExceptionExplorer explorer, int maxRetryTimes) {
		return new SleepAndRetryWeiboApiRequestStrategy(explorer, maxRetryTimes);
	}

	/**
	 * 
	 * @param explorer
	 * @param maxRetryTimes
	 * @param sleepTime
	 * @return
	 */
	public static WeiboApiRequestStrategy newSleepAndRetry(WeiboApiExceptionExplorer explorer, int maxRetryTimes, long sleepTime) {
		return new SleepAndRetryWeiboApiRequestStrategy(explorer, maxRetryTimes, sleepTime);
	}

	private WeiboApiRequestStrategies() {}
}
