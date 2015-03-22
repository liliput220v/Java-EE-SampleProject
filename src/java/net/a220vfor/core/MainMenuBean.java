
package net.a220vfor.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * Main menu
 * @author Andrew
 */
public class MainMenuBean {
    
    private final DataSource pool;
    
    private List<String[]> menuItems;
    private List<String[]> visibleMenuItems;
    
    public MainMenuBean() throws ServletException{
        DB db = DB.getInstance();
        pool = db.getDataSource();
    }
    
    /**
     * Retrieves all menu items, visible and invisible ones.
     * 
     * @return a list of menu items; each list element contains an array with item name (at index 0) 
     *         and its link (at index 1).
     */
    public List<String[]> getMenuItems() {
        
        menuItems = new ArrayList<>();
        String sql = "SELECT item_name, item_link FROM j220v_sample_mvc.menu "
                    + "ORDER BY item_position, item_name ASC";
        
        try (
                Connection conn = pool.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
            ) {
            
            while (result.next()) {
                menuItems.add(new String[] {
                    result.getString("item_name"),
                    result.getString("item_link")
                });
            }
            
        } catch (SQLException e) {
            System.out.println("Can't retrieve menu items: " + e.getMessage());
        }
        
        return menuItems;
    }
    
    /**
     * Retrieves all visible menu items.
     * 
     * @return a list of menu items; each list element contains an array with item name (at index 0) 
     *         and its link (at index 1).
     */
    public List<String[]> getVisibleMenuItems() {
        
        visibleMenuItems = new ArrayList<>();
        String sql = "SELECT item_name, item_link FROM j220v_sample_mvc.menu "
                    + "WHERE item_visible = 1 "
                    + "ORDER BY item_position, item_name ASC";
        
        try (
                Connection conn = pool.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery(sql);
            ) {
            
            while (result.next()) {
                visibleMenuItems.add(new String[] {
                    result.getString("item_name"),
                    result.getString("item_link")
                });
            }
            
        } catch (SQLException e) {
            System.out.println("Can't retrieve menu items: " + e.getMessage());
        }
        
        return visibleMenuItems;
        
    }
}
