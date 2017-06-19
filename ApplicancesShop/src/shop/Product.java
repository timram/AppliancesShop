package shop;

public class Product {
	private int id;
	private String title;
	private String type;
	private String vendor;
	private int quantity;
	private int cost;
	private boolean available;
	
	public Product(String title, String type, String vendor, int quantity, int cost, int id) {
		this.title = title;
		this.type = type;
		this.vendor = vendor;
		this.quantity = quantity;
		this.cost = cost;
		this.id = id;
		available = quantity > 0;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public int getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		available = this.quantity > 0;
	}
	
	public void increaseQuantity() {
		quantity++;
	}
	
	public void decreaseQuantity() {
		quantity--;
		available = quantity > 0;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public int compareByTitle(Product other) {
		return title.compareTo(other.getTitle());
	}
	
	public int compareByVendor(Product other) {
		return vendor.compareTo(other.getVendor());
	}
	
	public int compareByCost(Product other) {
		return cost - other.getCost();
	}
	
	@Override
	public String toString() {
		return "ID: < " + id + " > ;\n" + 
				"Title: < " + title.replace(' ', '_') + " > ;\n" + 
				"Type: < " + type.replace(' ', '_') + " > ;\n" + 
				"Vendor: < " + vendor.replace(' ', '_') + " > ;\n" + 
				"Cost: < " + cost + " > ;\n" + 
				"Quantity: < " + quantity + " > ;\n";
	}
}
