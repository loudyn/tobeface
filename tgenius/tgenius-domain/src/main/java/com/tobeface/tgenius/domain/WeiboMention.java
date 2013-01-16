package com.tobeface.tgenius.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObj;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboMention implements ValueObj<WeiboMention> {

	private WeiboPlatform platform;
	private List<String> names = new ArrayList<String>();
	private String content;

	public WeiboPlatform getPlatform() {
		return platform;
	}

	public void setPlatform(WeiboPlatform platform) {
		this.platform = platform;
	}

	public List<String> getNames() {
		return null == names ? Collections.<String> emptyList() : names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	@Override
	public boolean sameValueAs(WeiboMention other) {
		return new EqualsBuilder().append(getContent(), other.getContent()).isEquals();
	}

}
