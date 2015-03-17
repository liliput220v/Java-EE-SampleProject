
package net.a220vfor.core;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 * This class provides access to a database connection pool.
 * 
 * @author Andrew
 * @version 1.0.0
 */
public class DB {
    
    private static DB instance;
    private DataSource dataSource;
    
    private DB() throws ServletException {
        
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MySQL56");
//            dataSource = (DataSource) ctx.lookup("jdbc/MySQL56");
            
        } catch (NamingException e) {
            throw new ServletException("Can't find MySQL DataSource.", e);
        }
    }
    
    /**
     * Returns an instance of the class.
     * 
     * @return
     * @throws ServletException if specified data source cannot be found in the environment context
     */
    public static DB getInstance() throws ServletException {
        
        synchronized (DB.class) {
            if (instance == null) {
                instance = new DB();
            }
        }
        
        return instance;
    }
    
    /**
     * Returns a connection pool.
     * @return 
     */
    public DataSource getDataSource() {
        return dataSource;
    }
}
