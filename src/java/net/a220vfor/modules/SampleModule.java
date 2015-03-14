
package net.a220vfor.modules;

import javax.servlet.http.HttpServletRequest;
import net.a220vfor.core.Module;

/**
 *
 * @author Andrew
 */
public class SampleModule extends Module {
    
    public SampleModule(HttpServletRequest request) {
        super(request);
        
        template = "module";
        
        request.setAttribute("title", "Test page");
        request.setAttribute("module", this.getClass()); // debug
    }
    
    @Override
    public void index() {
        
    }
    
    // probably, actions should return or set its content template
    public void action1() {}
    public void action2() {}
    public void action3() {}
    
}
