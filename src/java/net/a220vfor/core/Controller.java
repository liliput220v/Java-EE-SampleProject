
package net.a220vfor.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The application controller, its main entry point. Implements front controller pattern.
 * @author Andrew
 */
public class Controller extends HttpServlet {
    
    private final Map<String, String> availModules = new HashMap<>();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        Enumeration<String> paramNames = config.getInitParameterNames();
        
        while (paramNames.hasMoreElements()) {
            
            String moduleName = paramNames.nextElement();
            availModules.put(
                moduleName,
                config.getInitParameter(moduleName)
            );
        }
    }
    
    /**
     * Receives HTTP requests from the public service method, process and forwards them to the view templates, or sends
     * JSON object back to the client. This method won't call any of the doXXX methods.
     *
     * @param request the servlet request
     * @param response the servlet response
     * @throws ServletException if a servlet-specific error occurs and servlet cannot handle the request
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
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
            
            JsonStructure json = (JsonStructure) request.getAttribute("data");
            
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Front action controller.";
    }
}
