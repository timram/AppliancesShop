package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import fileReader.OrderReader;

public class SetOfOrders extends SetOfItems<ProductOrder>{

	public SetOfOrders(Shop shop) {
		super(shop);
	}

	@Override
	protected void initItemsFile() throws URISyntaxException {
		try {
			itemsFile = new File(getClass().getResource("/orders.info").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	protected void initItems() {
		try {
			OrderReader or = new OrderReader(itemsFile);
			items = or.readProductsOrders();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected void initFilters() {
		filters = new HashMap<String, FilterItems<ProductOrder>>();
		filters.put("status", (order, status) -> {
			return order.getStatus().equals(status);
		});
		filters.put("cost", (order, cost) -> {
			return order.getCost() == Integer.parseInt(cost);
		});
		filters.put("all", (order, filter) -> {
			return true;
		});
	}

	@Override
	protected void updateMaxItemId() {
		maxId = 0;
		for(ProductOrder order : items) {
			if(order.getId() > maxId) {
				maxId = order.getId();
			}
		}
	}

	@Override
	public ProductOrder getItemById(int id) {
		for(ProductOrder order : items) {
			if(order.getId() == id) {
				return order;
			}
		}
		return null;
	}

	@Override
	public ArrayList<ProductOrder> getItemsByType(String type, String value) {
		ArrayList<ProductOrder> filteredOrders = new ArrayList<ProductOrder>();
		if(filters.containsKey(type)) {
			for(ProductOrder order : items) {
				if(filters.get(type).checkItem(order, value)) {
					filteredOrders.add(order);
				}
			}
		}
		User user = shop.getCurrentUser();
		if(user == null || !user.getType().equals("admin")) {
			/*ArrayList<ProductOrder> ordersForUser = new ArrayList<ProductOrder>();
			for(ProductOrder ord : filteredOrders) {
				if(ord.getUserId() == shop.getCurrentUser().getId()) {
					ordersForUser.add(ord);
				}
			}
			return ordersForUser;*/
		}
		return filteredOrders;
	}

	@Override
	public void addItem(HashMap<String, String> params) {
		ProductOrder order = new ProductOrder(Integer.parseInt(params.get("productId")), Integer.parseInt(params.get("userId")), 
				params.get("status"), Integer.parseInt(params.get("cost")), Integer.parseInt(params.get("quantity")), ++maxId);
		items.add(order);
		saveItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ProductOrder> getCloneOfSet() {
		return (ArrayList<ProductOrder>) items.clone();
	}
}
