package za.ac.up.cs.cos221.model;

import java.io.Serializable;

public class Staff implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 359477231666020873L;

	private static int count = 1;

	private int staffid;
	private String firstname;
	private String lastname;
	private String email;
	private boolean active;
	
	private int storeId;
	private int addressId;

	private String username;
	private String password;
	
	private Address address;
	
//	a.address, a.address2, a.district, a.city_id, a.postal_code, a.phone
	
//	public Staff(String firstname, String lastname, String email, boolean active, int storeId,
//			int addressId, String username, String password) {
//		this.staffid = count;
//		count++;
////		this.staffid = staffid;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//		this.active = active;
//		this.addressId = addressId;
//		this.storeId = storeId;
//		this.username = username;
//		this.password = password;
//	}
	
	public Staff(int id, String firstname, String lastname, String email, boolean active, int storeId, 
										int addressId, String username, String password, Address address) {
		this(firstname, lastname, email, active, storeId, addressId, username, password, address);
		this.staffid=id;
	}
	

	public Staff(String firstname, String lastname, String email, boolean active, int storeId,
			int addressId, String username, String password, Address address) {
		this.staffid = count;
		count++;
//		this.staffid = staffid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.active = active;
		this.storeId = storeId;
		this.addressId = addressId;
		this.username = username;
		this.password = password;
		this.address = address;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Staff [staffid=" + staffid + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", active=" + active + ", storeId=" + storeId + ", addressId=" + addressId + ", username=" + username
				+ "]";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
