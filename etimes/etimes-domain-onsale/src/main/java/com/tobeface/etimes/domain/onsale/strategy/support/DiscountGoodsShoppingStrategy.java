package com.tobeface.etimes.domain.onsale.strategy.support;

import java.math.BigDecimal;

import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public class DiscountGoodsShoppingStrategy extends GoodsShoppingStrategySupport {
	private BigDecimal discount;

	/**
	 * 
	 * @param discount
	 * @param goodsShoppingWay
	 */
	public DiscountGoodsShoppingStrategy(BigDecimal discount, GoodsShoppingWay goodsShoppingWay) {
		super(goodsShoppingWay);
		this.discount = discount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public int getSalience() {
		return GoodsShoppingStrategySaliences.DISCOUNT.getSalience();
	}
	
	public int compareTo(GoodsShoppingStrategy o) {
		if (o.getClass() != getClass()) {
			return o.getSalience() - getSalience();
		}

		DiscountGoodsShoppingStrategy another = (DiscountGoodsShoppingStrategy) o;
		return getDiscount().compareTo(another.getDiscount());
	}

}
