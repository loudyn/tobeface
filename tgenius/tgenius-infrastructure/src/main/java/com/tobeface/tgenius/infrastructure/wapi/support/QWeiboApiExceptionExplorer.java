package com.tobeface.tgenius.infrastructure.wapi.support;

import org.springframework.stereotype.Component;

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
@Component("qweibo-api-exception-explorer")
public class QWeiboApiExceptionExplorer implements WeiboApiExceptionExplorer {

	@Override
	public WeiboApiException explore(WeiboApiResponse response) {
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
		default:
			return WeiboApiExceptions.newServiceUnavaliable();
		}
	}

	@Override
	public String getErrors(WeiboApiResponse response) {
		return "errorcode[" + response.getResult().on("errcode").get() + "],errormsg[" + response.getResult().on("msg").get() + "]";
	}

}
