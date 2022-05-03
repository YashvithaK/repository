package za.ac.up.cs.cos221.gui;
import java.util.EventObject;

public class ClientFormEvent extends EventObject {

//	private String name;
//	private String occupation;
//	private int ageCategory;
//	private String empCat;
//	private String taxId;
//	private boolean usCitizen;
//	private String gender;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7263187207420044313L;

	private int customerid;

	private String firstname;
	private String lastname;
	private String email;
	private boolean active;
	
	private int storeId;
	private int addressId;
	
	public ClientFormEvent(Object source) {
		super(source);
	}

	public ClientFormEvent(Object source, String firstname, String lastname, String email,
			boolean active, int storeId, int addressId) {
		super(source);
//		this.customerid = customerid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.active = active;
		this.storeId = storeId;
		this.addressId = addressId;
	}

//	public ClientFormEvent(Object source, String name, String occupation, int ageCat,
//			String empCat, String taxId, boolean usCitizen, String gender) {
//		super(source);
//
//		this.name = name;
//		this.occupation = occupation;
//		this.ageCategory = ageCat;
//		this.empCat = empCat;
//		this.taxId = taxId;
//		this.usCitizen = usCitizen;
//		this.gender = gender;
//	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
//	public String getGender() {
//		return gender;
//	}
//
//	public String getTaxId() {
//		return taxId;
//	}
//
//	public boolean isUsCitizen() {
//		return usCitizen;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getOccupation() {
//		return occupation;
//	}
//
//	public void setOccupation(String occupation) {
//		this.occupation = occupation;
//	}
//
//	public int getAgeCategory() {
//		return ageCategory;
//	}
//
//	public String getEmploymentCategory() {
//		return empCat;
//	}

}
