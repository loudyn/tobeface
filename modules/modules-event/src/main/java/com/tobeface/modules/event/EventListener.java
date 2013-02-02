package com.tobeface.modules.event;

import com.tobeface.modules.lang.annotation.Beta;

/**
 * 
 * @author loudyn
 * 
 */
@Beta
public interface EventListener extends java.util.EventListener {

	/**
	 * 
	 * @param event
	 */
	void onEvent(Event event);
}
