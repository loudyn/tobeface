package com.tobeface.tgenius.infrastructure.wapi.selector;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 * 
 */
public interface WeiboApiRequestSeletor {

	/**
	 * 
	 * @return
	 */
	public WeiboApiRequest select();
}
