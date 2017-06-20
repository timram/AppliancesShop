package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shop.Product;

public class ProductReader {
	private Scanner scanner;
	
	public ProductReader(String fileName) throws FileNotFoundException {
		scanner = new Scanner(new File(fileName));
	}
	
	public ProductReader(File file) throws FileNotFoundException { 
		scanner = new Scanner(file);
	}
	
	private String readLine(String label) {
		String line;
		while(!scanner.hasNext(label)) {
			scanner.next();
		}
		line = scanner.next();
		while(!scanner.hasNext(";")) {
			line += scanner.next();
		}
		scanner.next();
		return line;
	}
	
	private int readId() {
		String line = readLine("ID:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return Integer.parseInt(line);
	}
	
	private String readTitle() {
		String line = readLine("Title:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return line.replace('_', ' ');
	}
	
	private String readType() {
		String line = readLine("Type:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return line.replace('_', ' ');
	}
	
	private String readVendor() {
		String line = readLine("Vendor:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return line.replace('_', ' ');
	}
	
	private int readCost() {
		String  line = readLine("Cost:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return Integer.parseInt(line);
	}
	
	private int readQuantity() {
		String  line = readLine("Quantity:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return Integer.parseInt(line);
	}
	
	public Product readProduct() {
		int id = readId();
		String title = readTitle();
		String type = readType();
		String vendor = readVendor();
		int cost = readCost();
		int quantity = readQuantity();
		return new Product(title, type, vendor, quantity, cost, id);
	}
	
	public ArrayList<Product> readProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		while(scanner.hasNext()) {
			products.add(readProduct());
		}
		return products;
	}
}
