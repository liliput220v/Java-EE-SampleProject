/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.a220vfor.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Andrew
 */
public class ModuleFactory {
    
    private Map<String, String> availModules;
    private HttpServletRequest request;
    
    public ModuleFactory(HttpServletRequest request, Map<String, String> availModules) {
        this.request = request;
        this.availModules = availModules;
    }
    
    public Module getModule(String moduleName) throws ServletException {
        
        try {
            
            moduleName = moduleName.toLowerCase();
            Class<?> moduleClass = Class.forName(availModules.get(moduleName));
            Constructor<?> constr = moduleClass.getConstructor(HttpServletRequest.class);
            
            return (Module) constr.newInstance(request);
            
        } catch (ClassNotFoundException e) {
            throw new ServletException("The class " + moduleName + " wasn't found.", e);
        } catch (IllegalAccessException | IllegalArgumentException | 
                InstantiationException | InvocationTargetException e) {
            throw new ServletException("The class " + moduleName + " can't be instantiated.", e);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new ServletException("The class' constructor wasn't found.", e);
        }
    }
    
    
}
