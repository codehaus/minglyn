package com.thoughtworks.mingle.mylyn.core;

import java.util.Collection;

import junit.framework.TestCase;

/**
 * @author Ketan Padegaonkar
 */
public abstract class AbstractMingleTestCase extends TestCase {

	protected void pass() {
		// do nothing
	}

	protected void assertNotEmpty(Collection collection) {
		assertNotNull(collection);
		assertFalse(collection.isEmpty());
	}

}
