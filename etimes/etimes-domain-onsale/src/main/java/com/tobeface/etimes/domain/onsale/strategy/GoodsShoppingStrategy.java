package com.tobeface.etimes.domain.onsale.strategy;

import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public interface GoodsShoppingStrategy extends Comparable<GoodsShoppingStrategy> {

	/**
	 * 
	 * @return
	 */
	int getSalience();

	/**
	 * 
	 * @param goodsShoppingList
	 */
	void onSale(GoodsShoppingList goodsShoppingList);

	/**
	 * 
	 * @return
	 */
	GoodsShoppingWay getGoodsShoppingWay();
}
