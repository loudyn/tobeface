package com.tobeface.etimes.domain.onsale.way.support;

import java.math.BigDecimal;

import com.tobeface.etimes.domain.onsale.Goods;

/**
 * 
 * @author loudyn
 *
 */
public class SpecCategoryAndAmountGoodsShoppingWay extends GoodsShoppingWaySupport {

	private Long categoryId;
	private BigDecimal specAmount;

	/**
	 * 
	 * @param categoryId
	 * @param specAmount
	 */
	public SpecCategoryAndAmountGoodsShoppingWay(Long categoryId, BigDecimal specAmount) {
		this.categoryId = categoryId;
		this.specAmount = specAmount;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public BigDecimal getSpecAmount() {
		return specAmount;
	}

	public boolean isSatisfiedBy(Goods goods) {
		return false;
	}

}
