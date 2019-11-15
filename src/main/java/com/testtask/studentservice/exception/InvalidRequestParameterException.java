package com.testtask.studentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestParameterException extends RuntimeException {
	public InvalidRequestParameterException() {
	}

	public InvalidRequestParameterException(String message) {
		super(message);
	}

	public InvalidRequestParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidRequestParameterException(Throwable cause) {
		super(cause);
	}

	public InvalidRequestParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
