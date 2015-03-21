
package net.a220vfor.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * Provides prepared request information.
 * @author Andrew
 * @version 1.0.0
 */
public class MainHttpRequest extends FilteredHttpRequest {

    public MainHttpRequest(HttpServletRequest request) {
        super(request);
    }
    
    /**
     * Returns a <code>List</code> representation of path component in the request.<br>
     * Basically, a request path kind of /module-name/action/action-param-1/action-param-N will be split by the "/" 
     * character.
     * 
     * @return a list of substrings
     */
    @Override
    public List<String> getPathAsList() {
        
        List<String> items = new ArrayList<>(5);
        for (String action : request.getPathInfo().split("/")) {
            if (action.length() > 0) items.add(action);
        }
        
        return items;
    }
    
    /**
     * Retrieves requested module name out of the URL path.<br> 
     * In a request path like /module-name/action/etc it's the first part of the string, separated by the "/".
     * If the path is empty, it's assumed that the app's index module has been requested.
     * 
     * @return a module name (always in lower case) or 'index', if it is not provided by a client
     */
    @Override
    public String getModuleName() {
        
        String moduleName = "index";
        List<String> pathInfoList = getPathAsList();
        
        if (!pathInfoList.isEmpty()) {
            moduleName = pathInfoList.get(0).toLowerCase();
        }
        
        return moduleName;
    }
    
    /**
     * Retrieves requested action out of the URL path.<br>
     * The action name is the second part of a request path kind of /module-name/action/action-param/etc.
     * If the action is absent, it's assumed that module's index action has been requested. Dashes and underscores in
     * action name will be removed and replaced with camel case, e.g. my-action-name becomes myActionName.
     * 
     * @return the exact action (a method to be invoked) name or 'index', if no action is provided
     */
    @Override
    public String getActionName() {
        
        List<String> pathInfoList = getPathAsList();
        String action = "index";
        
        if (pathInfoList.size() >= 2) {
            // we have module name and its action
            action = dashToCamel(pathInfoList.get(1));
        }
        
        return action;
    }

    @Override
    public String getActionNameAsRequested() {
        
        List<String> pathInfoList = getPathAsList();
        String action = "index";
        
        if (pathInfoList.size() >= 2) {
            // we have module name and its action
            action = pathInfoList.get(1).toLowerCase();
        }
        
        return action;
    }
    
    /**
     * Retrieves a <code>List</code> of action parameters from the URL path.<br>
     * Every third and the following substrings of a request path kind of 
     * /mudule-name/action/action-param-1/action-param-2/etc are considered to be action parameters.
     * 
     * @return a list of action parameters or empty list, if no parameters were found
     */
    @Override
    public List<String> getActionParams() {
        
        List<String> pathInfoList = getPathAsList();
        List<String> params = new ArrayList<>(1);
        
        if (pathInfoList.size() > 2) {
            params = pathInfoList.subList(2, pathInfoList.size());
        }
        
        return params;
    }
    
    @Override
    public boolean isAjax() {
        Map<String, String> headers = getHeaderMap();
        return "XMLHttpRequest".equalsIgnoreCase(headers.get("x-requested-with"));
    }
    
    /**
     * Converts dashes and underscores in given string to camel case.
     * 
     * @param str the string to be converted
     * @return 
     */
    private String dashToCamel(String str) {
        
        if (!str.contains("-") && !str.contains("_")) {
            return str;
        }
        
        StringBuilder camelCasedStr = new StringBuilder();
        String[] strParts = str.split("-|_");
        
        for (String part : strParts) {
            
            camelCasedStr.append(Character.toUpperCase(part.charAt(0)));
            camelCasedStr.append(part.substring(1).toLowerCase());
            
        }
        
        return camelCasedStr.toString();
    }
    
}
