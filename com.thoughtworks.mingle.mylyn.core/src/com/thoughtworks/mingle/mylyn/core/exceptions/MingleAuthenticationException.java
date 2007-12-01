package com.thoughtworks.mingle.mylyn.core.exceptions;

/**
 * @author Ketan Padegaonkar
 * 
 */
public class MingleAuthenticationException extends Exception {

	public MingleAuthenticationException() {
		super();
	}

	public MingleAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MingleAuthenticationException(String message) {
		super(message);
	}

	public MingleAuthenticationException(Throwable cause) {
		super(cause);
	}

}
