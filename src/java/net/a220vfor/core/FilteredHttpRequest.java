
package net.a220vfor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

/**
 * Provides prepared request information.
 * @author Andrew
 */
public abstract class FilteredHttpRequest implements HttpServletRequest {
    
    protected HttpServletRequest request;
    
    public FilteredHttpRequest(HttpServletRequest request) {
        this.request = request;
    }

    //<editor-fold defaultstate="collapsed" desc="Delegated methods.">
    @Override
    public String getAuthType() {
        return request.getAuthType();
    }
    
    @Override
    public Cookie[] getCookies() {
        return request.getCookies();
    }
    
    @Override
    public long getDateHeader(String name) {
        return request.getDateHeader(name);
    }
    
    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }
    
    @Override
    public Enumeration<String> getHeaders(String name) {
        return request.getHeaders(name);
    }
    
    @Override
    public Enumeration<String> getHeaderNames() {
        return request.getHeaderNames();
    }
    
    @Override
    public int getIntHeader(String name) {
        return request.getIntHeader(name);
    }
    
    @Override
    public String getMethod() {
        return request.getMethod();
    }
    
    @Override
    public String getPathInfo() {
        return request.getPathInfo();
    }
    
    @Override
    public String getPathTranslated() {
        return request.getPathTranslated();
    }
    
    @Override
    public String getContextPath() {
        return request.getContextPath();
    }
    
    @Override
    public String getQueryString() {
        return request.getQueryString();
    }
    
    @Override
    public String getRemoteUser() {
        return request.getRemoteUser();
    }
    
    @Override
    public boolean isUserInRole(String role) {
        return request.isUserInRole(role);
    }
    
    @Override
    public Principal getUserPrincipal() {
        return request.getUserPrincipal();
    }
    
    @Override
    public String getRequestedSessionId() {
        return request.getRequestedSessionId();
    }
    
    @Override
    public String getRequestURI() {
        return request.getRequestURI();
    }
    
    @Override
    public StringBuffer getRequestURL() {
        return request.getRequestURL();
    }
    
    @Override
    public String getServletPath() {
        return request.getServletPath();
    }
    
    @Override
    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }
    
    @Override
    public HttpSession getSession() {
        return request.getSession();
    }
    
    @Override
    public String changeSessionId() {
        return request.changeSessionId();
    }
    
    @Override
    public boolean isRequestedSessionIdValid() {
        return request.isRequestedSessionIdValid();
    }
    
    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return request.isRequestedSessionIdFromCookie();
    }
    
    @Override
    public boolean isRequestedSessionIdFromURL() {
        return request.isRequestedSessionIdFromURL();
    }
    
    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return request.isRequestedSessionIdFromUrl();
    }
    
    @Override
    public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
        return request.authenticate(response);
    }
    
    @Override
    public void login(String username, String password) throws ServletException {
        request.login(username, password);
    }
    
    @Override
    public void logout() throws ServletException {
        request.logout();
    }
    
    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return request.getParts();
    }
    
    @Override
    public Part getPart(String name) throws IOException, ServletException {
        return request.getPart(name);
    }
    
    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
        return request.upgrade(handlerClass);
    }
    
    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }
    
    @Override
    public Enumeration<String> getAttributeNames() {
        return request.getAttributeNames();
    }
    
    @Override
    public String getCharacterEncoding() {
        return request.getCharacterEncoding();
    }
    
    @Override
    public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
        request.setCharacterEncoding(env);
    }
    
    @Override
    public int getContentLength() {
        return request.getContentLength();
    }
    
    @Override
    public long getContentLengthLong() {
        return request.getContentLengthLong();
    }
    
    @Override
    public String getContentType() {
        return request.getContentType();
    }
    
    @Override
    public ServletInputStream getInputStream() throws IOException {
        return request.getInputStream();
    }
    
    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }
    
    @Override
    public Enumeration<String> getParameterNames() {
        return request.getParameterNames();
    }
    
    @Override
    public String[] getParameterValues(String name) {
        return request.getParameterValues(name);
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
    }
    
    @Override
    public String getProtocol() {
        return request.getProtocol();
    }
    
    @Override
    public String getScheme() {
        return request.getScheme();
    }
    
    @Override
    public String getServerName() {
        return request.getServerName();
    }
    
    @Override
    public int getServerPort() {
        return request.getServerPort();
    }
    
    @Override
    public BufferedReader getReader() throws IOException {
        return request.getReader();
    }
    
    @Override
    public String getRemoteAddr() {
        return request.getRemoteAddr();
    }
    
    @Override
    public String getRemoteHost() {
        return request.getRemoteHost();
    }
    
    @Override
    public void setAttribute(String name, Object o) {
        request.setAttribute(name, o);
    }
    
    @Override
    public void removeAttribute(String name) {
        request.removeAttribute(name);
    }
    
    @Override
    public Locale getLocale() {
        return request.getLocale();
    }
    
    @Override
    public Enumeration<Locale> getLocales() {
        return request.getLocales();
    }
    
    @Override
    public boolean isSecure() {
        return request.isSecure();
    }
    
    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return request.getRequestDispatcher(path);
    }
    
    @Override
    public String getRealPath(String path) {
        return request.getRealPath(path);
    }
    
    @Override
    public int getRemotePort() {
        return request.getRemotePort();
    }
    
    @Override
    public String getLocalName() {
        return request.getLocalName();
    }
    
    @Override
    public String getLocalAddr() {
        return request.getLocalAddr();
    }
    
    @Override
    public int getLocalPort() {
        return request.getLocalPort();
    }
    
    @Override
    public ServletContext getServletContext() {
        return request.getServletContext();
    }
    
    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return request.startAsync();
    }
    
    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return request.startAsync(servletRequest, servletResponse);
    }
    
    @Override
    public boolean isAsyncStarted() {
        return request.isAsyncStarted();
    }
    
    @Override
    public boolean isAsyncSupported() {
        return request.isAsyncSupported();
    }
    
    @Override
    public AsyncContext getAsyncContext() {
        return request.getAsyncContext();
    }
    
    @Override
    public DispatcherType getDispatcherType() {
        return request.getDispatcherType();
    }
