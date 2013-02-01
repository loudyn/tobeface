package com.tobeface.etimes.domain.onsale.way.support;

import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.tobeface.etimes.domain.onsale.Goods;

/**
 * 
 * @author loudyn
 * 
 */
public class SpecGoodsShoppingWay extends GoodsShoppingWaySupport {

	private String goodsIds;

	/**
	 * 
	 * @param goodsIds
	 */
	public SpecGoodsShoppingWay(String goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getGoodsIds() {
		return goodsIds;
	}

	public Long[] getGoodsIdsAsLong() {

		Iterable<String> ids = Splitter.on(',').split(getGoodsIds());
		Iterable<Long> transform = Iterables.transform(ids, new Function<String, Long>() {

			public Long apply(String input) {
				return Long.parseLong(input);
			}
		});

		return Iterables.toArray(transform, Long.class);
	}

	public boolean isSatisfiedBy(Goods goods) {
		return false;
	}
}
