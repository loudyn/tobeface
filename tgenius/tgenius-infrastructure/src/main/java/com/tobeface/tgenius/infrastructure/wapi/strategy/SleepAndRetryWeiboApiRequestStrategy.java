package com.tobeface.tgenius.infrastructure.wapi.strategy;

import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;
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

	private Logger logger = LoggerFactory.getLogger(getClass());
	private final int maxRetryTimes;
	private final long sleepTime;

	private int retryTimes = 0;

	/**
	 * 
	 * @param sleepTime
	 */
	SleepAndRetryWeiboApiRequestStrategy(WeiboApiExceptionExplorer explorer, long sleepTime) {
		this(explorer, 3, sleepTime);
	}

	SleepAndRetryWeiboApiRequestStrategy(WeiboApiExceptionExplorer explorer, int maxRetryTimes, long sleepTime) {
		super(explorer);

		Preconditions.isTrue(maxRetryTimes > 0 && maxRetryTimes < 99);
		Preconditions.isTrue(sleepTime > 0);
		this.maxRetryTimes = maxRetryTimes;
		this.sleepTime = sleepTime;
	}

	@Override
	protected WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp, WeiboApiException ex) {
		
		if (WeiboApiExceptions.isAccessLimit(ex) && canRetryAgain()) {
			logger.warn(
							"Access rate limit,sleep {} {} and retry again,retry {} times yet", 
							new Object[] { sleepTime, "millsecond", retryTimes - 1 }
						);
			
			Lang.sleepQuietly(sleepTime);
			return req.execute(this);
		}

		if (null != ex) {
			throw ex;
		}

		return preResp;
	}

	private boolean canRetryAgain() {
		return ++retryTimes <= maxRetryTimes;
	}
}
