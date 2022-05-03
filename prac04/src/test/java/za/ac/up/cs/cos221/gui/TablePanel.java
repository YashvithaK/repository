package za.ac.up.cs.cos221.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import za.ac.up.cs.cos221.model.Customer;

public class TablePanel extends JPanel {
	
	/**
	 * Customer / Client Table 
	 */
	private static final long serialVersionUID = -7692471859294446411L;
	
	private JTable table;
	private CustomerTableModel customerTableModel;
	private JPopupMenu popup;
	private CustomerTableListener personTableListener;
	
	public TablePanel() {
		
		customerTableModel = new CustomerTableModel();
		table = new JTable(customerTableModel);
		popup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete row");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
				int row = table.rowAtPoint(e.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);

				if(e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				
				if(personTableListener != null) {
					personTableListener.rowDeleted(row);
					customerTableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Customer> db) {
		customerTableModel.setData(db);
	}
	
	public void refresh() {
		customerTableModel.fireTableDataChanged();
	}
	
	public void setPersonTableListener(CustomerTableListener listener) {
		this.personTableListener = listener;
	}
}