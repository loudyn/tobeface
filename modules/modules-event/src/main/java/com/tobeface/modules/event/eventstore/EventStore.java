package com.tobeface.modules.event.eventstore;

import com.tobeface.modules.event.Event;

/*8
 * 
 */
public interface EventStore {

	void append(Event event);
}
