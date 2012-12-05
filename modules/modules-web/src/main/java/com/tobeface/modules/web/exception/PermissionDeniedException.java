package com.tobeface.modules.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author loudyn
 * 
 */
@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class PermissionDeniedException extends RuntimeException {
}
