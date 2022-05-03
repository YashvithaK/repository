package za.ac.up.cs.cos221.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Connect to Database
 */
public class ConnectionMariaDB { 
	/*LocalHost*/
	public static final String USER = "root";
	public static final String PASS = "root";
	public static final String db = "sakila";
	
	public static final String URL = "jdbc:mariadb://localhost:3307/";

    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection() throws ClassNotFoundException {
      try {
    	  return DriverManager.getConnection(URL+db, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }

    /**
     * Test Connection
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	Connection connection = ConnectionMariaDB.getConnection();
    	System.out.println("Test MariaDB Connection:"+connection.getMetaData().getDriverVersion());
//    	FilmsDao tableInfoDaoImpl = new FilmsDao();
//    	tableInfoDaoImpl.getFilmDetails(db);
//    	InventoryDao inventoryDao = new InventoryDao();
//    	CustomerDao dao = new CustomerDao();
//    	StaffDao dao = new StaffDao();
//    	dao.getDetails(db);
    	AddressDao dao = new AddressDao();
    	dao.getDetails();
    }
}