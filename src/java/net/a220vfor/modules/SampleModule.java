
package net.a220vfor.modules;

import javax.json.Json;
import javax.json.JsonObject;
import net.a220vfor.core.FilteredHttpRequest;
import net.a220vfor.core.Module;

/**
 *
 * @author Andrew
 */
public class SampleModule extends Module {
    
    public SampleModule(FilteredHttpRequest request) {
        super(request);
        
        template = "module";
        
        request.setAttribute("title", "Test page");
        request.setAttribute("heading", "Sample module heading");
        request.setAttribute("module", this.getClass()); // debug
    }
    
    @Override
    public Object index() {
        
        JsonObject json = Json.createObjectBuilder()
            .add("paramKey", "param-value + Кириллица")
            .add("array", Json.createArrayBuilder()
                .add("some value in the array & *")
                .add(System.currentTimeMillis()))
            .build();
        
        return json;
    }
    
}
