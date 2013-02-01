package com.tobeface.etimes.domain.onsale.way.support;

import java.math.BigDecimal;

import com.tobeface.etimes.domain.onsale.Goods;

/**
 * 
 * @author loudyn
 * 
 */
public class SpecAmountGoodsShoppingWay extends GoodsShoppingWaySupport {
	private BigDecimal specAmount;

	/**
	 * 
	 * @param specAmount
	 */
	public SpecAmountGoodsShoppingWay(BigDecimal specAmount) {
		this.specAmount = specAmount;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getSpecAmount() {
		return specAmount;
	}

	public boolean isSatisfiedBy(Goods goods) {
		return true;
	}
}
