package za.ac.up.cs.cos221.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 */

public class InventoryDao{
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	

	//Use the JTabbedPane to add Staff, Films, Inventory and Clients to your application.
    public void getDetails(String tenantDB) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = getDBConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from inventory");
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                System.out.println("customerId = " + customerId);
                int store_id = rs.getInt("store_id");
                System.out.println("store_id = " + store_id);
                String first_name = rs.getString("first_name");
                System.out.println("first_name = " + first_name);
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