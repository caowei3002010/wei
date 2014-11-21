package unl.cse;

public class Customer {
	
	private String customerCode;
	private String name;
	private Address address;
	private Person primaryContact;
	
	public Customer(){
		this.customerCode="";
		this.primaryContact=new Person();
		this.name="";
		this.address = new Address();
	}
	
	public Customer(String code, Person contact, String name, String address){
		this.customerCode=code.trim();
		this.primaryContact=contact;
		this.name=name.trim();
		String[] arr = address.split(",");
		this.address=new Address(arr[0].trim(), arr[1].trim(), arr[2].trim(), arr[3].trim(), arr[4].trim());
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String code) {
		this.customerCode = code;
	}

	public Person getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(Person contact) {
		this.primaryContact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		
		String ret="";
		Address address = this.getPrimaryContact().getAddress();
		ret+="Customer Info:\n";
		ret+=" "+this.getName()+" (" + this.getCustomerCode() + ")\n";
		ret+=" "+this.getPrimaryContact().getLastName() + "," + this.getPrimaryContact().getFirstName()+"\n";
		ret+=" "+address.getStreet() + "\n";
		ret+=" "+address.getCity() +" "+address.getState() +" "+address.getZip() + " " +address.getCountry();
		return ret;
	}
}
