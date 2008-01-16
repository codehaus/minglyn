package com.thoughtworks.mingle.mylyn.core;

import java.io.IOException;
import java.net.URL;

import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;

/**
 * @author Ketan Padegaonkar
 */
public class MingleClientExternalTest extends AbstractMingleTestCase {
    private static final String MINGLE_PASSWORD = "password";
    private static final String MINGLE_USER = "minglyn";
    private static final String MINGLE_URL = "http://tidepair4:8080/projects/data_farm";

    public void testValidatesConnectionPasswordIsCorrect() throws Exception {
        MingleClient mingleClient = new MingleClient(MINGLE_USER, MINGLE_PASSWORD, new URL(MINGLE_URL));
        assertTrue(mingleClient.validate());
    }

    public void testThrowsMingleAuthenticationExceptionOnLoginFailure() throws Exception {
        MingleClient mingleClient = new MingleClient(MINGLE_USER, "invalidPassword", new URL(MINGLE_URL));
        try {
            mingleClient.validate();
            fail("expected MingleAuthenticationException, should not have reached here");
        } catch (MingleAuthenticationException e) {
            pass();
        }
    }

    public void testDoesNotValidateConnectionOnHttpErrors() throws Exception {
        MingleClient mingleClient = new MingleClient(MINGLE_USER, MINGLE_USER, new URL("http://nonwhere:3000"));
        try {
            assertFalse(mingleClient.validate());
            fail("expected IOException, should not have reached here");
        } catch (IOException e) {
            pass();
        }
    }

    public void testGets25MingleCards() throws Exception {
        MingleClient mingleClient = new MingleClient(MINGLE_USER, MINGLE_PASSWORD, new URL(MINGLE_URL));
        MingleTaskList allTasks = mingleClient.getAllTasks("");
        assertEquals(25, allTasks.size());
    }

}
