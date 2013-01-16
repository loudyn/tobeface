package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.lang.annotation.NotThreadSafe;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptions;

/**
 * 
 * @author loudyn
 * 
 */
@NotThreadSafe
final class SleepAndRetryWeiboApiRequestStrategy extends AbstractWeiboApiRequestStrategy {

	private static final int DEFAULT_MAX_RETRY_TIME = 3;
	private static final long DEFAULT_SLEEP_TIME = 3000;

	private final int maxRetryTimes;
	private final long sleepTime;

	private int retryTimes = 0;

	/**
	 * 
	 * @param sleepTime
	 */
	SleepAndRetryWeiboApiRequestStrategy(WeiboApiExceptionExplorer explorer) {
		this(explorer, DEFAULT_MAX_RETRY_TIME);
	}

	/**
	 * 
	 * @param sleepTime
	 */
	SleepAndRetryWeiboApiRequestStrategy(WeiboApiExceptionExplorer explorer, int maxRetryTimes) {
		this(explorer, DEFAULT_MAX_RETRY_TIME, DEFAULT_SLEEP_TIME);
	}

	SleepAndRetryWeiboApiRequestStrategy(WeiboApiExceptionExplorer explorer, int maxRetryTimes, long sleepTime) {
		super(explorer);

		Preconditions.isTrue(maxRetryTimes > 0 && maxRetryTimes < 99);
		Preconditions.isTrue(sleepTime > 0);
		this.maxRetryTimes = maxRetryTimes;
		this.sleepTime = sleepTime;
	}

	@Override
	protected WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse prevResp, WeiboApiException ex) {
		if (null == ex) {
			return prevResp;
		}

		if (WeiboApiExceptions.isAccessLimit(ex) && canRetryAgain()) {
			getLogger().warn(
								"Access rate limit,sleep {} {} and retry again,retry {} times yet",
								new Object[] { sleepTime, "millsecond", retryTimes - 1 }
					);
			Lang.sleepQuietly(sleepTime);
			return req.execute(this);
		}

		throw ex;
	}

	private boolean canRetryAgain() {
		return ++retryTimes <= maxRetryTimes;
	}
}
