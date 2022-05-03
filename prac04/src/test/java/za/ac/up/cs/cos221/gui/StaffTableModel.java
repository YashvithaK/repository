package za.ac.up.cs.cos221.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import za.ac.up.cs.cos221.model.Staff;

public class StaffTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5628059517960292560L;

	private List<Staff> db;
	
	private String[] colNames = {"First Name", "Last Name","Address", "Address2", "District","City", "Postal Code", "Store ID"};
//	a.address, a.address2, a.district, a.city_id, a.postal_code, a.phone 
	
	public StaffTableModel() {
	}
	
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}


	public void setData(List<Staff> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Staff staff = db.get(row);
		
		switch(col) {
		case 0:
			return staff.getFirstname();
		case 1:
			return staff.getLastname();
		case 2:
			return staff.getAddress().getAddress1();
		case 3:
			return staff.getAddress().getAddress2();
		case 4:
			return staff.getAddress().getDistrict();
		case 5:
			return staff.getAddress().getCityId();
		case 6:
			return staff.getAddress().getPostalCode();
		case 7:
			return staff.getStoreId();
		}
//		case 8:
//		return staff.getEmail();
		return null;
	}


	@Override
	public boolean isCellEditable(int row, int col) {
		switch(col) {
		case 0:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if(db == null) return;

			Staff staff = db.get(row);
			
			switch(col) {
			case 1:
				staff.setFirstname((String)value);
				break;
			default:
				return;
			}
	}
}
