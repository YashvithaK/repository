package za.ac.up.cs.cos221.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 */

public class FilmsDao{
	
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	

	//Use the JTabbedPane to add Staff, Films, Inventory and Clients to your application.
    public void getDetails(String tenantDB) {
        Connection c = null;
        Statement stmt = null;
        try {
//            c = ConnectionMariaDB.getConnection();
        	c = getDBConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from film");
            while (rs.next()) {
                int key = rs.getInt("film_id");
                System.out.println("film_id = " + key);
                String title = rs.getString("title");
                System.out.println("title = " + title);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}