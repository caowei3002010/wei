package com.cinco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 *
 */
public class InvoiceData {

	/**
	 * Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql1 = "DELETE FROM `email`";
			String sql2 = "DELETE FROM `item`";
			String sql3 = "DELETE FROM `invoice`";
			String sql4 = "DELETE FROM `person`";
			
			statement.executeQuery(sql1);  
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			statement.executeQuery(sql4); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Removes the person record from the database corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 */
	public static void removePerson(String personCode) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql1 = "DELETE FROM `email` WHERE personCode='"+personCode+"'";
			String sql2 = "DELETE FROM `item` WHERE invoiceID in(select invoiceCode from invoice where personCode='" + personCode+"')";
			String sql3 = "DELETE FROM `invoice` WHERE personCode='"+personCode+"'";
			String sql4 = "DELETE FROM `person` WHERE personCode='"+personCode+"'";
			
			statement.executeQuery(sql1);  
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			statement.executeQuery(sql4); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to add a person record to the database with the provided data. 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, 
			String street, String city, String state, String zip, String country) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql = "INSERT INTO person(personCode,firstName,lastName,street,city,state,zip,country) " +
					"VALUES('"+personCode+"','" + firstName+"','" +lastName+"','" +street+"','" +city+"','" +state+"','" +zip+"','" +country+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql = "INSERT INTO email(emailAdress,personCode) VALUES('"+email+"','"+personCode+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql2 = "DELETE FROM `item`";
			String sql3 = "DELETE FROM `invoice`";
			String sql4 = "DELETE FROM `customer`";
			 
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			statement.executeQuery(sql4); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void addCustomer(String customerCode, String type, String primaryContactPersonCode, String name, 
			String street, String city, String state, String zip, String country) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			int customerType;
			if(type.equals("G")){
				customerType=1;
			}else{
				customerType=2;
			}

			String sql = "INSERT INTO customer(customerCode,name,customerType,primaryContact,street,city,state,zip,country) " +
					"VALUES('"+customerCode+"','" + name+"','"+ customerType +"','"+primaryContactPersonCode+"','" +street+"','" +city+"','" +state+"','" +zip+"','" +country+"')";
			
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql2 = "DELETE FROM `item`";
			String sql3 = "DELETE FROM `invoice`";
			String sql4 = "DELETE FROM `product`";
			 
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			statement.executeQuery(sql4); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Removes a particular product record from the database corresponding to the
	 * provided <code>productCode</code>
	 * @param assetCode
	 */
	public static void removeProduct(String productCode) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql2 = "DELETE FROM `item` WHERE productCode='"+productCode+"'";
			String sql4 = "DELETE FROM `product` WHERE productCode='"+productCode+"'";
			 
			statement.executeQuery(sql2); 
			statement.executeQuery(sql4); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Adds an equipment record to the database with the
	 * provided data.  
	 */
	public static void addEquipment(String productCode, String name, Double pricePerUnit) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO product(productCode,name,pricePerUnit,productType) VALUES('"+productCode+"','"+name+"','"+pricePerUnit+"',1)";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an license record to the database with the
	 * provided data.  
	 */
	public static void addLicense(String productCode, String name, double serviceFee, double annualFee) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO product(productCode,name,serviceFee,annualLicenseFee,productType) VALUES('"+productCode+"','"+name+"','"+serviceFee+"','"+annualFee+"',3)";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Adds an consultation record to the database with the
	 * provided data.  
	 */
	public static void addConsultation(String productCode, String name, String consultantPersonCode, Double hourlyFee) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO product(productCode,name,consultant,hourlyFee,productType) VALUES('"+productCode+"','"+name+"','"+consultantPersonCode+"','"+hourlyFee+"',2)";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql2 = "DELETE FROM `item`";
			String sql3 = "DELETE FROM `invoice`";
			 
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes the invoice record from the database corresponding to the
	 * provided <code>invoiceCode</code>
	 * @param invoiceCode
	 */
	public static void removeInvoice(String invoiceCode) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();

			String sql2 = "DELETE FROM `item` WHERE invoiceID='"+invoiceCode+"'";
			String sql3 = "DELETE FROM `invoice` WHERE invoiceCode='"+invoiceCode+"'";
			 
			statement.executeQuery(sql2); 
			statement.executeQuery(sql3); 
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an invoice record to the database with the given data.  
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO invoice(invoiceCode,customerCode,personCode) VALUES('"+invoiceCode+"','"+customerCode+"','"+salesPersonCode+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of units
	 */
	public static void addEquipmentToInvoice(String invoiceCode, String productCode, int numUnits) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO item(productCode,units,invoiceID) VALUES('"+productCode+"','"+numUnits+"','"+invoiceCode+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * begin/end dates
	 */
	public static void addLicenseToInvoice(String invoiceCode, String productCode, String startDate, String endDate) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO item(productCode,startDate,endDate,invoiceID) VALUES('"+productCode+"','"+startDate+"','" +"','"+endDate+"','"+invoiceCode+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Adds a particular equipment (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of billable hours.
	 */
	public static void addConsultationToInvoice(String invoiceCode, String productCode, double numHours) {
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/invoicedb";
		String user = "root";
		String password = "66wz66wz";

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO item(productCode,hours,invoiceID) VALUES('"+productCode+"','"+numHours+"','"+invoiceCode+"')";
			statement.execute(sql);
			
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
