package za.ac.up.cs.cos221.prac04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import za.ac.up.cs.cos221.model.Address;
import za.ac.up.cs.cos221.model.AddressDao;
/**
 * Connect to Database
 */
public class TestAddressDao { 
	/*LocalHost*/
	public static final String USER = "root";
	public static final String PASS = "";
	public static final String db = "sakila";
	
	public static final String URL = "jdbc:mariadb://localhost:3306/";

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
    	Connection connection = TestAddressDao.getConnection();
    	System.out.println("Test MariaDB Connection:"+connection.getMetaData().getDriverVersion());
//    	FilmsDao tableInfoDaoImpl = new FilmsDao();
//    	tableInfoDaoImpl.getFilmDetails(db);
//    	InventoryDao inventoryDao = new InventoryDao();
//    	CustomerDao dao = new CustomerDao();
//    	StaffDao dao = new StaffDao();
//    	dao.getDetails();
//    	try {
//			dao.load();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    	List<Staff> staf = dao.getStaff();
//    	for (Staff staff : staf) {
//			System.out.println(staff.getFirstname());
//			System.out.println(staff.getLastname());
//			System.out.println(staff.getEmail());
//			System.out.println(staff.getAddressId());
//			System.out.println(staff.isActive());
//			System.out.println(staff.getUsername());
//    	}
    	
    	AddressDao dao = new AddressDao();
    	try {
			dao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	List<Address> addrList = dao.getAddress();
    	for (Address addr : addrList) {
			System.out.println(addr.getAddress1());
			System.out.println(addr.getAddress2());
			System.out.println(addr.getDistrict());
			System.out.println(addr.getCityId());
			System.out.println(addr.getPostalCode());
    	}
    }
    
}