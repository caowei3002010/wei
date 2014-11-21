package unl.cse;

public class Product {
	
	private String productCode;
	private String name;
	
	public Product(){
		this.productCode="";
		this.name="";
	}
	
	public Product(String code, String name){
		this.productCode=code.trim();
		this.name=name.trim();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String code) {
		this.productCode = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
