package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import fileReader.ProductReader;


public class SetOfProducts extends SetOfItems<Product>{

	public SetOfProducts(Shop shop) {
		super(shop);
	}

	@Override
	protected void initItemsFile() throws URISyntaxException {
		try {
			itemsFile = new File(getClass().getResource("/products.info").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	protected void initItems() {
		try {
			ProductReader pr = new ProductReader(itemsFile);
			items = pr.readProducts();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initFilters() {
		filters = new HashMap<String, FilterItems<Product>>();
		filters.put("type", (prod, type) -> {
			return prod.getType().equals(type);
		});
		filters.put("cost", (prod, cost) -> {
			return prod.getCost() == Integer.parseInt(cost);
		});
	}
	
	@Override
	protected void updateMaxItemId() {
		maxId = 0;
		for(Product prod : items) {
			if(prod.getId() > maxId) {
				maxId = prod.getId();
			}
		}
	}

	@Override
	public Product getItemById(int id) {
		for(Product prod : items) {
			if(prod.getId() == id) {
				return prod;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Product> getItemsByType(String type, String value) {
		ArrayList<Product> filteredProds = new ArrayList<Product>();
		if(filters.containsKey(type)) {
			for(Product prod : items) {
				if(filters.get(type).checkItem(prod, value)) {
					filteredProds.add(prod);
				}
			}
		}
		return filteredProds;
	}

	@Override
	public void addItem(HashMap<String, String> params) {
		Product prod = new Product(params.get("title"), params.get("type"), params.get("vendor"),
				Integer.parseInt(params.get("quantity")), Integer.parseInt(params.get("cost")), ++maxId);
		items.add(prod);
		saveItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Product> getCloneOfSet() {
		return (ArrayList<Product>) items.clone();
	}
}
