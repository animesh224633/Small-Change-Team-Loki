package com.smallchange.exception;

public class InsufficientHoldingsException extends Exception {
	public InsufficientHoldingsException() {
		super();
		
	}

	public InsufficientHoldingsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
	public InsufficientHoldingsException(String message, Throwable cause) {
		super(message, cause);
	
	}
	public InsufficientHoldingsException(String message) {
		super(message);
	
	}
	public InsufficientHoldingsException(Throwable cause) {
		super(cause);		
		}
}