//</editor-fold>
    
    /**
     * Returns a <code>List</code> representation of path component in the request.
     * 
     * @return a list of requested actions
     */
    public abstract List<String> getPathAsList();
    
    /**
     * Retrieves requested module name out of the URL path.
     * 
     * @return a module name or 'index', if it is not provided by a client
     */
    public abstract String getModuleName();
    
    /**
     * Retrieves requested action out of the URL path.
     * 
     * @return the exact action (a method to be invoked) name or 'index', if no action is provided
     */
    public abstract String getActionName();
    
    /**
     * Retrieves requested action name out of the URL path. Unlike <code>getActionName()</code> this method returns
     * almost unfiltered action name (in lower case).
     * 
     * @return the exact action name or 'index', if no action is provided
     */
    public abstract String getActionNameAsRequested();
    
    /**
     * Retrieves a <code>List</code> of action parameters from the URL path.
     * 
     * @return a list of action parameters or empty list, if no parameters were found
     */
    public abstract List<String> getActionParams();
    
    /**
     * Whether or not the request is an AJAX (XMLHttpRequest) request.
     * 
     * @return <code>true</code> if the request is an AJAX request, <code>false</code> otherwise
     */
    public abstract boolean isAjax();
    
    /**
     * Returns request headers as a <code>Map</code>.
     * 
     * @return if the request has no headers, this method returns an empty map.
     */
    public Map<String, String> getHeaderMap() {
        
        Map<String, String> headerMap = new HashMap<>();
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            
            String name = headerNames.nextElement();
            headerMap.put(name, request.getHeader(name));
        }
        
        return headerMap;
    }
    
}
