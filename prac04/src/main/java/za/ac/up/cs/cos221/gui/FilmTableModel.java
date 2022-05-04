package za.ac.up.cs.cos221.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import za.ac.up.cs.cos221.model.Film;

public class FilmTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5628059517960292560L;

	private List<Film> db;
	
	private String[] colNames = {"Film Id", "Title","Description", "Release Year", "Rental Rate", "Special Features","Language Id"};
//	film_id, title, description, release_year, language_id, rental_rate, special_features
	
	public FilmTableModel() {
	}
	
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}


	public void setData(List<Film> db) {
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
		Film film = db.get(row);
		
		switch(col) {
		case 0:
			return film.getFilmId();
		case 1:
			return film.getTitle();
		case 2:
			return film.getDescr();
		case 3:
			return film.getReleaseYear();
		case 4:
			return film.getRentalRate();
		case 5:
			return film.getSpecialFeature();
		case 6:
			return film.getLanguageId();
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

			Film staff = db.get(row);
			switch(col) {
			case 1:
				staff.setTitle((String)value);
				break;
			default:
				return;
			}
	}
}
