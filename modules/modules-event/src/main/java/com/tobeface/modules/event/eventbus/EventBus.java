package com.tobeface.modules.event.eventbus;

import com.tobeface.modules.event.Event;
import com.tobeface.modules.event.EventListener;

/**
 * 
 * @author loudyn
 * 
 */
public interface EventBus {

	/**
	 * 
	 * @param listener
	 */
	public void subscibe(EventListener listener);

	/**
	 * 
	 * @param listener
	 */
	public void unsubscibe(EventListener listener);

	/**
	 * 
	 * @param event
	 */
	public void publish(Event event);
}
