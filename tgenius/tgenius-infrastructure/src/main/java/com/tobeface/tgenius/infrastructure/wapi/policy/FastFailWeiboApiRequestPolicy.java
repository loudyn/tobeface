package com.tobeface.tgenius.infrastructure.wapi.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;

/**
 * 
 * @author loudyn
 * 
 */
final class FastFailWeiboApiRequestPolicy extends AbstractWeiboApiRequestPolicy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	FastFailWeiboApiRequestPolicy(WeiboApiExceptionExplorer exceptionTranslator) {
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
