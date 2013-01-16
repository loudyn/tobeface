package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObj;

/**
 * 
 * @author loudyn
 * 
 */
public final class WeiboLetter implements ValueObj<WeiboLetter> {

	private WeiboPlatform platform;

	private String[] users;
	private String content;

	public WeiboPlatform getPlatform() {
		return platform;
	}

	public void setPlatform(WeiboPlatform platform) {
		this.platform = platform;
	}

	public String[] getUsers() {
		return null == users ? new String[] {} : users;
	}

	public void setUsers(String[] users) {
		this.users = users;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean sameValueAs(WeiboLetter other) {
		if (null == other) {
			return false;
		}

		return new EqualsBuilder().append(getUsers(), other.getUsers()).append(getContent(), other.getContent()).isEquals();
	}
}
