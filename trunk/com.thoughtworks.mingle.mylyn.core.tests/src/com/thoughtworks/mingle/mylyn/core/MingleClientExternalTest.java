package com.thoughtworks.mingle.mylyn.core;

import java.io.IOException;
import java.net.URL;

import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;

/**
 * @author Ketan Padegaonkar
 */
public class MingleClientExternalTest extends AbstractMingleTestCase {
	public void testValidatesConnectionPasswordIsCorrect() throws Exception {
		MingleClient mingleClient = new MingleClient("ketan", "ketan", new URL("http://mingle:3000"));
		assertTrue(mingleClient.validate());
	}

	public void testThrowsMingleAuthenticationExceptionOnLoginFailure() throws Exception {
		MingleClient mingleClient = new MingleClient("ketan", "ketan1", new URL("http://mingle:3000"));
		try {
			mingleClient.validate();
			fail("expected MingleAuthenticationException, should not have reached here");
		} catch (MingleAuthenticationException e) {
			pass();
		}
	}

	public void testDoesNotValidateConnectionOnHttpErrors() throws Exception {
		MingleClient mingleClient = new MingleClient("ketan", "ketan", new URL("http://nonwhere:3000"));
		try {
			assertFalse(mingleClient.validate());
			fail("expected IOException, should not have reached here");
		} catch (IOException e) {
			pass();
		}
	}

}