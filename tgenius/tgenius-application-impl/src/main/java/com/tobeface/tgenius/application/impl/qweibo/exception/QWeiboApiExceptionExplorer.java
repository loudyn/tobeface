package com.tobeface.tgenius.application.impl.qweibo.exception;

import org.springframework.stereotype.Component;

import com.tobeface.modules.lang.Preconditions;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse;
import com.tobeface.tgenius.infrastructure.wapi.WeiboApiResponse.WeiboApiResponseResult;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiException;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptionExplorer;
import com.tobeface.tgenius.infrastructure.wapi.exception.WeiboApiExceptions;

/**
 * 
 * @author loudyn
 * 
 */
@Component(QWeiboApiExceptionExplorer.BEAN_NAME)
public class QWeiboApiExceptionExplorer implements WeiboApiExceptionExplorer {

	public static final String BEAN_NAME = "qweiboApiExceptionExplorer";

	@Override
	public WeiboApiException explore(WeiboApiResponse response) {

		Preconditions.notNull(response);

		WeiboApiResponseResult result = response.getResult();
		int ret = (Integer) result.on("ret").get();
		switch (ret) {
		case 0:
			return null;
		case 2:
			return WeiboApiExceptions.newAccessLimit();
		case 1:
			return WeiboApiExceptions.newBadParams();
		case 3:
			return WeiboApiExceptions.newBadCredentials();
		case 4:
			return WeiboApiExceptions.newInternalServiceUnavaliable();
		default:
			return WeiboApiExceptions.newWeiboApiException();
		}
	}

	@Override
	public String getErrors(WeiboApiResponse response) {
		return "errorcode[" + response.getResult().on("errcode").get() + "],errormsg[" + response.getResult().on("msg").get() + "]";
	}

}
