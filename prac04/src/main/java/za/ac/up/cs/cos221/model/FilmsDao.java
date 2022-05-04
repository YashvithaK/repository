package za.ac.up.cs.cos221.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 */

public class FilmsDao{
	
private List<Film> filmList;
	
	public FilmsDao() {
		filmList = new LinkedList<Film>();
	}
	
	protected Connection getDBConnection() throws Exception {
		Database databaseMariaDb = new Database();
		return databaseMariaDb.connect();
	}
	
	public void load() throws Exception {
		filmList.clear();

//		select film_id, title, description, release_year, language_id, rental_rate, special_features from film order by title
		String sql = " select film_id, title, description, release_year, language_id, rental_rate, special_features "
				+ " FROM film order by title ";
		
		Connection con = getDBConnection();
		Statement selectStatement = con.createStatement();
		
		System.out.println("film Load Query:"+sql);
		ResultSet results = selectStatement.executeQuery(sql);
		while(results.next()) {
			int film_id = results.getInt("film_id");
			String title = results.getString("title");
			String descr = results.getString("description");
			String specialFeature = results.getString("special_features");
			String rating = results.getString("rental_rate");
			
			int releaseYear = 0;
			try {
				releaseYear = results.getInt("release_year");
			} catch (Exception e) {
			}
		
			int languageId = 1;
			try {
				languageId = results.getInt("language_id");
			} catch (Exception e) {
//				System.out.println("Empty languageId.");
			}
			int rentalRate = 0;
			try {
				rentalRate = results.getInt("rentalRate");
			} catch (Exception e) {
//				System.out.println("Empty rentalRate.");
			}
			
			Film film = new Film(film_id, title, descr, specialFeature, releaseYear, languageId,rentalRate);
				
			this.filmList.add(film);
		}
		results.close();
		selectStatement.close();
	}

	public void addFilm(Film film) {
		filmList.add(film);
	}

	public void removeFilm(int index) {
		filmList.remove(index);
	}

	public List<Film> getFilms() {
		return Collections.unmodifiableList(filmList);
	}

	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Film[] customersArray = filmList.toArray(new Film[this.filmList.size()]);

		oos.writeObject(customersArray);

		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Film[] customers = (Film[]) ois.readObject();

			this.filmList.clear();

			this.filmList.addAll(Arrays.asList(customers));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
	}

	//Use the JTabbedPane to add Staff, Films, Inventory and Clients to your application.
    public void getDetails() {
        Connection c = null;
        Statement stmt = null;
        try {
        	c = getDBConnection();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from film");
            while (rs.next()) {
                int key = rs.getInt("film_id");
                System.out.println("film_id = " + key);
                String title = rs.getString("title");
                System.out.println("title = " + title);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}