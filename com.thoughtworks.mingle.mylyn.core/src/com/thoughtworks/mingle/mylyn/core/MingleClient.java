package com.thoughtworks.mingle.mylyn.core;

import static com.thoughtworks.mingle.mylyn.core.MingleConstants.LOGIN_URL;
import static com.thoughtworks.mingle.mylyn.core.MingleConstants.PROJECTS_BASE_URL;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.eclipse.mylyn.tasks.core.RepositoryTaskData;
import org.eclipse.mylyn.tasks.core.TaskRepository;

import com.thoughtworks.mingle.mylyn.core.exceptions.CouldNotParseTasksException;
import com.thoughtworks.mingle.mylyn.core.exceptions.MingleAuthenticationException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * @author Ketan Padegaonkar
 */
public class MingleClient {

    private URL serverUrl;
    private String password;
    private String userName;
    protected HttpClient httpClient;

    public MingleClient(String userName, String password, URL serverUrl) {
        this.userName = userName;
        this.password = password;
        this.serverUrl = serverUrl;
    }

    public MingleClient(String userName, String password, String serverUrl) throws MalformedURLException {
        this(userName, password, new URL(serverUrl));
    }

    public MingleClient(TaskRepository repository) throws MalformedURLException {
        this(repository.getUserName(), repository.getPassword(), repository.getUrl());
    }

    protected HttpClient getClient() {
        if (this.httpClient == null) {
            this.httpClient = new HttpClient();
            this.httpClient.getParams().setAuthenticationPreemptive(true);
            Credentials defaultcreds = new UsernamePasswordCredentials(userName, password);
            AuthScope authScope = new AuthScope(this.serverUrl.getHost(), this.serverUrl.getPort(), AuthScope.ANY_REALM);
            this.httpClient.getState().setCredentials(authScope, defaultcreds);
        }
        return this.httpClient;
    }

    public boolean validate() throws IOException, MingleAuthenticationException {
        HttpMethod method = getMethod(loginUrl());
        try {
            int httpStatus = executeMethod(method);
            switch (httpStatus) {
            case HttpStatus.SC_OK:
                return true;
            case HttpStatus.SC_UNAUTHORIZED:
                throw new MingleAuthenticationException();
            }
        } finally {
            if (method != null)
                method.releaseConnection();
        }
        return false;
    }

    protected Header getCookieHeader(PostMethod method) {
        return method.getResponseHeader("set-cookie");
    }

    protected Header getRedirectLocationHeader(PostMethod method) {
        return method.getResponseHeader("location");
    }

    // private boolean verifyResponse(HttpMethod method, int httpStatus, String
    // responseString) throws IOException,
    // HttpException {
    // if (executeMethod(method) == httpStatus) {
    // return method.getResponseBodyAsString().contains(responseString);
    // } else
    // return false;
    // }

    protected int executeMethod(HttpMethod method) throws IOException {
        return getClient().executeMethod(method);
    }

    protected PostMethod postMethod(String url) {
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        return postMethod;
    }

    protected HttpMethod getMethod(String url) {
        return new GetMethod(url);
    }

    public String queryUrl(String queryString) {
        return serverUrl + "/cards.xml?" + queryString;
    }

    private String loginUrl() {
        return serverUrl.toString();
    }

    @Override
    public String toString() {
        return userName + ":" + password + "@" + serverUrl;
    }

    public MingleTaskList getAllTasks(String queryString) throws IOException, MingleAuthenticationException, CouldNotParseTasksException {
        HttpMethod method = getMethod(queryUrl(queryString));
        try {
            switch (executeMethod(method)) {
            case HttpStatus.SC_OK:
                return (MingleTaskList) parse(getResponse(method));
            case HttpStatus.SC_UNAUTHORIZED:
                throw new MingleAuthenticationException("Could not authenticate user. Check username and password.");
            }
            return new MingleTaskList();
        } finally {
            if (method != null)
                method.releaseConnection();
        }
    }

    private MingleTaskList parse(Reader inputStreamReader) throws CouldNotParseTasksException {
        try {
            XStream stream = new XStream(new StaxDriver());
            stream.registerConverter(new MingleTaskConverter(serverUrl));
            stream.alias("cards", List.class);
            stream.alias("card", MingleTask.class);
            List<MingleTask> tasks = (List<MingleTask>) stream.fromXML(inputStreamReader);
            return new MingleTaskList(tasks);
        } catch (Exception e) {
            throw new CouldNotParseTasksException(e);
        }
    }

    protected Reader getResponse(HttpMethod method) throws IOException {
        return new InputStreamReader(method.getResponseBodyAsStream());
    }

    public MingleTask getTask(String taskId, String projectName) throws IOException, MingleAuthenticationException,
            CouldNotParseTasksException {
        return getAllTasks(projectName).getTaskWithId(taskId);
    }

    public RepositoryTaskData getTaskData(String taskId) {
        return new RepositoryTaskData(new MingleAttributeFactory(), Activator.CONNECTOR_KIND, this.serverUrl.toString(), taskId);
    }


}
