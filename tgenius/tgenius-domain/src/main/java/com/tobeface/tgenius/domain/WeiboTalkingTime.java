package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObj;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboTalkingTime implements ValueObj<WeiboTalkingTime> {

	private long startTime = -1;
	private long endTime = -1;

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

	public boolean isAvaliable() {
		return (getStartTime() > 0) && (getEndTime() > getStartTime());
	}

	@Override
	public boolean sameValueAs(WeiboTalkingTime other) {
		return new EqualsBuilder().append(getStartTime(), other.getStartTime())
									.append(getEndTime(), other.getEndTime())
									.isEquals();
	}

}
