package com.tobeface.tgenius.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObject;
import com.tobeface.modules.lang.Preconditions;
import com.tobeface.modules.lang.Strings;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboMention implements ValueObject<WeiboMention> {
	private List<String> names = new ArrayList<String>();
	private String content;

	/**
	 * 
	 * @param username
	 * @return
	 */
	public WeiboMention mention(String username) {
		Preconditions.hasText(username);
		if (username.startsWith("@")) {
			names.add(username);
			return this;
		}

		names.add("@".concat(username));
		return this;
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
		if (names.isEmpty()) {
			return content;
		}

		return Strings.join(names, " ").concat(" ").concat(content);
	}

	@Override
	public boolean sameValueAs(WeiboMention other) {
		return new EqualsBuilder().append(getContent(), other.getContent()).isEquals();
	}

}
