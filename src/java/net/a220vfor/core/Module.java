
package net.a220vfor.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;

/**
 *
 * @author Andrew
 */
public abstract class Module {
    
    protected FilteredHttpRequest request;
    
    /**
     * The main template that is related to the module.
     */
    protected String template = "index";
        
    public Module(FilteredHttpRequest request) {
        this.request = request;
    }
    
    /**
     * Returns module's template.
     * @return 
     */
    public String getTemplate() {
        return template;
    }
    
    /**
     * Sets a template related to this module.
     * 
     * @param template the template name without .jsp extension
     */
    public void setTemplate(String template) {
        this.template = template;
    }
    
    /**
     * The index action. This method will be invoked when client requests module's index page.
     * @return 
     */
    public abstract Object index();
    
    /**
     * Executes requested action. The method obtains action name out of request.
     * @throws ServletException if the action can't be found or invoked
     */
    public void executeAction() throws ServletException {
        executeAction(request.getActionName());
    }
    
    /**
     * Executes requested action.
     * 
     * @param action the exact name of an action to be executed
     * @throws ServletException if the action can't be found or invoked
     */
    public void executeAction(String action) throws ServletException {
        
        try {
            Method method = this.getClass().getMethod(action);
            
            String actionTemplate = request.getActionNameAsRequested();
            String allowedMethod = "get";
            
            // get (or set) action properties
            ActionProperties actProps = method.<ActionProperties>getAnnotation(ActionProperties.class);
            
            if (actProps != null) {
                allowedMethod = actProps.method();
                actionTemplate = actProps.template();
            }
            
            if (!request.getMethod().equalsIgnoreCase(allowedMethod) && 
                !allowedMethod.equalsIgnoreCase("both")) {
                
                throw new ServletException("The request method " + request.getMethod() 
                    + " isn't supported by action '" + action +"'.");
            }
            
            request.setAttribute("actionTemplate", actionTemplate);
            Object data = method.invoke(this);
            request.setAttribute("data", data);

        } catch (NoSuchMethodException | SecurityException e) {
            throw new ServletException("The action '" + action + "' wasn't found.", e);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ServletException("The action '" + action + "' can't be invoked.", e);
        }
        
    }
}
