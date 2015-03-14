
package net.a220vfor.modules;

import javax.servlet.http.HttpServletRequest;
import net.a220vfor.core.Module;

/**
 *
 * @author Andrew
 */
public class IndexPageModule extends Module {

    public IndexPageModule(HttpServletRequest request) {
        super(request);
    }

    @Override
    public void index() {
        // to do: set some index content here, e.g. print database statistics
    }
    
}
