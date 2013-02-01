package com.tobeface.etimes.domain.onsale.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tobeface.etimes.domain.onsale.GoodsShoppingPromotion;
import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.strategy.support.GoodsShoppingStrategies;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;
import com.tobeface.etimes.domain.onsale.way.support.GoodsShoppingWays;

public class MinusCashGoodsShoppingStrategyTest {

	@Test
	public void testSpecifiedAmount() {

		GoodsShoppingWay specAmount = GoodsShoppingWays.newSpecAmount(new BigDecimal("300"));
		GoodsShoppingStrategy strategy = GoodsShoppingStrategies.newMinusCash(new BigDecimal("80"), specAmount);
		GoodsShoppingWay specAmount2 = GoodsShoppingWays.newSpecAmount(new BigDecimal("200"));
		GoodsShoppingStrategy strategy2 = GoodsShoppingStrategies.newMinusCash(new BigDecimal("50"), specAmount2);
		GoodsShoppingList goodsShoppingList = new GoodsShoppingList().addAmount(new BigDecimal("320"));
		GoodsShoppingList goodsShoppingList2 = new GoodsShoppingList().addAmount(new BigDecimal("210"));
		
		List<GoodsShoppingStrategy> strategies = new ArrayList<GoodsShoppingStrategy>();
		strategies.add(strategy);
		strategies.add(strategy2);
		
		GoodsShoppingPromotion promotion = new GoodsShoppingPromotion();
		promotion.setGoodsShoppingStrategies(strategies);
		promotion.promote(goodsShoppingList);
		promotion.promote(goodsShoppingList2);
	}
}
