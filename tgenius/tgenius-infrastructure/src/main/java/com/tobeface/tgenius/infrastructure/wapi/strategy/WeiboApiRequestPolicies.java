package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestStrategy;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboApiRequestPolicies {

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
	public static WeiboApiRequestStrategy newFastFail(WeiboApiExceptionExplorer explorer) {
		return new FastFailWeiboApiRequestStrategy(explorer);
	}

	/**
	 * 
	 * @param explorer
	 * @param sleepTime
	 * @return
	 */
	public static WeiboApiRequestStrategy newSleepAndRetry(WeiboApiExceptionExplorer explorer, long sleepTime) {
		return new SleepAndRetryWeiboApiRequestStrategy(explorer, sleepTime);
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

	private WeiboApiRequestPolicies() {}
}
