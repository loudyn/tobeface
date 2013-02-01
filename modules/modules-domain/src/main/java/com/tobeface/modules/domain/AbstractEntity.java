package com.tobeface.modules.domain;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author loudyn
 * 
 * @param <ID>
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity<ID, T> implements Entity<T>, Serializable {

	@Id
	private ID id;
	@Version
	private Long version;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final boolean equals(Object obj) {

		if (null == obj || getClass() != obj.getClass()) {
			return false;
		}

		T other = (T) obj;
		return sameIdentityAs(other);
	}

	@Override
	public final int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getVersion()).toHashCode();
	}

	public boolean hasIdentity() {
		return null != getId();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public final boolean sameIdentityAs(T other) {
		if (null == other) {
			return false;
		}

		if (this == other) {
			return true;
		}

		AbstractEntity another = (AbstractEntity) other;
		return new EqualsBuilder().append(getId(), another.getId()).append(getVersion(), another.getVersion()).isEquals();
	}
}
