
package net.a220vfor.core;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Simple URL rewrite filter to separate requests to servlets from requests to static resources.
 * @author Andrew
 */
public class UrlRewriteFilter implements Filter {
    
    private FilterConfig filterConfig = null;
    
    /**
     * Returns configuration of this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return filterConfig;
    }
    
    /**
     * Init method of this filter
     * 
     * @param filterConfig the filter configuration to be set
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
    }
    
    /**
     * Performs filtration of any incoming request.
     * 
     * @param request the servlet request to be processed
     * @param response the servlet response
     * @param chain the filter chain
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpReq = (HttpServletRequest) request;
        
        String path = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
        
        if (isResourceStatic(path)) {
            // simply serve the request, pass it back down to the filter chain
            chain.doFilter(request, response);
        } else {
            // forward the request to the servlet
            request.getRequestDispatcher("/app" + path).forward(request, response);
        }
        
    }
    
    private boolean isResourceStatic(String path) {
        
        boolean isStatic = false;
        
        if (filterConfig == null) return isStatic;
        
        Enumeration<String> paramNames = filterConfig.getInitParameterNames();
        
        while (paramNames.hasMoreElements()) {
            
            String staticResource = filterConfig.getInitParameter(paramNames.nextElement());
            if (path.startsWith("/" + staticResource)) {
                isStatic = true;
                break;
            }
        }
        
        return isStatic;
    }

    /**
     * Destroy method of this filter.
     */
    @Override
    public void destroy() {
        // do nothing
    }
    
    /**
     * Returns a String representation of this object.
     */
    @Override
    public String toString() {
        
        if (filterConfig == null) {
            return ("UrlRewriteFilter()");
        }
        
        StringBuilder sb = new StringBuilder("UrlRewriteFilter(");
        sb.append(filterConfig);
        sb.append(")");
        
        return (sb.toString());
    }
    
}
