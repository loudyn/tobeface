package com.tobeface.modules.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.lang.Strings;

/**
 * 
 * @author loudyn
 * 
 * @param <ID>
 */
@SuppressWarnings("serial")
public abstract class AbstractDomainObj implements DomainObj<AbstractDomainObj>, Serializable {

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean hasIdentity() {
		return !Strings.isBlank(getId());
	}

	public boolean sameIdentityAs(AbstractDomainObj other) {
		if (null == other) {
			return false;
		}

		if (this == other) {
			return true;
		}

		return new EqualsBuilder().append(this.getId(), other.getId()).isEquals();
	}
}
