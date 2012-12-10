package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestStrategy;
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

	private WeiboApiRequestStrategies() {}
}
