package com.tobeface.modules.event.eventstore;

import com.tobeface.modules.event.Event;
import com.tobeface.modules.lang.annotation.Beta;

/**
 * 
 * @author loudyn
 * 
 */

@Beta
public interface EventStore {

	/**
	 * 
	 * @param event
	 */
	void append(Event event);
}
