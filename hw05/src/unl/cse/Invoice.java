package unl.cse;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	
	private String InvoiceCode;
	private String personCode;
	private String customerCode;
	private Person person;
	private Customer customer;
	
	private List<Item> items;
	
	public Invoice(String InvoiceCode, String personCode, String customerCode, List<Person> persons, List<Customer> customers){
		this.InvoiceCode = InvoiceCode;
		this.personCode = personCode;
		this.customerCode = customerCode;
		items = new ArrayList<Item>();
		person = Invoice.getPerson(persons,personCode);
		customer = Invoice.getCustomer(customers,customerCode);
	}
	
	public static Product getProduct(List<Product> list, String productCode){
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProductCode().equals(productCode)){
				return list.get(i);
			}
		}
		return null;
	}
	
	public static Person getPerson(List<Person> list, String personCode){
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getPersonCode().equals(personCode)){
				return list.get(i);
			}
		}
		return null;
	}
	
	public static Customer getCustomer(List<Customer> list, String customerCode){
		
		
		for(int i=0;i<list.size();i++){
			if(list.get(i).getCustomerCode().equals(customerCode)){
				return list.get(i);
			}
		}
		return null;
	}
	
	
	public double getSubTotal(){
		
		double total = 0;
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			total+=item.getSub_total();
		}
		return (double)(Math.round(total*100))/100;
	}
	
	public double getFee(){
		
		double fee = 0;
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			fee+=item.getFee();
		}
		return (double)(Math.round(fee*100))/100;
	}
	
	public double getTotal(){

		return (double)(Math.round((getComplianceFee()+this.getFee()+this.getSubTotal()+this.getTax())*100))/100;
	}
	
	public double getTax(){
		
		double tax=0;
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			tax+=item.getTax();
		}
		return (double)(Math.round(tax*100))/100;
	}
	
	public double getComplianceFee(){
		
		double ComplianceFee=0;
		for(int i=0;i<items.size();i++){
			Item item = items.get(i);
			ComplianceFee+=item.getComplianceFee();
			if(ComplianceFee>0){
				break;
			}
		}
		return (double)(Math.round(ComplianceFee*100))/100;
	}
	
	public String getSummary(){
		
		String ret = "";
		ret+=String.format("%s",  this.getInvoiceCode());
		ret+=String.format("%40s", this.customer.getName());
		ret+=String.format("%20s", this.person.getLastName()+","+this.person.getFirstName());
		ret+=String.format("%16s", "$"+this.getSubTotal());
		ret+=String.format("%16s", "$"+(this.getFee()+this.getComplianceFee()));
		ret+=String.format("%16s", "$"+this.getTax());
		ret+=String.format("%16s", "$"+this.getTotal());
		
		return ret;
	}
	
	public void addItem(Item item){
		items.add(item);
	}

	public String getInvoiceCode() {
		return InvoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		InvoiceCode = invoiceCode;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	public String toString(){
		
		String ret="";
		ret+="Invoice "+InvoiceCode +"\n";
		ret+="========================\n";
		ret+="Salesperson: "+person.getLastName()+", "+person.getFirstName()+"\n";
		ret+=customer.toString()+"\n";
		ret+="-------------------------------------------\n";
		ret+=String.format("%s","Code") + String.format("%60s","Item") + String.format("%20s","Fees")+ String.format("%20s","Total");
		ret+="\n";
		double fees,total;
		fees = total=0;
		for(int i=0;i<items.size();i++){
			ret+=items.get(i)+"\n";
			fees+=items.get(i).getFee();
			total+=items.get(i).getSub_total();
		}
		ret+=String.format("%105s", "===========================");
		ret+="\n";
		ret+="SUB-TOTALS"+String.format("%75s", "$" + fees);
		ret+=String.format("%20s", "$" + total);
		ret+="\n";
		ret+="COMPLIANCE FEE" + String.format("%91s","$" + this.getComplianceFee());
		ret+="\n";
		ret+="TAXES" + String.format("%100s","$" + this.getTax());
		ret+="\n";
		ret+="TOTAL" + String.format("%100s","$" + this.getTotal());
		
		return ret;
	}
	
}
