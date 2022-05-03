package za.ac.up.cs.cos221.model;

import java.io.Serializable;

public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2654179237802906956L;
	private static int count = 1;
	private int id;

	private String address1;
	private String address2;
	private String district;
	
	private int cityId;
	private int postalCode;
	private int phone;
	
	public Address(String address1, String address2, String district, int cityId, int postalCode, int phone) {
		super();
		this.id = count;
		count++;
//		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.district = district;
		this.cityId = cityId;
		this.postalCode = postalCode;
	}
	
	public Address(int id, String address1, String address2, String district, int cityId, int postalCode, int phone) {
		this(address1, address2, district, cityId, postalCode, phone);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", address2=" + address2 + ", district=" + district
				+ ", cityId=" + cityId + ", postalCode=" + postalCode + "]";
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}