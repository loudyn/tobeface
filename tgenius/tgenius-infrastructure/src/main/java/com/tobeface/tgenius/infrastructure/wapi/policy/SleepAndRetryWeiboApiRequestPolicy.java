package com.tobeface.tgenius.infrastructure.wapi.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.modules.lang.Lang;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptions;

/**
 * 
 * @author loudyn
 * 
 */
class SleepAndRetryWeiboApiRequestPolicy extends AbstractWeiboApiRequestPolicy {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final long sleepTime;

	/**
	 * 
	 * @param sleepTime
	 */
	public SleepAndRetryWeiboApiRequestPolicy(long sleepTime, WeiboApiExceptionExplorer translator) {
		super(translator);
		Preconditions.isTrue(sleepTime > 0);
		this.sleepTime = sleepTime;
	}

	@Override
	protected WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp, WeiboApiException ex) {
		if (WeiboApiExceptions.isAccessLimit(ex)) {
			logger.warn("Access rate limit,sleep {} {} and retry again", sleepTime, "millsecond");
			Lang.sleepQuietly(sleepTime);
			return req.execute(this);
		}

		if (null != ex) {
			throw ex;
		}

		return preResp;
	}
}
