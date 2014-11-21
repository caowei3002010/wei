package unl.cse;

public class ConsultationItem extends Item{
	
	private int hours;
	
	public ConsultationItem(Product product, Customer customer, int hours){
		super(product,customer);
		this.hours=hours;
	}
	
	public int getHours() {
		return hours;
	}

	public void setHourss(int hours) {
		this.hours = hours;
	}
	
	public double getSub_total(){
		
		double fee = ((Consultation)(super.getProduct())).getHourlyFee();
		return (double)(Math.round(fee * hours*100))/100;
	}
	
	public double getFee(){
		return 150;
	}
	
	public double getComplianceFee(){
		if(super.getCustomer() instanceof Government){
			return 125;
		}else{
			return 0;
		}
	}
	
	public double getTax(){
		if(super.getCustomer() instanceof Corporate){
			return (double)(Math.round(0.0425 * getSub_total()*100))/100;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		
		String ret="";
		ret+=String.format("%s",this.getProduct().getProductCode() + "");
		ret+=String.format("%60s",this.getProduct().getName()+"("+this.getHours() + " hrs @ $" + ((Consultation)(super.getProduct())).getHourlyFee() + "/hr)");
		ret+=String.format("%20s","$" + this.getFee()) + String.format("%20s","$" +this.getSub_total());
		return ret;

	}

}
