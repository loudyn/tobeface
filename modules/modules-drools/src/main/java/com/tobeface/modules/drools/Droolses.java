package com.tobeface.modules.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatelessKnowledgeSession;

public final class Droolses {

	public static StatelessKnowledgeSession newStateless(String path, ResourceType type) {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource(path, Droolses.class), type);

		if (kbuilder.hasErrors()) {
			throw new DroolsException();
		}

		KnowledgeBase knowledge = KnowledgeBaseFactory.newKnowledgeBase();
		knowledge.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return knowledge.newStatelessKnowledgeSession();
	}
}
