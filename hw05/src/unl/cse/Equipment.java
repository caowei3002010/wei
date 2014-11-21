package unl.cse;

public class Equipment extends Product {
	
	private double pricePerUnit;
	
	public Equipment(){
		super();
		this.pricePerUnit=0;
	}
	
	public Equipment(String code, String name, double pricePerUnit){
		super(code,name);
		this.pricePerUnit=pricePerUnit;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
}
