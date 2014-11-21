package unl.cse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseItem extends Item {
	
	private Date startDate;
	private Date endDate;
	
	public LicenseItem(Product product, Customer customer, String start, String end) throws ParseException{
		super(product, customer);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.parse(start);
		endDate = sdf.parse(end);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public double getSub_total(){
		
		long diff = endDate.getTime() - startDate.getTime();   
	    long days = diff / (1000 * 60 * 60 * 24);  
	    
		double a = (1.0*days/365) * ((License)super.getProduct()).getAnnualLicenseFee();
		return (double)(Math.round(a*100))/100;
	}
	
	public double getFee(){
		
		double a=((License)super.getProduct()).getServiceFee();
		return (double)(Math.round(a*100))/100;
	}
	
	public double getComplianceFee(){
		if(super.getCustomer() instanceof Government){
			return 125;
		}else{
			return 0;
		}
	}

	public double getTax() {

		if(super.getCustomer() instanceof Corporate){
			double a = 0.0425 * getSub_total();
			return (double)(Math.round(a*100))/100;
		}else{
			return 0;
		}
	}
	
	public String toString(){
		
		String ret="";
		long diff = endDate.getTime() - startDate.getTime();   
	    long days = diff / (1000 * 60 * 60 * 24);  
	    ret+=String.format("%s",this.getProduct().getProductCode() + "");
		ret+=String.format("%60s",this.getProduct().getName()+"(" + days + " days @ $" + ((License)(super.getProduct())).getAnnualLicenseFee() + "/yr)");
		ret+=String.format("%20s","$" + this.getFee()) + String.format("%20s","$" +this.getSub_total());
		return ret;
	}
}
