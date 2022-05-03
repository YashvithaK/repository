package za.ac.up.cs.cos221.prac04;
import java.sql.SQLException;

import za.ac.up.cs.cos221.model.Customer;
import za.ac.up.cs.cos221.model.CustomerDao;
import za.ac.up.cs.cos221.model.Database;


public class TestCustomerDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Running Maria database test");

		Database db = new Database();
		CustomerDao customerDao = new CustomerDao();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		customerDao.addCustomer(new Customer("Yash1", "Kana", "yk1@gmail.com", true, 1, 444));
		customerDao.addCustomer(new Customer("Shamy1", "K", "sh2@gmail.com", false, 2, 55));
		
		try {
			customerDao.customerSave();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			customerDao.load();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.disconnect();
	}
}
