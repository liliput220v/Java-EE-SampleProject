/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.a220vfor.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrew
 */
public class Controller extends HttpServlet {
    
    private Map<String, String> availModules;
    
    {
        availModules = new HashMap<>();
        availModules.put("index", "net.a220vfor.modules.IndexPageModule");
        availModules.put("module", "net.a220vfor.modules.SampleModule");
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //<editor-fold defaultstate="collapsed" desc="Debug path info.">
        String pathInfo = request.getPathInfo();
        request.setAttribute("pathInfo", pathInfo);
        String queryString = request.getQueryString();
        request.setAttribute("queryString", queryString);
        //</editor-fold>
        
        // Example path: Servlet/app/module-name/action?param1=value1&param2=value2#hash
        
        // first of all, we need to get module name and its actions to execute
        List<String> pathInfoList = getPathAsList(request);
        String moduleName = getModuleName(pathInfoList);
        List<String> actions = getActions(pathInfoList);
        
        // now we can load the module and execute its actions, if any of them exist    
        Module module = new ModuleFactory(request, availModules).getModule(moduleName);
        module.executeActions(actions);
        String template = module.getTemplate();
        
        request.getRequestDispatcher("/" + template + ".jsp").forward(request, response);
        
        
    }

    
    
    //<editor-fold defaultstate="collapsed" desc="Method for debug.">
    private String getQueryAsString(Map<String, String[]> queryMap) {
        
        StringBuilder output = new StringBuilder();
        
        for (Map.Entry<String, String[]> entry : queryMap.entrySet()) {
            output.append(entry.getKey());
            output.append(Arrays.toString(entry.getValue()));
            output.append("<br>");
        }
        
        return output.toString();
    }
    //</editor-fold>
    
    /**
     * Returns a <code>List</code> representation of path component in the request.
     * 
     * @param request the servlet request
     * @return a list of requested actions
     */
    private List<String> getPathAsList(HttpServletRequest request) {
        
        List<String> items = new ArrayList<>(5);
        for (String action : request.getPathInfo().split("/")) {
            if (action.length() > 0) items.add(action);
        }
        
        return items;
    }
    
    /**
     * Retrieves module name out of the path action list.
     * 
     * @param pathInfoList the list of path elements
     * @return a module name or 'index', if it is not provided by the client
     */
    private String getModuleName(List<String> pathInfoList) {
        
        String moduleName = "index";
        
        if (!pathInfoList.isEmpty()) {
            moduleName = pathInfoList.get(0);
        }
        
        return moduleName;
    }
    
    /**
     * Retrieves action list out of the path action list.
     * 
     * @param pathInfoList the list of path elements
     * @return a list of requested actions; 
     *         if actions hasn't been provided by the client, the list will contain only 'index' action
     */
    private List<String> getActions(List<String> pathInfoList) {
        
        List<String> actions = new ArrayList<>(1);
        actions.add("index");
        
        if (pathInfoList.size() >= 2) {
            // we have module name and its action so fill up the list
            actions = pathInfoList.subList(1, pathInfoList.size());
        }
        
        return actions;
    }
    
    /**
     * Whether or not the request is an AJAX request.
     * 
     * @param pathInfoList the list of path elements
     * @return <code>true</code> if the request is an AJAX request, <code>false</code> otherwise
     */
    private boolean isAjax(List<String> pathInfoList) {
        
        int size = pathInfoList.size();
        
        if (size > 2) {
            return pathInfoList.remove(size - 1).equalsIgnoreCase("ajax");
        }
        
        return false;
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Front action controller.";
    }
}
