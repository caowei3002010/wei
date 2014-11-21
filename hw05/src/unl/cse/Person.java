package unl.cse;
import java.util.ArrayList;


public class Person {
	
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String> emails;
	
	public Person(){
		this.personCode="";
		this.firstName="";
		this.lastName="";
		this.address=new Address();
		this.emails=new ArrayList<String>();
	}
	
	public Person(String code, String firstName, String lastName, String address, String emails){
		this.personCode=code.trim();
		this.firstName=firstName.trim();
		this.lastName=lastName.trim();
		String[] arr = address.split(",");
		this.address=new Address(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim());
		this.emails = new ArrayList<String>();
		arr = emails.split(",");
		for(int i=0;i<arr.length;i++){
			this.emails.add(arr[i]);
		}
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String code) {
		this.personCode = code;
	}


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getEmail() {
		return emails;
	}
	
	public void setEmail(ArrayList<String> emails) {
		this.emails = emails;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public ArrayList<String> getEmails() {
		return emails;
	}
	
	public void setEmails(ArrayList<String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "Person [personCode=" + personCode + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address
				+ ", emails=" + emails + "]";
	}
	
}
