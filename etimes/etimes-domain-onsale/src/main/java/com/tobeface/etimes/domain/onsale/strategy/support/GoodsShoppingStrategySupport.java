package com.tobeface.etimes.domain.onsale.strategy.support;

import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;
import com.tobeface.etimes.domain.onsale.way.support.GoodsShoppingWays;

/**
 * 
 * @author loudyn
 * 
 */
abstract class GoodsShoppingStrategySupport implements GoodsShoppingStrategy {

	private GoodsShoppingWay goodsShoppingWay;

	protected GoodsShoppingStrategySupport() {
		this(GoodsShoppingWays.newNOP());
	}

	/**
	 * 
	 * @param goodsShoppingWays
	 */
	protected GoodsShoppingStrategySupport(GoodsShoppingWay goodsShoppingWay) {
		this.goodsShoppingWay = goodsShoppingWay;
	}

	public GoodsShoppingWay getGoodsShoppingWay() {
		return goodsShoppingWay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tobeface.etimes.domain.onsale.GoodsShoppingStrategy#onSale(com.tobeface.etimes.domain.onsale.GoodsShoppingList
	 * )
	 */
	public final void onSale(GoodsShoppingList goodsShoppingList) {

		try {

			if (!beforeOnSale(goodsShoppingList)) {
				return;
			}

			getGoodsShoppingWay().apply(this, goodsShoppingList);
		} finally {
			afterOnSale(goodsShoppingList);
		}
	}

	/**
	 * 
	 * @param goodsShoppingList
	 * @return
	 */
	protected boolean beforeOnSale(GoodsShoppingList goodsShoppingList) {
		return true;
	}

	/**
	 * 
	 * @param goodsShoppingList
	 */
	protected void afterOnSale(GoodsShoppingList goodsShoppingList) {}
}
