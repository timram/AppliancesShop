package shop;

import java.util.ArrayList;
import java.util.HashMap;


public class Shop {
	private ArrayList<User> users;
	
	private UpdateableSetOfItems<Product> products;
	private UpdateableSetOfItems<ProductOrder> orders;
	private User currentUser;
	
	public Shop() {
		initUsers();
		initProfiles();
		initProducts();
		initOrders();
		
	}
	
	private void addProducts() {
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("title", "Test Title");
		props.put("type", "Test type");
		props.put("vendor", "testVendor");
		props.put("quantity", "10");
		props.put("cost", "100");
		products.addItem(props);
	}
	
	private void addOrders() {
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("productId", "1");
		props.put("userId", "1");
		props.put("status", "pending");
		props.put("quantity", "5");
		props.put("cost", "500");
		orders.addItem(props);
	}
	
	private void initUsers() {
		users = new ArrayList<User>();
		users.add(new Customer("customer", "customer", 0));
		users.add(new Admin("admin", "admin", 1));
	}
	
	private void initProfiles() {
		users.get(0).setProfile(new UserProfile("Ramazanov", "Timur", "rjckec@gmail.com", "Sevastopol"));
		users.get(1).setProfile(new UserProfile("Ivanov", "Ivan", "ivanovivan@gmail.com", "Simferopol"));
	}
	
	private void initProducts() {
		products = new SetOfProducts(this);
	}
	
	private void initOrders() {
		orders = new SetOfOrders(this);
	}
	
	public User getUser(String login, String password) {
		for(User user : users) {
			if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public User getUserById(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User user) {
		currentUser = user;
	}
	
	public UpdateableSetOfItems<Product> getProducts() {
		return products;
	}
	
	public UpdateableSetOfItems<ProductOrder> getOrders() {
		return orders;
	}
}
