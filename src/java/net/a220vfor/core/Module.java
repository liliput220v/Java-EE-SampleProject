
package net.a220vfor.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Andrew
 */
public abstract class Module {
    
    protected HttpServletRequest request;
    
    /**
     * The main template that is related to the module.
     */
    protected String template = "index";
    
    /**
     * The action content template.
     */
    protected String actionTemplate;
    
    public Module(HttpServletRequest request) {
        this.request = request;
    }
    
    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
    
    /**
     * The index action. This method will be invoked when client requests module's index page.
     */
    public abstract void index();
    
    /**
     * 
     * @param actions the action list to execute
     * @throws ServletException 
     */
    public void executeActions(List<String> actions) throws ServletException {
        
        for (String action : actions) {
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
}
