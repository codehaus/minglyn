package com.thoughtworks.mingle.mylyn.core;

import junit.framework.TestSuite;

/**
 * @author Ketan Padegaonkar
 */
public class AllTests extends TestSuite {

	public AllTests() {
		addTest(new TestSuite(MingleClientTest.class));
	}

	public void testNullTestToKeepJunitHappy() throws Exception {

	}
}
