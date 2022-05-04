package za.ac.up.cs.cos221.model;

import java.io.Serializable;

public class Film implements Serializable {
	
	private static final long serialVersionUID = -8619218627533074108L;
	private static int count = 1;

	private int filmId;
	private String title;
	private String descr;
	private String specialFeature;
	private String rating;
	
	private int releaseYear;
	private int languageId;
	private int rentalRate;
	
	public Film( String title, String descr, String specialFeature, int releaseYear,
			int languageId, int rentalRate) {
		super();
		this.filmId = count;
		count++;
		this.title = title;
		this.descr = descr;
		this.specialFeature = specialFeature;
//		this.rating = rating;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalRate = rentalRate;
	}
	
	public Film(int filmId, String title, String descr, String specialFeature, int releaseYear,
			int languageId, int rentalRate) {
		this(title, descr, specialFeature, releaseYear, languageId,rentalRate);
		this.filmId=filmId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getSpecialFeature() {
		return specialFeature;
	}

	public void setSpecialFeature(String specialFeature) {
		this.specialFeature = specialFeature;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(int rentalRate) {
		this.rentalRate = rentalRate;
	}
	
}