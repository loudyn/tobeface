package com.tobeface.etimes.domain.onsale.way.support;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

import com.tobeface.etimes.domain.onsale.GoodsShoppingList;
import com.tobeface.etimes.domain.onsale.strategy.GoodsShoppingStrategy;
import com.tobeface.etimes.domain.onsale.way.GoodsShoppingWay;

/**
 * 
 * @author loudyn
 * 
 */
abstract class GoodsShoppingWaySupport implements GoodsShoppingWay {

	private static final ConcurrentMap<Class<?>, KnowledgeBase> kbaseSeen = new ConcurrentHashMap<Class<?>, KnowledgeBase>();

	public final void apply(GoodsShoppingStrategy goodsShoppingStrategy, GoodsShoppingList goodsShoppingList) {

		try {

			if (!applyBefore(goodsShoppingStrategy, goodsShoppingList)) {
				return;
			}

			StatelessKnowledgeSession session = getKnowledgeBase(getClass()).newStatelessKnowledgeSession();
			session.execute(Arrays.asList(this, goodsShoppingStrategy, goodsShoppingList));
		} finally {
			applyAfter(goodsShoppingStrategy, goodsShoppingList);
		}

	}

	private KnowledgeBase getKnowledgeBase(Class<? extends GoodsShoppingWaySupport> clazz) {

		KnowledgeBase knowledgeBase = kbaseSeen.get(clazz);
		if (null != knowledgeBase) {
			return knowledgeBase;
		}

		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(ResourceFactory.newClassPathResource(getDrl(), getClass()), ResourceType.DRL);
		if (builder.hasErrors()) {
			// throw here
			throw new RuntimeException(builder.getErrors().toString());
		}

		knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
		kbaseSeen.putIfAbsent(clazz, knowledgeBase);
		return knowledgeBase;
	}

	private String getDrl() {
		return getClass().getSimpleName() + ".drl";
	}

	/**
	 * 
	 * @param goodsShoppingStrategy
	 * @param goodsShoppingList
	 * @return
	 */
	protected boolean applyBefore(GoodsShoppingStrategy goodsShoppingStrategy, GoodsShoppingList goodsShoppingList) {
		return true;
	}

	/**
	 * 
	 * @param goodsShoppingStrategy
	 * @param goodsShoppingList
	 */
	protected void applyAfter(GoodsShoppingStrategy goodsShoppingStrategy, GoodsShoppingList goodsShoppingList) {}
}
