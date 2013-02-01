package com.tobeface.etimes.domain.onsale.strategy.support;

import java.math.BigDecimal;

import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public class MinusCashGoodsShoppingStrategy extends GoodsShoppingStrategySupport {

	private BigDecimal minusAmount;

	/**
	 * 
	 * @param minusAmount
	 * @param goodsShoppingWays
	 */
	public MinusCashGoodsShoppingStrategy(BigDecimal minusAmount, GoodsShoppingWay goodsShoppingWays) {
		super(goodsShoppingWays);
		this.minusAmount = minusAmount;
	}

	public BigDecimal getMinusAmount() {
		return minusAmount;
	}

	public int getSalience() {
		return GoodsShoppingStrategySaliences.MINUS_CASH.getSalience();
	}

	public int compareTo(GoodsShoppingStrategy o) {
		if (o.getClass() != getClass()) {
			return o.getSalience() - getSalience();
		}

		MinusCashGoodsShoppingStrategy another = (MinusCashGoodsShoppingStrategy) o;
		return another.getMinusAmount().compareTo(getMinusAmount());
	}
}
