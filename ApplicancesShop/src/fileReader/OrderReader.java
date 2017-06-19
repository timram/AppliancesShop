package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shop.ProductOrder;

public class OrderReader {
	private Scanner scanner;
	
	public OrderReader(String fileName) throws FileNotFoundException {
		scanner = new Scanner(new File(fileName));
	}
	
	public OrderReader(File file) throws FileNotFoundException { 
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
	
	private int readProdId() {
		String line = readLine("ProductId:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return Integer.parseInt(line);
	}
	
	private int readUserId() {
		String line = readLine("UserId:");
		line = line.substring(line.indexOf("<") + 1, line.indexOf(">"));
		return Integer.parseInt(line);
	}
	
	private String readStatus() {
		String line = readLine("Status:");
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
	
	public ProductOrder readOrder() {
		int id = readId();
		int prodId = readProdId();
		int userId = readUserId();
		String status = readStatus();
		int cost = readCost();
		int quantity = readQuantity();
		return new ProductOrder(prodId, userId, status, cost, quantity, id);
	}
	
	public ArrayList<ProductOrder> readProductsOrders() {
		ArrayList<ProductOrder> orders = new ArrayList<ProductOrder>();
		while(scanner.hasNext()) {
			orders.add(readOrder());
		}
		return orders;
	}
}
