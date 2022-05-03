package za.ac.up.cs.cos221.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class CustomerDao{
	private List<Customer> customerList;
	
	public CustomerDao() {
		customerList = new LinkedList<Customer>();
//		this.customers = customers;
	}
	
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	

	public void customerSave() throws Exception {

		Connection con = getDBConnection();
		String checkSql = "select count(*) as count from customer where customer_id=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSql);

		//String insertSql = "insert into customer (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String insertSql = "insert into customer (customer_id, first_name, last_name, email, active, store_id, address_id) values (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updateSql = "update customer set first_name=?, last_name=?, email=?, active=?, store_id=?, address_id=? where customer_id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		for (Customer customer : customerList) {
			int id = customer.getId();
			String firstname = customer.getName();
			String lastname = customer.getLastname();
			String email = customer.getEmail();
			boolean isActive = customer.isActive();
			
//			AgeCategory age = customer.getAgeCategory();
//			EmploymentCategory emp = customer.getEmpCat();
			int storeId = customer.getStoreId();
			int addressId = customer.getAddressId();

			checkStmt.setInt(1, id);

			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);
			if (count == 0) {
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, firstname);
				insertStatement.setString(col++, lastname);
				insertStatement.setString(col++, email);
				insertStatement.setBoolean(col++, isActive);
				insertStatement.setInt(col++, storeId);
				insertStatement.setInt(col++, addressId);
				System.out.println("insert query:"+insertSql);
				
				insertStatement.executeUpdate();
			} else {
				int col = 1;
				updateStatement.setString(col++, firstname);
				updateStatement.setString(col++, lastname);
				updateStatement.setString(col++, email);
				updateStatement.setBoolean(col++, isActive);
				updateStatement.setInt(col++, storeId);
				updateStatement.setInt(col++, addressId);
				
				updateStatement.setInt(col++, id);
				updateStatement.executeUpdate();
			}
		}
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
	}
	
	public void load() throws Exception {
		customerList.clear();
		
		String sql = "select customer_id, first_name, last_name, email, active, store_id, address_id from customer order by first_name";
		
		Connection con = getDBConnection();
		Statement selectStatement = con.createStatement();
		
		System.out.println("Customer Load Query:"+sql);
		ResultSet results = selectStatement.executeQuery(sql);
		while(results.next()) {
			int id = results.getInt("customer_id");
			String firstname = results.getString("first_name");
			String lastname = results.getString("last_name");
			String email = results.getString("email");
			boolean active = results.getBoolean("active");
			
			int storeId = results.getInt("store_id");
			int addressId = results.getInt("address_id");
			
//			String gender = results.getString("gender");
//			Customer customer = new Customer(id, name, occ, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxId, isUs, Gender.valueOf(gender));
			Customer customer = new Customer(id, firstname, lastname, email, active, storeId, addressId);
			this.customerList.add(customer);
		}
		results.close();
		selectStatement.close();
	}
	
	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public void removeCustomer(int index) {
		customerList.remove(index);
	}

	public List<Customer> getClients() {
		return Collections.unmodifiableList(customerList);
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Customer[] customersArray = customerList.toArray(new Customer[this.customerList.size()]);

		oos.writeObject(customersArray);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Customer[] customers = (Customer[]) ois.readObject();

			this.customerList.clear();

			this.customerList.addAll(Arrays.asList(customers));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
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
            ResultSet rs = stmt.executeQuery("SELECT * from customer");
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                System.out.println("customerId = " + customerId);
                int store_id = rs.getInt("store_id");
                System.out.println("store_id = " + store_id);
                String first_name = rs.getString("first_name");
                System.out.println("first_name = " + first_name);
                String last_name = rs.getString("last_name");
                System.out.println("last_name = " + last_name);
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