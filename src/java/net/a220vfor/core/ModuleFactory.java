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

/**
 *
 * @author Andrew
 */
public class ModuleFactory {
    
    private Map<String, String> availModules;
    private FilteredHttpRequest request;
    
    /**
     * Creates new module simple factory.
     * 
     * @param request 
     * @param availModules the map of available modules
     */
    public ModuleFactory(FilteredHttpRequest request, Map<String, String> availModules) {
        this.request = request;
        this.availModules = availModules;
    }
    
    /**
     * Creates new module.
     * 
     * @return
     * @throws ServletException if the module can't be found or instantiated
     */
    public Module getModule() throws ServletException {
        return getModule(request.getModuleName());
    }
    
    /**
     * Creates module by its name.
     * 
     * @param moduleName the name of a module to create
     * @return
     * @throws ServletException if the module can't be found or instantiated
     */
    public Module getModule(String moduleName) throws ServletException {
        
        try {
            
            moduleName = moduleName.toLowerCase();
            Class<?> moduleClass = Class.forName(availModules.get(moduleName));
            Constructor<?> constr = moduleClass.getConstructor(FilteredHttpRequest.class);
            
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
