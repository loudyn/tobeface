package com.tobeface.etimes.domain.onsale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;

/**
 * 
 * @author loudyn
 * 
 */
public final class GoodsShoppingList {

	private Set<GoodsShoppingStrategy> satisfiedGoodsShoppingStrategies = new HashSet<GoodsShoppingStrategy>();
	private Set<GoodsShoppingItem> goodsShoppingItems = new HashSet<GoodsShoppingItem>();

	private List<GiftItem> giftItems = new ArrayList<GiftItem>();
	private BigDecimal amount = new BigDecimal("0.0");

	private transient MemoGoodsShoppingList memo;

	public Set<GoodsShoppingStrategy> getSatisfiedGoodsShoppingStrategies() {
		return satisfiedGoodsShoppingStrategies;
	}

	public GoodsShoppingList addSatisfiedGoodsShoppingStrategy(GoodsShoppingStrategy goodsShoppingStrategy) {
		this.satisfiedGoodsShoppingStrategies.add(goodsShoppingStrategy);
		return this;
	}

	public Set<GoodsShoppingItem> getGoodsShoppingItems() {
		return goodsShoppingItems;
	}

	public GoodsShoppingList addGoodsShoppingItem(Goods goods, int quantity) {
		this.goodsShoppingItems.add(new GoodsShoppingItem(goods, quantity));
		return this;
	}

	public GoodsShoppingList addGoodsShoppingItem(GoodsShoppingItem goodsShoppingItem) {
		this.goodsShoppingItems.add(goodsShoppingItem);
		return this;
	}

	public GoodsShoppingList addGoodsShoppingItems(Collection<GoodsShoppingItem> goodsShoppingItems) {
		for (GoodsShoppingItem item : goodsShoppingItems) {
			addGoodsShoppingItem(item);
		}
		
		return this;
	}

	public List<GiftItem> getGiftItems() {
		return giftItems;
	}

	public GoodsShoppingList addGiftItem(GiftItem giftItem) {
		this.giftItems.add(giftItem);
		return this;
	}

	public GoodsShoppingList addGiftItems(Collection<GiftItem> giftItems) {
		this.giftItems.addAll(giftItems);
		return this;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public GoodsShoppingList substractAmount(BigDecimal amount) {
		if (this.amount.compareTo(amount) < 0) {
			// throw here
		}

		this.amount = this.amount.subtract(amount);
		return this;
	}

	public GoodsShoppingList addAmount(BigDecimal amount) {
		this.amount = this.amount.add(amount);
		return this;
	}

	public GoodsShoppingList divideAmount(BigDecimal amount) {
		this.amount = this.amount.divide(amount);
		return this;
	}

	public GoodsShoppingList multiplyAmount(BigDecimal amount) {
		this.amount = this.amount.multiply(amount);
		return this;
	}

	public GoodsShoppingList createMemo() {
		this.memo = new MemoGoodsShoppingList(getAmount(), getGiftItems());
		return this;
	}

	public MemoGoodsShoppingList getMemo() {
		return memo;
	}
}
