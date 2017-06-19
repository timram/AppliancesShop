package shop;

public class ProductOrder {
	private int id;
	private int productId;
	private int userId;
	private int quantity;
	private int cost;
	private String status;
	
	public ProductOrder(int productId, int userId, String status, int cost, int quantity, int id) {
		this.productId = productId;
		this.userId = userId;
		this.status = status;
		this.quantity = quantity;
		this.cost = cost;
		this.id = id;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "ID: < " + id + " > ;\n" + 
				"ProductId: < " + productId + " > ;\n" + 
				"UserId: < " + userId + " > ;\n" + 
				"Status: < " + status.replace(' ', '_') + " > ;\n" + 
				"Cost: < " + cost + " > ;\n" + 
				"Quantity: < " + quantity + " > ;\n";
	}
}
