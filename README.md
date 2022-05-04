# repository
Repo Code:
https://github.com/YashvithaK/repository.git

Download the project code as ZIP

How to run code from zip file: 
	Import the project into IDE
	Maven clean
	Maven install 
	Right click on App.java in package /prac04/src/test/java/za/ac/up/cs/cos221/gui and run as Java Application

Create MariaDB
schema/DB name: u19025492_u20450207_sakila

import sakila-schema.sql to u19025492_u20450207_sakila

import sakila-data.sql to u19025492_u20450207_sakila

login DB:
Host: localhost
port: 13306
user: root
pwd: 

JDBC Url:
	String url = "jdbc:mariadb://localhost:3306/u19025492_u20450207_sakila";

	
Customer: 

Display All Customer data from Customer Table
SQL:
String sql = "select customer_id, first_name, last_name, email, active, store_id, address_id from customer order by first_name";
		
Address:
SQL:
String sql = " SELECT address_id, address, address2, district, city_id, postal_code, phone FROM address order by address ";

Staff:
SQL:
String sql = " SELECT s.staff_id, s.first_name, s.last_name, s.address_id, s.email, s.store_id, s.active, s.username, s.password, "
				+ " a.address address, a.address2, a.district, a.city_id, a.postal_code, a.phone "
				+ " FROM address a, staff s "
				+ " where s.address_id = a.address_id ";

Films:
SQL: 
select film_id, title, description, release_year, language_id, rental_rate, special_features from film order by title   


How to run the Jar(Java Archive) File:

1. Double click JAR will launch the application OR
2. command line run the below command
	 Java -jar DvdApp.jar


In the Application
1. Refresh will load all Tables with Data
2. 		tabPane.addTab("Client List", tablePanel1);
		tabPane.addTab("Staff List", tablePanel2);
		tabPane.addTab("Films List", tablePanel3);
		tabPane.addTab("Inventory List", tablePanel4);
3.Client List:
	Coulmn: First Name Filed can be updated by double clicking on the field
	Delete can be done by right clicking on the selected row

4.Staff List:
	First Name Filed can be updated by double clicking on the field
	Delete can be done by right clicking on the selected row

5.Film List:
	Title Name Filed can be updated by double clicking on the field
	Delete can be done by right clicking on the selected row

6. Window Menu will show/hide customer input form
7. File Menu Exit will terminate DB Connection and prompt the user to Do you really want to exit the application
