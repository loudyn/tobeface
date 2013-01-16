package com.tobeface.tgenius.infrastructure.wapi.strategy;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;

/**
 * 
 * @author loudyn
 * 
 */
final class FailSafeWeiboApiRequestStrategy extends AbstractWeiboApiRequestStrategy {

	FailSafeWeiboApiRequestStrategy(WeiboApiExceptionExplorer exceptionTranslator) {
		super(exceptionTranslator);
	}

	@Override
	protected WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse prevResp, WeiboApiException ex) {
		if (null != ex) {
			getLogger().error(getExceptionExplorer().getErrors(prevResp));
		}

		return prevResp;
	}

}
