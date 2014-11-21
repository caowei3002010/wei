package unl.cse;

public class EquipmentItem extends Item {
	
	private int units;
	
	public EquipmentItem(Product product, Customer customer, int units){
		super(product,customer);
		this.units=units;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public double getSub_total(){
		
		double price = ((Equipment)(super.getProduct())).getPricePerUnit();
		return (double)(Math.round(price * units*100))/100;
	}
	
	public double getComplianceFee(){
		if(super.getCustomer() instanceof Government){
			return 125;
		}else{
			return 0;
		}
	}
	
	public double getFee(){
		return 0;
	}
	
	public double getTax(){
		if(super.getCustomer() instanceof Corporate){
			return (double)(Math.round(0.07 * getSub_total() *100))/100;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		
		String ret="";
		ret+=String.format("%s",this.getProduct().getProductCode() + "");
		ret+=String.format("%60s",this.getProduct().getName()+"("+this.getUnits() + " units @ $" + ((Equipment)(super.getProduct())).getPricePerUnit() + "/unit)");
		ret+=String.format("%20s","$" + this.getFee()) + String.format("%20s","$" +this.getSub_total());
		return ret;
	}
}
