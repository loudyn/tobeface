package com.tobeface.etimes.domain.onsale.way;

import com.tobeface.etimes.domain.onsale.Goods;
import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;

/**
 * 
 * @author loudyn
 * 
 */
public interface GoodsShoppingWay {

	/**
	 * 
	 * @param goods
	 * @return
	 */
	boolean isSatisfiedBy(Goods goods);

	/**
	 * 
	 * @param goodsShoppingStrategy
	 * @param goodsShoppingList
	 */
	void apply(GoodsShoppingStrategy goodsShoppingStrategy, GoodsShoppingList goodsShoppingList);
}
