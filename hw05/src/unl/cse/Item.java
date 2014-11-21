package unl.cse;

public abstract class Item {
	
	private Product product;
	private Customer customer;
	
	public Item(Product product, Customer customer){
		this.product=product;
		this.customer=customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Customer getCustomer(){
		return customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
	public abstract double getSub_total();
	public abstract double getFee();
	public abstract double getTax();
	public abstract double getComplianceFee();
	public abstract String toString();
}
