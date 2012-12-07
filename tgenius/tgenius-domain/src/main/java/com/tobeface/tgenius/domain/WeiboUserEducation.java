package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

import com.tobeface.modules.domain.ValueObject;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboUserEducation implements ValueObject<WeiboUserEducation> {
	
	@JsonProperty("level")
	private String level;
	@JsonProperty("year")
	private int year;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean sameValueAs(WeiboUserEducation other) {
		return new EqualsBuilder().append(getYear(), other.getYear())
				.append(getLevel(), other.getLevel())
				.isEquals();
	}
}
