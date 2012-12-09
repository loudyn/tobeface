package com.tobeface.tgenius.infrastructure.wapi.policy;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequestPolicy;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;

/**
 * 
 * @author loudyn
 * 
 */
public abstract class AbstractWeiboApiRequestPolicy implements WeiboApiRequestPolicy {

	private final WeiboApiExceptionExplorer exceptionExplorer;

	/**
	 * 
	 * @param exceptionExplorer
	 */
	protected AbstractWeiboApiRequestPolicy(WeiboApiExceptionExplorer exceptionExplorer) {
		Preconditions.notNull(exceptionExplorer);
		this.exceptionExplorer = exceptionExplorer;
	}

	protected WeiboApiExceptionExplorer getExceptionExplorer() {
		return exceptionExplorer;
	}

	@Override
	public final WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp) {
		WeiboApiException ex = getExceptionExplorer().explore(preResp);
		return continueExecute(req, preResp, ex);
	}

	/**
	 * 
	 * @param req
	 * @param preResp
	 * @param ex
	 * @return
	 */
	protected abstract WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp, WeiboApiException ex);
}
