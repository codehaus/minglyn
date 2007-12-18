package com.thoughtworks.mingle.mylyn.core;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Ketan Padegaonkar
 */
public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.thoughtworks.mingle.mylyn.core");
        //$JUnit-BEGIN$
        suite.addTestSuite(MingleClientExternalTest.class);
        suite.addTestSuite(MingleClientTest.class);
        //$JUnit-END$
        return suite;
    }
}
