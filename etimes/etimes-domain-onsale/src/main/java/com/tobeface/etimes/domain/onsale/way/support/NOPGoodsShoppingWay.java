package com.tobeface.etimes.domain.onsale.way.support;

import com.tobeface.etimes.domain.onsale.Goods;
import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
final class NOPGoodsShoppingWay implements GoodsShoppingWay {

	public boolean isSatisfiedBy(Goods goods) {
		return false;
	}

	public void apply(GoodsShoppingStrategy goodsShoppingStrategy, GoodsShoppingList goodsShoppingList) {}
}
