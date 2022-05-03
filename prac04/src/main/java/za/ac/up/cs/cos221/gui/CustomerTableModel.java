package za.ac.up.cs.cos221.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import za.ac.up.cs.cos221.model.Customer;

public class CustomerTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7814508831879030177L;

	private List<Customer> db;
	
	private String[] colNames = {"ID", "First Name", "Last Name", "Email", "Active", "Store ID", "Address ID"};
	
	public CustomerTableModel() {
	}
	
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}


	public void setData(List<Customer> db) {
		this.db = db;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	
	@Override
	public Object getValueAt(int row, int col) {
		Customer person = db.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getFirstname();
		case 2:
			return person.getLastname();
		case 3:
			return person.getEmail();
		case 4:
			return person.isActive();
		case 5:
			return person.getStoreId();
		case 6:
			return person.getAddressId();
		}
		
		return null;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		switch(col) {
		case 1:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		if(db == null) return;

			Customer customer = db.get(row);
			
			switch(col) {
			case 1:
				customer.setFirstname((String)value);
				break;
			default:
				return;
			}
			
	}

}
