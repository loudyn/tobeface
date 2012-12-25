package com.tobeface.modules.event;

import java.util.EventObject;

/**
 * 
 * @author loudyn
 * 
 */
public class Event extends EventObject {
	private static final long serialVersionUID = 1L;
	private final long timestamp;
	private final String type;

	/**
	 * 
	 * @param source
	 */
	public Event(String type, Object source) {
		this(type, source, System.currentTimeMillis());
	}

	/**
	 * 
	 * @param source
	 * @param timestamp
	 */
	public Event(String type, Object source, long timestamp) {
		super(source);
		this.type = type;
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @return
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}
}
