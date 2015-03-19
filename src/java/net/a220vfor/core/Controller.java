/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.a220vfor.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
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
        
        // Example path: Servlet/app/module-name/action?param1=value1&param2=value2#hash
        
        // first of all, we need to get module name and its action to execute
        FilteredHttpRequest filtRequest = new MainHttpRequest(request);
        
        // now we can load the module and execute its action, if any it exist    
        Module module = new ModuleFactory(filtRequest, availModules).getModule();
        module.executeAction();
        String template = module.getTemplate();
        
        if (filtRequest.isAjax()) {
            sendJSON(filtRequest, response);
        } else {
            filtRequest.getRequestDispatcher("/" + template + ".jsp").forward(filtRequest, response);
        }
                
    }
    
    private void sendJSON(FilteredHttpRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("application/json; charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            JsonStructure json = (JsonStructure) request.getAttribute("json");
            
            if (json == null) {
                out.println("{error: \"No JSON object found!\"}");
                return;
            }
            
            try (JsonWriter jWriter = Json.createWriter(out)) {
                jWriter.write(json);
            }
        }
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
