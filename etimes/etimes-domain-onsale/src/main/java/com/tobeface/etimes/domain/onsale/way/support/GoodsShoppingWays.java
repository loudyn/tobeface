package com.tobeface.etimes.domain.onsale.way.support;

import java.math.BigDecimal;

import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public final class GoodsShoppingWays {

	/**
	 * 
	 * @return
	 */
	public static GoodsShoppingWay newNOP() {
		return new NOPGoodsShoppingWay();
	}

	/**
	 * 
	 * @param specifiedAmount
	 * @return
	 */
	public static GoodsShoppingWay newSpecAmount(BigDecimal specifiedAmount) {
		return new SpecAmountGoodsShoppingWay(specifiedAmount);
	}

	private GoodsShoppingWays() {}
}
