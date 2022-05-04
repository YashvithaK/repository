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
    public void getDetails() {
        Connection c = null;
        Statement stmt = null;
        try {
            c = getDBConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from inventory");
            while (rs.next()) {
                int inventory_id = rs.getInt("inventory_id");
                System.out.println("customerId = " + inventory_id);
                int film_id = rs.getInt("film_id");
                System.out.println("film_id = " + film_id);
                String store_id = rs.getString("store_id");
                System.out.println("store_id = " + store_id);
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