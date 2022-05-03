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
public class AddressDao{
	private List<Address> addressList;
	
	public AddressDao() {
		addressList = new LinkedList<Address>();
	}
	
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	
	public void addressSave() throws Exception {

		Connection con = getDBConnection();
		String checkSql = "select count(*) as count from address where address_id=?";

		PreparedStatement checkStmt = con.prepareStatement(checkSql);
//		INSERT INTO sakila.address
//		(address, address2, district, city_id, postal_code, phone, last_update)
//		VALUES('', NULL, '', 0, NULL, '', current_timestamp());
		String insertSql = "insert into address"
				+ " (address, address2, district, city_id, postal_code, phone) "
				+ " values(?, ?, ?, ?, ?, ?) ";

		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
//		UPDATE sakila.address
//		SET address='', address2=NULL, district='', city_id=0, postal_code=NULL, phone='', last_update=current_timestamp()
//		WHERE address_id=0;
		String updateSql = "update address "
							+ " SET address=?, address2=?, district=?, city_id=?, postal_code=?, phone=? "
							+ " where address_id=? ";
		
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		for (Address addr : addressList) {
			int id = addr.getId();
			String address1 = addr.getAddress1();
			String address2 = addr.getAddress2();
			String district = addr.getDistrict();

			int cityId = addr.getCityId();
			int postalCode = addr.getPostalCode();
			int phone = addr.getPhone();
			
			checkStmt.setInt(1, id);

			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();

			int count = checkResult.getInt(1);
			if (count == 0) {
				int col = 1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, address1);
				insertStatement.setString(col++, address2);
				insertStatement.setString(col++, district);
				insertStatement.setInt(col++, cityId);
				insertStatement.setInt(col++, postalCode);
				insertStatement.setInt(col++, phone);
				System.out.println("insert query:"+insertSql);
				
				insertStatement.executeUpdate();
			} else {
				int col = 1;
				updateStatement.setString(col++, address1);
				updateStatement.setString(col++, address2);
				updateStatement.setString(col++, district);
				updateStatement.setInt(col++, cityId);
				updateStatement.setInt(col++, postalCode);
				updateStatement.setInt(col++, phone);
				
				updateStatement.setInt(col++, id);
				updateStatement.executeUpdate();
			}
		}
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
	}
	
	public void load() throws Exception {
		addressList.clear();

//		SELECT address_id, address, address2, district, city_id, postal_code, phone, last_update
//		FROM sakila.address;
		String sql = " SELECT address_id, address, address2, district, city_id, postal_code, phone "
				+ " FROM address order by address ";
		
		Connection con = getDBConnection();
		Statement selectStatement = con.createStatement();
		
		System.out.println("Address Load Query:"+sql);
		ResultSet results = selectStatement.executeQuery(sql);
		while(results.next()) {
			int id = results.getInt("address_id");
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
			
			Address address = new Address(id, address1, address2, district, city_id, postal_code, phone);
			this.addressList.add(address);
		}
		results.close();
		selectStatement.close();
	}
	
	public void addAddress(Address addr) {
		addressList.add(addr);
	}

	public void removeAddress(int index) {
		addressList.remove(index);
	}

	public List<Address> getAddress() {
		return Collections.unmodifiableList(addressList);
	}

    public void getDetails() {
        Connection c = null;
        Statement stmt = null;
        try {
            c = ConnectionMariaDB.getConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * from address");
            while (results.next()) {
//        		int key = results.getInt("address_id");
    			String address1 = results.getString("address");
    			System.out.println("Address:"+address1);
    			String address2 = results.getString("address2");
    			System.out.println("Address2:"+address2);
    			String district = results.getString("district");
    			System.out.println("district:"+district);
    			int city_id = results.getInt("city_id");
    			System.out.println("city_id:"+city_id);
    			int postal_code = 0;
    			try {
        			postal_code = results.getInt("postal_code");
        			System.out.println("postal_code:"+postal_code);
				} catch (Exception e) {
					System.out.println("Empty Postal Code");
				}
    			
    			int phone = 0;
    			try {
        			phone = results.getInt("phone");
        			System.out.println("phone:"+phone);
				} catch (Exception e) {
					System.out.println("Empty Phone No.");
				}
                System.out.println();
            }
            results.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}