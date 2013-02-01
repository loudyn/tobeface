package com.tobeface.etimes.domain.onsale;

/**
 * 
 * @author loudyn
 * 
 */
public final class GoodsShoppingItem {

	private final Goods goods;
	private final int quantity;

	/**
	 * 
	 * @param goods
	 * @param quantity
	 */
	public GoodsShoppingItem(Goods goods, int quantity) {
		this.goods = goods;
		this.quantity = quantity;
	}

	public Goods getGoods() {
		return goods;
	}

	public int getQuantity() {
		return quantity;
	}
}
