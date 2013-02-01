package com.tobeface.etimes.domain.onsale;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author loudyn
 * 
 */
public final class MemoGoodsShoppingList {
	private final BigDecimal amount;
	private final List<GiftItem> giftItems;

	/**
	 * 
	 * @param amount
	 * @param giftItems
	 */
	public MemoGoodsShoppingList(BigDecimal amount, List<GiftItem> giftItems) {
		this.amount = amount;
		this.giftItems = giftItems;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public boolean sameAmountAs(GoodsShoppingList goodsShoppingList) {
		return getAmount().compareTo(goodsShoppingList.getAmount()) == 0;
	}

	public List<GiftItem> getGiftItems() {
		return giftItems;
	}

	public boolean sameGiftItemsAs(GoodsShoppingList goodsShoppingList) {
		Set<GiftItem> me = new HashSet<GiftItem>(getGiftItems());
		Set<GiftItem> he = new HashSet<GiftItem>(goodsShoppingList.getGiftItems());
		he.removeAll(me);
		return he.isEmpty();
	}
}
