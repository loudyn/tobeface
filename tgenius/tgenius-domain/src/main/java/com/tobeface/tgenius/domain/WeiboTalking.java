package com.tobeface.tgenius.domain;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.tobeface.modules.domain.ValueObject;

/**
 * 
 * @author loudyn
 * 
 */
public class WeiboTalking implements ValueObject<WeiboTalking> {

	private WeiboUserLocation location;
	private WeiboTalkingTime talkingTime;
	private String keyword;

	public WeiboUserLocation getLocation() {
		return location;
	}

	public void setLocation(WeiboUserLocation location) {
		this.location = location;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasAvaliableLocation() {
		if (null != getLocation()) {
			return getLocation().isAvaliable();
		}

		return false;
	}

	/**
	 * 
	 * @return
	 */
	public long getLocLongitude() {
		if (hasAvaliableLocation()) {
			return getLocation().getLongitude();
		}

		return -1L;
	}

	/**
	 * 
	 * @return
	 */
	public long getLocLatitude() {
		if (hasAvaliableLocation()) {
			return getLocation().getLatitude();
		}
		return -1L;
	}

	public int getRadius() {
		if (hasAvaliableLocation()) {
			return getLocation().getRadius();
		}

		return -1;
	}

	public WeiboTalkingTime getTalkingTime() {
		return talkingTime;
	}

	public void setTalkingTime(WeiboTalkingTime talkingTime) {
		this.talkingTime = talkingTime;
	}

	public long getTalkingStartTime() {
		if (hasAvaliableTalkingTime()) {
			return getTalkingTime().getStartTime();
		}

		return -1L;
	}

	public long getTalkingEndTime() {
		if (hasAvaliableTalkingTime()) {
			return getTalkingTime().getEndTime();
		}

		return -1L;
	}

	public boolean hasAvaliableTalkingTime() {
		if (null == getTalkingTime()) {
			return getTalkingTime().isAvaliable();
		}
		return false;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public boolean sameValueAs(WeiboTalking other) {
		return new EqualsBuilder().append(getLocation(), other.getLocation())
									.append(getTalkingTime(), other.getTalkingTime())
									.append(getKeyword(), other.getKeyword()).isEquals();
	}

}
