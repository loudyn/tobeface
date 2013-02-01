package com.tobeface.etimes.domain.onsale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
public class GoodsShoppingPromotion {

	private long startTime = -1;
	private long endTime = Long.MAX_VALUE;
	private List<GoodsShoppingStrategy> goodsShoppingStrategies = new ArrayList<GoodsShoppingStrategy>(0);

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public List<GoodsShoppingStrategy> getGoodsShoppingStrategies() {
		return null == goodsShoppingStrategies ? Collections.<GoodsShoppingStrategy> emptyList() : goodsShoppingStrategies;
	}

	public void setGoodsShoppingStrategies(List<GoodsShoppingStrategy> goodsShoppingStrategies) {
		this.goodsShoppingStrategies = goodsShoppingStrategies;
	}

	/**
	 * 
	 * @return
	 */
	public List<GoodsShoppingWay> getGoodsShoppingWays() {
		return Lists.transform(getGoodsShoppingStrategies(), new Function<GoodsShoppingStrategy, GoodsShoppingWay>() {

			public GoodsShoppingWay apply(GoodsShoppingStrategy input) {
				return null == input ? null : input.getGoodsShoppingWay();
			}
		});
	}

	/**
	 * 
	 * @param goodsShoppingList
	 */
	public void promote(GoodsShoppingList goodsShoppingList) {

		// check time? startTime <= now <= endTime

		Iterator<GoodsShoppingStrategy> it = getSortedGoodsShoppingStrategies().iterator();
		GoodsShoppingStrategy prev = null;

		while (it.hasNext()) {

			GoodsShoppingStrategy next = it.next();

			// [StrategyA,StrategyA,StrategyB,StrategyB].
			if (isGoodsShoppingStrategySkippable(goodsShoppingList, prev, next)) {
				continue;
			}

			// remerber create Memo ! it can be used on decide skip the strategy or not.
			goodsShoppingList.createMemo();
			next.onSale(goodsShoppingList);
			prev = next;
		}
	}

	private List<GoodsShoppingStrategy> getSortedGoodsShoppingStrategies() {
		List<GoodsShoppingStrategy> strategies = getGoodsShoppingStrategies();
		Collections.sort(strategies);
		return strategies;
	}

	private boolean isGoodsShoppingStrategySkippable(GoodsShoppingList goodsShoppingList,
														GoodsShoppingStrategy prev,
														GoodsShoppingStrategy next) {

		// first strategy
		if (null == prev) {
			return false;
		}

		// [StrategyA, StrategyB]
		if (prev.getClass() != next.getClass()) {
			return false;
		}

		// now is [StrategyA, StrategyA]
		MemoGoodsShoppingList memo = goodsShoppingList.getMemo();
		return !(memo.sameAmountAs(goodsShoppingList) && memo.sameGiftItemsAs(goodsShoppingList));
	}
}
