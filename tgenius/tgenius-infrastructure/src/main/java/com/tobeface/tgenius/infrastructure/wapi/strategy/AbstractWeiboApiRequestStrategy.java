package com.tobeface.tgenius.infrastructure.wapi.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;

/**
 * 
 * @author loudyn
 * 
 */
public abstract class AbstractWeiboApiRequestStrategy implements WeiboApiRequestStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final WeiboApiExceptionExplorer exceptionExplorer;

	/**
	 * 
	 * @param exceptionExplorer
	 */
	protected AbstractWeiboApiRequestStrategy(WeiboApiExceptionExplorer exceptionExplorer) {
		Preconditions.notNull(exceptionExplorer);
		this.exceptionExplorer = exceptionExplorer;
	}

	protected Logger getLogger() {
		return logger;
	}

	protected WeiboApiExceptionExplorer getExceptionExplorer() {
		return exceptionExplorer;
	}

	@Override
	public final WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse prevResp) {
		WeiboApiException ex = getExceptionExplorer().explore(prevResp);
		return continueExecute(req, prevResp, ex);
	}

	/**
	 * 
	 * @param req
	 * @param prevResp
	 * @param ex
	 * @return
	 */
	protected abstract WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse prevResp, WeiboApiException ex);
}
