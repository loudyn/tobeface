package com.tobeface.tgenius.infrastructure.wapi.selector;

import java.util.List;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 * 
 */
class RoundRobinWeiboApiRequestSeletor extends AbstractWeiboApiRequestSelector {
	private int current = 0;

	/**
	 * 
	 * @param requests
	 */
	RoundRobinWeiboApiRequestSeletor(List<WeiboApiRequest> requests) {
		super(requests);
	}

	@Override
	protected WeiboApiRequest doSelect(List<WeiboApiRequest> requests) {
		return requests.get(current++ % requests.size());
	}
}
