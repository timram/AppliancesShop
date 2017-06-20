package scenes;

import java.util.Collections;
import java.util.HashMap;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.MainApp;
import shop.Product;
import shop.ProductOrder;

public class OrdersListViewScene extends ListViewScene<ProductOrder>{

	public OrdersListViewScene(MainApp app) {
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
		options.get("sort").getItems().addAll("Cost Hight to Low", "Cost Low to Hight");
		options.get("sort").valueProperty().addListener((ov, oldVal, newVal) -> {
			sortOrderedItems((String)newVal);
			updateMappingItems();
		});
		options.put("filter", new ComboBox());
		options.get("filter").getItems().addAll("pending", "confirm", "reject", "all");
		options.get("filter").setValue("all");
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
		for(ProductOrder order: orderedItems) {
			Product prod = app.getShop().getProducts().getItemById(order.getProductId());
			mappingItems.add("Название: " + prod.getTitle() + "; кол-во: " +  order.getQuantity() + "; цена:" + order.getCost());
		}
	}

	@Override
	protected void filterOrderedItems(String type) {
		if(type.equals("all")) {
			orderedItems = app.getShop().getOrders().getItemsByType("all", "");
		} else {
			orderedItems = app.getShop().getOrders().getItemsByType("status", type);
		}
		String sortType =  (String) options.get("sort").getValue();
		if(sortType != null) {
			sortOrderedItems(sortType);
		}
	}
	
	@Override
	protected void sortOrderedItems(String type) {
		if(type.equals("Cost Low to Hight")) {
			Collections.sort(orderedItems, (ord1, ord2) -> {
				return ord1.getCost() - ord2.getCost();
			});
		}
		if(type.equals("Cost Hight to Low")) {
			Collections.sort(orderedItems, (ord1, ord2) -> {
				return ord2.getCost() - ord1.getCost();
			});
		}
	}

}
