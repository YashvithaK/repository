package za.ac.up.cs.cos221.controller;

import java.sql.SQLException;
import java.util.List;

import za.ac.up.cs.cos221.gui.ClientFormEvent;
import za.ac.up.cs.cos221.model.Address;
import za.ac.up.cs.cos221.model.AddressDao;
import za.ac.up.cs.cos221.model.Customer;
import za.ac.up.cs.cos221.model.CustomerDao;
import za.ac.up.cs.cos221.model.Database;
import za.ac.up.cs.cos221.model.Film;
import za.ac.up.cs.cos221.model.FilmsDao;
import za.ac.up.cs.cos221.model.Staff;
import za.ac.up.cs.cos221.model.StaffDao;

public class Controller {
	Database db = new Database();
	CustomerDao customerDao = new CustomerDao();
	StaffDao staffDao = new StaffDao();
	AddressDao addressDao = new AddressDao();
	FilmsDao filmDao = new FilmsDao();
	
	public List<Film> getFilms() {
		return filmDao.getFilms();
	}
	
	public void removeFilm(int index) {
		filmDao.removeFilm(index);
	}
	
	public List<Customer> getClients() {
		return customerDao.getClients();
	}
	
	public void removeCustomer(int index) {
		customerDao.removeCustomer(index);
	}
	
	public List<Staff> getStaff() {
		return staffDao.getStaff();
	}
	
	public void removeStaff(int index) {
		staffDao.removeStaff(index);
	}
	
	public List<Address> getAddress() {
		return addressDao.getAddress();
	}
	
	public void removeAddress(int index) {
		addressDao.removeAddress(index);
	}
	
	public void saveCustomer() throws SQLException {
		try {
			customerDao.customerSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveStaff() throws SQLException {
		try {
			staffDao.staffSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveAddress() throws SQLException {
		try {
			addressDao.addressSave();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadFilm() throws SQLException {
		try {
			filmDao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadCustomer() throws SQLException {
		try {
			customerDao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStaff() throws SQLException {
		try {
			staffDao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadAddress() throws SQLException {
		try {
			addressDao.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect() throws Exception {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addPerson(ClientFormEvent ev) {
		String firstname = ev.getFirstname();
		String lastname = ev.getLastname();
//		int ageCatId = ev.getAgeCategory();
		String email = ev.getEmail();
		boolean isActive = ev.isActive();
		
		Customer client = new Customer(firstname, lastname, email, isActive, 1, 444);
		
		customerDao.addCustomer(client);
	}
	
//	public void saveToFile(File file) throws IOException {
//		customerDao.saveToFile(file);
//	}

//	public void loadFromFile(File file) throws IOException {
//		customerDao.loadFromFile(file);
//	}
}