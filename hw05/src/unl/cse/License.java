package unl.cse;

public class License extends Product {
	
	private double serviceFee;
	private double annualLicenseFee;
	
	public License(){
		super();
		this.serviceFee=this.annualLicenseFee=0;
	}
	
	public License(String code, String name, double serviceFee, double annualLicenseFee){
		super(code ,name);
		this.serviceFee=serviceFee;
		this.annualLicenseFee=annualLicenseFee;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getAnnualLicenseFee() {
		return annualLicenseFee;
	}

	public void setAnnualLicenseFee(double annualLicenseFee) {
		this.annualLicenseFee = annualLicenseFee;
	}
}	
