
package net.a220vfor.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
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
    
    /**
     * The action content template.
     */
    protected String actionTemplate;
    
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
     */
    public abstract void index();
    
    /**
     * Executes requested action.
     * @throws ServletException if the action can't be found or invoked
     */
    public void executeAction() throws ServletException {
        executeAction(request.getAction());
    }
    
    /**
     * Executes requested action.
     * 
     * @param action the action to be executed
     * @throws ServletException if the action can't be found or invoked
     */
    public void executeAction(String action) throws ServletException {
        
        try {
            Method method = this.getClass().getMethod(action);
            method.invoke(this);

        } catch (NoSuchMethodException | SecurityException e) {
            throw new ServletException("The action '" + action + "' wasn't found.", e);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ServletException("The action '" + action + "' can't be invoked.", e);
        }
        
    }
}
