package com.tobeface.etimes.domain.onsale.way.support;

import java.util.List;

import com.tobeface.etimes.domain.onsale.Goods;
import com.tobeface.etimes.domain.onsale.GoodsShoppingItem;

/**
 * 
 * @author loudyn
 * 
 */
public class GroupGoodsShoppingWay extends GoodsShoppingWaySupport {

	private List<GoodsShoppingItem> goodsShoppingItems;

	/**
	 * 
	 * @param goodsShoppingItems
	 */
	public GroupGoodsShoppingWay(List<GoodsShoppingItem> goodsShoppingItems) {
		this.goodsShoppingItems = goodsShoppingItems;
	}

	public List<GoodsShoppingItem> getGoodsShoppingItems() {
		return goodsShoppingItems;
	}

	public boolean isSatisfiedBy(Goods goods) {
		return false;
	}

}
