package com.tobeface.tgenius.infrastructure.wapi.policy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestPolicy;

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
	public static WeiboApiRequestPolicy newNop() {
		return new NopWeiboApiRequestPolicy();
	}

	/**
	 * 
	 * @param translator
	 * @return
	 */
	public static WeiboApiRequestPolicy newFastFail(WeiboApiExceptionExplorer translator) {
		return new FastFailWeiboApiRequestPolicy(translator);
	}

	/**
	 * 
	 * @param translator
	 * @param sleepTime
	 * @return
	 */
	public static WeiboApiRequestPolicy newSleepAndRetry(WeiboApiExceptionExplorer translator, long sleepTime) {
		return new SleepAndRetryWeiboApiRequestPolicy(sleepTime, translator);
	}

	private WeiboApiRequestPolicies() {}
}
