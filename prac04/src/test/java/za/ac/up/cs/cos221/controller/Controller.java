package za.ac.up.cs.cos221.controller;

import java.sql.SQLException;
import java.util.List;

import za.ac.up.cs.cos221.gui.ClientFormEvent;
import za.ac.up.cs.cos221.model.Address;
import za.ac.up.cs.cos221.model.AddressDao;
import za.ac.up.cs.cos221.model.Customer;
import za.ac.up.cs.cos221.model.CustomerDao;
import za.ac.up.cs.cos221.model.Database;
import za.ac.up.cs.cos221.model.Staff;
import za.ac.up.cs.cos221.model.StaffDao;

public class Controller {
	Database db = new Database();
	CustomerDao customerDao = new CustomerDao();
	StaffDao staffDao = new StaffDao();
	AddressDao addressDao = new AddressDao();
	
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
//		String taxId = ev.getTaxId();
//		int storeId = Integer.valueOf(taxId).intValue();
		
//		String gender = ev.getGender();
//		int addressId = Integer.valueOf(gender).intValue();
		
//		AgeCategory ageCategory = null;
		
//		switch(ageCatId) {
//		case 0:
//			ageCategory = AgeCategory.child;
//			break;
//		case 1:
//			ageCategory = AgeCategory.adult;
//			break;
//		case 2:
//			ageCategory = AgeCategory.senior;
//			break;
//		}
//		
//		EmploymentCategory empCategory;
//		
//		if(empCat.equals("employed")) {
//			empCategory = EmploymentCategory.employed;
//		}
//		else if(empCat.equals("self-employed")) {
//			empCategory = EmploymentCategory.selfEmployed;
//		}
//		else if(empCat.equals("unemployed")) {
//			empCategory = EmploymentCategory.unemployed;
//		}
//		else {
//			empCategory = EmploymentCategory.other;
//			System.err.println(empCat);
//		}
		
//		Gender genderCat;
//		
//		if(gender.equals("male")) {
//			genderCat = Gender.male;
//		}
//		else {
//			genderCat = Gender.female;
//		}
		
		Customer person = new Customer(firstname, lastname, email, isActive, 1, 444);
		
		customerDao.addCustomer(person);
	}
	
	
//	public void saveToFile(File file) throws IOException {
//		customerDao.saveToFile(file);
//	}

//	public void loadFromFile(File file) throws IOException {
//		customerDao.loadFromFile(file);
//	}
}