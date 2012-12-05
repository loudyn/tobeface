package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObject;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboLetter implements ValueObject<WeiboLetter> {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean sameValueAs(WeiboLetter other) {
		return new EqualsBuilder().append(getContent(), other.getContent()).isEquals();
	}
}
