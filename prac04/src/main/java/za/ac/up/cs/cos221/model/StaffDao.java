package za.ac.up.cs.cos221.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 */
public class StaffDao{
	private List<Staff> staffList;
	
	public StaffDao() {
		staffList = new LinkedList<Staff>();
	}
	
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	
	public void staffSave() throws Exception {

		Connection con = getDBConnection();
		String checkSql = "select count(*) as count from staff where staff_id=?";

		PreparedStatement checkStmt = con.prepareStatement(checkSql);

		String insertSql = "insert into staff(first_name, last_name, email, address_id, store_id, active, username, password) values "
												+ " (?, ?, ?, ?, ?, ?, ?, ? ) ";

		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
		String updateSql = "update staff "
							+ " SET first_name=?, last_name=?, email=?, address_id=?, store_id=?, active=?, username=?, password=? "
							+ " where staff_id=? ";
		
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		for (Staff staff : staffList) {
			int id = staff.getStaffid();
			String firstname = staff.getFirstname();
			String lastname = staff.getLastname();
			String email = staff.getEmail();

			int addressId = staff.getAddressId();
			int storeId = staff.getStoreId();
			boolean isActive = staff.isActive();

			String username = staff.getUsername();
			String password = staff.getPassword();
			
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
				insertStatement.setInt(col++, addressId);
				insertStatement.setInt(col++, storeId);
				insertStatement.setBoolean(col++, isActive);
				insertStatement.setString(col++, username);
				insertStatement.setString(col++, password);

				System.out.println("insert query:"+insertSql);
				
				insertStatement.executeUpdate();
			} else {
				int col = 1;
				updateStatement.setString(col++, firstname);
				updateStatement.setString(col++, lastname);
				updateStatement.setString(col++, email);
				updateStatement.setInt(col++, addressId);
				updateStatement.setInt(col++, storeId);
				updateStatement.setBoolean(col++, isActive);
				updateStatement.setString(col++, username);
				updateStatement.setString(col++, password);
				
				updateStatement.setInt(col++, id);
				updateStatement.executeUpdate();
			}
		}
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
	}
	
	public void load() throws Exception {
		staffList.clear();
		
//		SELECT s.staff_id, s.first_name, s.last_name, s.address_id, s.email, s.store_id, s.active, s.username, s.password,
//		a.address, a.address2, a.district, a.city_id, a.postal_code, a.phone 
//		FROM sakila.address a, sakila.staff s
//		where a.address_id = s.address_id;		
		
//		String sql = " SELECT staff_id, first_name, last_name, email, address_id, store_id, active, username, password "
//				+ " FROM staff order by first_name ";

		String sql = " SELECT s.staff_id, s.first_name, s.last_name, s.address_id, s.email, s.store_id, s.active, s.username, s.password, "
				+ " a.address address, a.address2, a.district, a.city_id, a.postal_code, a.phone "
				+ " FROM address a, staff s "
				+ " where s.address_id = a.address_id ";
		
		Connection con = getDBConnection();
		Statement selectStatement = con.createStatement();
		
		System.out.println("Staff Load Query:"+sql);
		ResultSet results = selectStatement.executeQuery(sql);
		while(results.next()) {
			int id = results.getInt("staff_id");
			String firstname = results.getString("first_name");
			String lastname = results.getString("last_name");
			String email = results.getString("email");
			
			int storeId = results.getInt("store_id");
			int addressId = results.getInt("address_id");
			boolean active = results.getBoolean("active");

			String username = results.getString("username");
			String password = results.getString("password");
			
			//Extracting Address details
//			int address_id = results.getInt("address_id");
			String address1 = results.getString("address");
			String address2 = results.getString("address2");
			String district = results.getString("district");
			
			int city_id = results.getInt("city_id");
			int postal_code = 0;
			try {
				postal_code = results.getInt("postal_code");
			} catch (Exception e) {
//				System.out.println("Empty Postal code.");
			}
			int phone = 0;
			try {
				phone = results.getInt("phone");
			} catch (Exception e) {
//				System.out.println("Empty Phone No.");
			}
			
			Address address = new Address(addressId, address1, address2, district, city_id, postal_code, phone);
			
			Staff staff = new Staff(id, firstname, lastname, email, active, storeId, addressId, username, password, address);

			this.staffList.add(staff);
		}
		results.close();
		selectStatement.close();
	}
	
	public void addStaff(Staff staff) {
		staffList.add(staff);
	}

	public void removeStaff(int index) {
		staffList.remove(index);
	}

	public List<Staff> getStaff() {
		return Collections.unmodifiableList(staffList);
	}
	
	//Use the JTabbedPane to add Staff, Films, Inventory and Clients to your application.
    public void getDetails() {
        Connection c = null;
        Statement stmt = null;
        try {
//            c = ConnectionMariaDB.getConnection();
	          c = getDBConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from staff");
            while (rs.next()) {
                int key = rs.getInt("staff_id");
                System.out.println("staff_id = " + key);
                String firstName = rs.getString("first_name");
                System.out.println("firstName" + firstName);
                String lastName = rs.getString("last_name");
                System.out.println("lastName" + lastName);
                String addressId = rs.getString("address_id");
                System.out.println("addressId" + addressId);
                String email = rs.getString("email");
                System.out.println("email" + email);
                String storeid = rs.getString("store_id");
                System.out.println("storeid" + storeid);
                String active = rs.getString("active");
                System.out.println("active" + active);
                String username = rs.getString("username");
                System.out.println("username" + username);
//                String password = rs.getString("password");
//                System.out.println("password" + password);
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