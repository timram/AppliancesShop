package scenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.MainApp;
import shop.Product;

public class ProductListViewScene extends ListViewScene<Product>{

	public ProductListViewScene(MainApp app) {
		super(app);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected void initGridOptions() {
		super.initGridOptions();
		gridOptions.add(new Label("Сортировать:"), 0, 0);
		gridOptions.add(options.get("sort"), 1, 0);
		gridOptions.add(new Label("Фильтровать:"), 3, 0);
		gridOptions.add(options.get("filter"), 4, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void initOptions() {
		options = new HashMap<String, ComboBox>();
		options.put("sort", new ComboBox());
		options.get("sort").getItems().addAll("Title (A-Z)", "Title (Z-A)", "Vendor (A-Z)", "Vendor (Z-A)", "Cost Hight to Low", "Cost Low to Hight");
		options.get("sort").valueProperty().addListener((ov, oldVal, newVal) -> {
			sortOrderedItems((String)newVal);
			updateMappingItems();
		});
		options.put("filter", new ComboBox());
		options.get("filter").getItems().addAll("Fridge", "Microwave", "TV", "All");
		options.get("filter").setValue("All");
		options.get("filter").valueProperty().addListener((ov, oldVal, newVal) -> {
			filterOrderedItems((String)newVal);
			updateMappingItems();
		});
	}
	
	@Override
	public void updateItems() {
		updateOrderedItems();
		updateMappingItems();
	}

	@Override
	protected void updateOrderedItems() {
		String filterType = (String)options.get("filter").getValue();
		filterOrderedItems(filterType);
	}

	@Override
	protected void updateMappingItems() {
		mappingItems.clear(); 
		for(Product prod: orderedItems) {
			mappingItems.add("Название: " + prod.getTitle() + "; поставщик: " +  prod.getVendor() + "; цена:" + prod.getCost());
		}
	}

	@Override
	protected void filterOrderedItems(String type) {
		if(type.equals("All")) {
			orderedItems = app.getShop().getProducts().getCloneOfSet();
		} else {
			orderedItems = app.getShop().getProducts().getItemsByType("type", type);
		}
		String sortType =  (String) options.get("sort").getValue();
		if(sortType != null) {
			sortOrderedItems(sortType);
		}
	}
	
	@Override
	protected void sortOrderedItems(String type) {
		if(type.equals("Title (A-Z)")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod1.getTitle().compareTo(prod2.getTitle());
			});
		}
		if(type.equals("Title (Z-A)")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod2.getTitle().compareTo(prod1.getTitle());
			});
		}
		if(type.equals("Vendor (A-Z)")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod1.getVendor().compareTo(prod2.getVendor());
			});
		}
		if(type.equals("Vendor (Z-A)")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod2.getVendor().compareTo(prod1.getVendor());
			});
		}
		if(type.equals("Cost Low to Hight")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod1.getCost() - prod2.getCost();
			});
		}
		if(type.equals("Cost Hight to Low")) {
			Collections.sort(orderedItems, (prod1, prod2) -> {
				return prod2.getCost() - prod1.getCost();
			});
		}
	}

}
