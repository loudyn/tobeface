package com.tobeface.tgenius.infrastructure.wapi.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;

/**
 * 
 * @author loudyn
 * 
 */
final class FastFailWeiboApiRequestStrategy extends AbstractWeiboApiRequestStrategy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	FastFailWeiboApiRequestStrategy(WeiboApiExceptionExplorer exceptionTranslator) {
		super(exceptionTranslator);
	}

	@Override
	protected WeiboApiResponse continueExecute(WeiboApiRequest req, WeiboApiResponse preResp, WeiboApiException ex) {
		if (null != ex) {
			logger.error(getExceptionExplorer().getErrors(preResp));
			throw ex;
		}

		return preResp;
	}

}
