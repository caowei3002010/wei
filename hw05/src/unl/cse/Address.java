package unl.cse;

public class Address {
	
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	
	public Address(){
		this.state=this.street=this.city=this.country="";
	}
	
	public Address(String street, String city, String state, String zip, String country){
		this.state=state;
		this.street=street;
		this.city=city;
		this.zip=zip;
		this.country=country;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString(){
		
		String ret=this.street+","+this.city+","+this.state+","+this.zip+","+this.country;
		return ret;
	}
	
}
