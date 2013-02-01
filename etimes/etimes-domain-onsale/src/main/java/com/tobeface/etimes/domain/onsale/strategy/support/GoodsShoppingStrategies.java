package com.tobeface.etimes.domain.onsale.strategy.support;

import java.math.BigDecimal;
import java.util.List;

import com.tobeface.etimes.domain.onsale.GiftItem;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public final class GoodsShoppingStrategies {
	/**
	 * 
	 * @param minusAmount
	 * @param goodsShoppingWays
	 * @return
	 */
	public static GoodsShoppingStrategy newMinusCash(BigDecimal minusAmount, GoodsShoppingWay goodsShoppingWay) {
		return new MinusCashGoodsShoppingStrategy(minusAmount, goodsShoppingWay);
	}

	/**
	 * 
	 * @param discount
	 * @param goodsShoppingWay
	 * @return
	 */
	public static GoodsShoppingStrategy newDiscount(BigDecimal discount, GoodsShoppingWay goodsShoppingWay) {
		return new DiscountGoodsShoppingStrategy(discount, goodsShoppingWay);
	}

	/**
	 * 
	 * @param giftItems
	 * @param goodsShoppingWay
	 * @return
	 */
	public static GoodsShoppingStrategy newGiveGifts(List<GiftItem> giftItems, GoodsShoppingWay goodsShoppingWay) {
		return new GiveGiftsGoodsShoppingStrategy(giftItems, goodsShoppingWay);
	}

	private GoodsShoppingStrategies() {}
}
