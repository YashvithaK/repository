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

import za.ac.up.cs.cos221.model.Film;

public class TablePanel3 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3086828400270536726L;
	/**
	 *  Films  Table Panel 
	 */
	private JTable table;
	private FilmTableModel tableModel;
	private JPopupMenu popup;
	private FilmTableListener filmTableListener;
	
	public TablePanel3() {
		
		tableModel = new FilmTableModel();
		table = new JTable(tableModel);
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
				
				if(filmTableListener != null) {
					filmTableListener.rowDeleted(row);
					tableModel.fireTableRowsDeleted(row, row);
				}
			}
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Film> db) {
		tableModel.setData(db);
	}
	 
	public void refresh() {
		tableModel.fireTableDataChanged();
	}
	
	public void setStaffTableListener(FilmTableListener listener) {
		this.filmTableListener = listener;
	}
}
