import unl.cse.*;

import java.io.IOException;
import java.util.ArrayList;


public class TestDriver {
	
	public static void main(String[] args) throws IOException{

		ArrayList<Person> persons = ReaderFile.readPersons();
		ArrayList<Customer> customers = ReaderFile.readCustomers(persons);
		ArrayList<Product> products = ReaderFile.readProducts(persons);

		ArrayList<Invoice> invoices = ReaderFile.readInvoice(persons,customers,products);
		String ret = "";
		ret+=String.format("%s", "Invoice");
		ret+=String.format("%40s", "Customer");
		ret+=String.format("%20s", "Salesperson");
		ret+=String.format("%16s", "Subtotal");
		ret+=String.format("%16s", "Fees");
		ret+=String.format("%16s", "Taxes");
		ret+=String.format("%16s", "Total");
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.println(ret);
		
		double total=0;
		double subtotal=0;
		double fees = 0;
		double taxes = 0;
		
		for(int i=0;i<invoices.size();i++){
			System.out.println(invoices.get(i).getSummary());
			subtotal+=invoices.get(i).getSubTotal();
			total+=invoices.get(i).getTotal();
			taxes+=invoices.get(i).getTax();
			fees+=invoices.get(i).getFee()+invoices.get(i).getComplianceFee();
		}
		System.out.println("===================================================================================================================================");
		System.out.print(String.format("%-66s", "TOTALS"));
		System.out.print(String.format("%16s", "$"+subtotal));
		System.out.print(String.format("%16s", "$"+fees));
		System.out.print(String.format("%16s", "$"+taxes));
		System.out.print(String.format("%16s", "$"+total));
		System.out.println();
		System.out.println();
		System.out.println();
		

		System.out.println("Individual Invoice Detail Reports");
		System.out.println("==================================================");
		for(int i=0;i<invoices.size();i++){
			System.out.println(invoices.get(i)+"\n\n\n");
		}
	}

}
