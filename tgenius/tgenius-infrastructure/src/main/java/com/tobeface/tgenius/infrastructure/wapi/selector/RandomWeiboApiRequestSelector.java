package com.tobeface.tgenius.infrastructure.wapi.selector;

import java.util.List;
import java.util.Random;

import com.tobeface.tgenius.infrastructure.wapi.WeiboApiRequest;

/**
 * 
 * @author loudyn
 *
 */
class RandomWeiboApiRequestSelector extends AbstractWeiboApiRequestSelector {

	private final Random random = new Random();

	/**
	 * 
	 * @param requests
	 */
	RandomWeiboApiRequestSelector(List<WeiboApiRequest> requests) {
		super(requests);
	}

	@Override
	protected WeiboApiRequest doSelect(List<WeiboApiRequest> requests) {
		return requests.get(random.nextInt(requests.size()));
	}
}
