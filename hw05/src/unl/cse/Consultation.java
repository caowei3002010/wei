package unl.cse;

public class Consultation extends Product {
	
	private Person consultant;
	private double hourlyFee;
	
	public Consultation(){
		super();
		this.consultant=new Person();
		this.hourlyFee=0;
	}
	
	public Consultation(String code, String name, Person consultant, double hourlyFee){
		super(code,name);
		this.consultant=consultant;
		this.hourlyFee=hourlyFee;
	}

	public Person getConsultantPersonCode() {
		return consultant;
	}

	public void setConsultantPersonCode(Person consultant) {
		this.consultant = consultant;
	}

	public double getHourlyFee() {
		return hourlyFee;
	}

	public void setHourlyFee(double hourlyFee) {
		this.hourlyFee = hourlyFee;
	}
}
