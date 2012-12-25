package com.tobeface.modules.event;

/**
 * 
 * @author loudyn
 * 
 */
public interface EventListener {

	/**
	 * 
	 * @param event
	 */
	void onApplicationEvent(Event event);
}
