package com.thoughtworks.mingle.mylyn.core;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.eclipse.mylyn.tasks.core.AbstractTask;

import com.thoughtworks.mingle.mylyn.core.exceptions.CouldNotParseTasksException;
import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;

/**
 * @author Ketan Padegaonkar
 */
public class MingleClientTest extends AbstractMingleTestCase {

	/**
	 * @author Ketan Padegaonkar
	 */
	private class TestMingleClient extends MingleClient {
		private int	httpStatus	= HttpStatus.SC_OK;

		public TestMingleClient(String userName, String password, URL serverUrl, int httpStatus) {
			super(userName, password, serverUrl);
			this.httpStatus = httpStatus;
		}

		@Override
		protected int executeMethod(HttpMethod method) throws IOException, HttpException {
			return httpStatus;
		}
	}

	public void testValidatesWhenHttpStatusIs200() throws Exception {
		MingleClient mingleClient = new TestMingleClient("ketan", "ketan", new URL("http://localhost:30001"), HttpStatus.SC_OK);
		assertTrue(mingleClient.validate());
	}

	public void testThrowsMingleAuthenticationExceptionOnHttpStatus401() throws Exception {
		MingleClient mingleClient = new TestMingleClient("ketan", "ketan", new URL("http://localhost:30001"), HttpStatus.SC_UNAUTHORIZED);
		try {
			mingleClient.validate();
			fail("expected MingleAuthenticationException, should not have reached here");
		} catch (MingleAuthenticationException e) {
			pass();
		}
	}

	public void testDoesNotValidateConnectionOnHttpErrors() throws Exception {
		MingleClient mingleClient = new TestMingleClient("ketan", "ketan", new URL("http://nonwhere:3000"), 0) {
			@Override
			protected int executeMethod(HttpMethod method) throws IOException, HttpException {
				throw new IOException();
			}
		};
		try {
			assertFalse(mingleClient.validate());
			fail("expected IOException, should not have reached here");
		} catch (IOException e) {
			pass();
		}
	}

	public void testTaskListNotEmpty() throws Exception {
		MingleClient mingleClient = clientWithCards();

		MingleTaskList allTasks = mingleClient.getAllTasks("someproject");
		assertEquals(2, allTasks.size());
		MingleTask task = (MingleTask) allTasks.get(0);
		assertEquals("name1", task.getSummary());
		assertEquals("id1", task.getTaskId());
		assertEquals("description1", task.getDescription());
		assertEquals("attrib1", task.getAttribute("someAttribute"));
		assertEquals("attrib2", task.getAttribute("someAttribute1"));

		task = (MingleTask) allTasks.get(1);
		assertEquals("name2", task.getSummary());
		assertEquals("id2", task.getTaskId());
		assertEquals("description2", task.getDescription());
	}

	private MingleClient clientWithCards() throws MalformedURLException {
		MingleClient mingleClient = new TestMingleClient("ketan", "ketan", new URL("http://localhost:3000"), HttpStatus.SC_OK) {

			@Override
			protected Reader getResponse(HttpMethod method) throws IOException {
				return new StringReader("<cards>\n" + 
						"        <card>\n" + 
						"                <id>id1</id>\n" + 
						"                <name>name1</name>\n" + 
						"                <description>description1</description>\n" + 
						"                <someAttribute>attrib1</someAttribute>\n" + 
						"                <someAttribute1>attrib2</someAttribute1>\n" + 
						"        </card>\n" + 
						"        <card>\n" + 
						"                <id>id2</id>\n" + 
						"                <name>name2</name>\n" + 
						"                <description>description2</description>\n" + 
						"        </card>\n" + 
						"</cards>\n");
			}
		};
		return mingleClient;
	}
	
	public void testGetsArbritratyCard() throws Exception {
		MingleClient mingleClient = clientWithCards();
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("description", "description1");
		hashMap.put("id", "id1");
		hashMap.put("name", "name1");
		hashMap.put("someAttribute", "attrib1");
		hashMap.put("someAttribute1", "attrib2");
		
		MingleTask mingleTask = new MingleTask("http://localhost:3000", "id1", "name1", "description1", hashMap);
		assertEquals(mingleTask, mingleClient.getTask("id1", "someproject"));
	}

	public void testInvalidXmlThrowsException() throws Exception {
		MingleClient mingleClient = new TestMingleClient("ketan", "ketan", new URL("http://localhost:3000"),
				HttpStatus.SC_OK) {
			@Override
			protected Reader getResponse(HttpMethod method) throws IOException {
				return new StringReader("<html>Some response for client</html>\n");
			}
		};

		try {
			mingleClient.getAllTasks("asfsdf");
			fail("Expected CouldNotParseTasksException");
		} catch (CouldNotParseTasksException e) {
			pass();
		}
	}
}
