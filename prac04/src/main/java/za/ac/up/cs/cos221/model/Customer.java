package za.ac.up.cs.cos221.model;

import java.io.Serializable;

public class Customer implements Serializable {
	
	private static final long serialVersionUID = -8219218627533074108L;
 
	private static int count = 1;
	
	private int customerid;
	private String firstname;
	private String lastname;
	private String email;
	private boolean active;
	
	private int storeId;
	private int addressId;
	
	public Customer(String firstname,String lastname, String email,  boolean active, int storeId, int addressId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.active = active;
		
		this.storeId = storeId;
		this.addressId = addressId;
		
		this.customerid = count;
		count++;
	}
	
	public Customer(int id, String firstname, String lastname, String email, boolean active, int storeId, int addressId) {
		
		this(firstname, lastname, email, active, storeId, addressId);

		this.customerid=id;
	}
	
	public int getId() {
		return customerid;
	}
	public void setId(int id) {
		this.customerid = id;
	}
	public String getName() {
		return firstname;
	}
	public void setName(String name) {
		this.firstname = name;
	}
	public String getOccupation() {
		return email;
	}
	public void setOccupation(String occupation) {
		this.email = occupation;
	}

	public boolean isUsCitizen() {
		return active;
	}
	public void setUsCitizen(boolean usCitizen) {
		this.active = usCitizen;
	}
//	public Gender getGender() {
//		return gender;
//	}
//	public void setGender(Gender gender) {
//		this.gender = gender;
//	}
//	
	public String toString() {
		return customerid + ": " + firstname+": " + lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
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
}
