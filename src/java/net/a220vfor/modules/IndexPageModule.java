
package net.a220vfor.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import net.a220vfor.core.ActionProperties;
import net.a220vfor.core.DB;
import net.a220vfor.core.FilteredHttpRequest;
import net.a220vfor.core.Module;

/**
 *
 * @author Andrew
 */
public class IndexPageModule extends Module {

    private final DataSource pool;
    
    public IndexPageModule(FilteredHttpRequest request) throws ServletException {
        super(request);
        
        DB db = DB.getInstance();
        pool = db.getDataSource();
    }

    @Override
    @ActionProperties(template = "index", method = "BOTH")
    public void index() {
        testDB();
        String sql = "SELECT first_name, last_name FROM actor LIMIT 10";
        try (
                Connection conn = pool.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql)
            ) {

            Map<String, String> actors = new HashMap<>();

            while (result.next()) {
                actors.put(result.getString("first_name"), result.getString("last_name"));
            }

            request.setAttribute("actors", actors);

        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
        }
    }
    
    private void testDB() {
        
        try (Connection conn = pool.getConnection()) {
            
            System.out.println("Current DB (schema) name: " + conn.getSchema());
            System.out.println("Auto commit mode: " + conn.getAutoCommit());
            System.out.println("Current catalog name: " + conn.getCatalog());
            
            
        } catch (SQLException e) {
            System.out.println("SQLException occurred: " + e.getMessage());
        }
        
    }
}
