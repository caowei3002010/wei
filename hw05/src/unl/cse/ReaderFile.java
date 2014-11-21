package unl.cse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ReaderFile {
	
	public static String driver = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://localhost:3306/wcao";
	public static String user = "wcao";
	public static String password = "ZqfP{C";
	
	public static DSLinkedList readInvoice(ArrayList<Person> persons,ArrayList<Customer> customers,ArrayList<Product> products) throws IOException{
		
		DSLinkedList ret = new DSLinkedList();

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String sql = "select * from invoice";
			ResultSet rs = statement.executeQuery(sql);  

			while(rs.next()) {
				
				String code=rs.getString("invoiceCode");
				String personCode=rs.getString("personCode");
				String customerCode = rs.getString("customerCode");
				Customer c = Invoice.getCustomer(customers, customerCode);
				Invoice invoice = new Invoice(code,personCode,customerCode,persons,customers);
				
				sql = "select * from item where invoiceID='"+code+"'";
				ResultSet es = statement2.executeQuery(sql);
				while(es.next()){
					
					Item item = null;
					int type=es.getInt("itemType");
					String productCode = es.getString("productCode");
					Product pro = Invoice.getProduct(products,productCode);
					
					if(type==1){
						int unit = es.getInt("units");
						item  = new EquipmentItem(pro, c, unit);
					}else if(type==2){
						int hour = es.getInt("hours");
						item = new ConsultationItem(pro, c,hour);
					}else{
						item = new LicenseItem(pro, c, es.getString("startDate"), es.getString("endDate"));
					}
					
					invoice.addItem(item);
				}
				es.close();
				ret.add(invoice);
			}
			rs.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return ret;
	}
	public static ArrayList<Person> readPersons() throws IOException{
		
		ArrayList<Person> persons = new ArrayList<Person>();

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			Statement statement2 = conn.createStatement();
			String sql = "select * from person";
			ResultSet rs = statement.executeQuery(sql);  

			while(rs.next()) {
				
				String code;
				String firstName;
				String lastName;
				String address;
				String email;
				code = rs.getString("personCode");
				firstName =rs.getString("firstName");
				lastName=rs.getString("lastName");
				address= rs.getString("street") +","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("zip")+","+rs.getString("country");
				sql = "select * from email where personCode='"+code+"'";
				ResultSet es = statement2.executeQuery(sql);
				email = "";
				while(es.next()){
					email+=es.getString("emailAddress")+",";
				}
				es.close();
				if(email.isEmpty()==false){
					email = email.substring(0, email.length()-1);
				}
				persons.add(new Person(code,firstName,lastName,address,email));
				
			}
			rs.close();  
			conn.close();  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return persons;
	}
	
	public static ArrayList<Customer> readCustomers(ArrayList<Person> persons) throws IOException{
		
		ArrayList<Customer> customers = new ArrayList<Customer>();

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "select * from customer";
			ResultSet rs = statement.executeQuery(sql);  
			while(rs.next()){
				String code;
				String contact;
				String name;
				String address;
				int type;
				Customer customer;
				Person person=null;
				code = rs.getString("customerCode");
				contact = rs.getString("primaryContact");
				name = rs.getString("name");
				address= rs.getString("street") +","+rs.getString("city")+","+rs.getString("state")+","+rs.getString("zip")+","+rs.getString("country");
				type = rs.getInt("customerType");
				
				for(int j=0;j<persons.size();j++){
					if(persons.get(j).getPersonCode().equals(contact)){
						person=persons.get(j);
						break;
					}
					
				}
				if(type==1){
					customer = new Government(code,person,name,address);
				}else{
					customer = new Corporate(code,person,name,address);
				}
				customers.add(customer);
				
			}
			rs.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return customers;
	}
	
	public static ArrayList<Product> readProducts(ArrayList<Person> persons) throws IOException{
		
		ArrayList<Product> products = new ArrayList<Product>();

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement statement = conn.createStatement();
			String sql = "select * from product";
			ResultSet rs = statement.executeQuery(sql);  
			while(rs.next()){
				
				String code, name;
				Product p;
				code=rs.getString("productCode");
				name=rs.getString("name");
				int type = rs.getInt("productType");
				
				if(type==1){				
					double price = rs.getDouble("pricePerUnit");
					p = new Equipment(code,name,price);
					
				}else if(type==3){
					double serviceFee=rs.getDouble("serviceFee");
					double annualLicenseFee=rs.getDouble("annualLicenseFee");
					p = new License(code,name,serviceFee,annualLicenseFee);
					
				}else{
					String consultantPersonCode=rs.getString("consultant");
					double hourlyFee=rs.getDouble("hourlyFee");
					Person person=null;
					for(int j=0;j<persons.size();j++){
						if(persons.get(j).getPersonCode().equals(consultantPersonCode)){
							person=persons.get(j);
							break;
						}
					}
					p= new Consultation(code,name,person,hourlyFee);
				}
				products.add(p);
			}
			rs.close();
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return products;
	}


}
