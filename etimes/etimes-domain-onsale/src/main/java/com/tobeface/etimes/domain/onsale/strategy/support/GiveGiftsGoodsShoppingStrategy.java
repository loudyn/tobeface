package com.tobeface.etimes.domain.onsale.strategy.support;

import java.util.List;

import com.tobeface.etimes.domain.onsale.GiftItem;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public class GiveGiftsGoodsShoppingStrategy extends GoodsShoppingStrategySupport {
	private List<GiftItem> giftItems;

	/**
	 * 
	 * @param giftItems
	 * @param goodsShoppingWay
	 */
	public GiveGiftsGoodsShoppingStrategy(List<GiftItem> giftItems, GoodsShoppingWay goodsShoppingWay) {
		super(goodsShoppingWay);
		this.giftItems = giftItems;
	}

	public List<GiftItem> getGiftItems() {
		return giftItems;
	}

	public int getSalience() {
		return GoodsShoppingStrategySaliences.GIVE_GIFTS.getSalience();
	}

	public int compareTo(GoodsShoppingStrategy o) {
		if (o.getClass() != getClass()) {
			return o.getSalience() - getSalience();
		}

		GiveGiftsGoodsShoppingStrategy another = (GiveGiftsGoodsShoppingStrategy) o;
		return another.getGiftItems().size() - getGiftItems().size();
	}
}
