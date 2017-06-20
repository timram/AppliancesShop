package scenes;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.MainApp;
import shop.Product;

public class ViewProductsScene extends AbstractScene{
	private ProductListViewScene productList;
	private Label prodTitle;
	private Label prodType;
	private Label prodVendor;
	private Label prodCost;
	private Label prodQuantity;
	private Button order;
	private Button remove;
	private Button addProd;
	@SuppressWarnings("rawtypes")
	private ComboBox quantity;
	private BorderPane prodPane;
	private CreateProductScene createProdScene;
	
	public ViewProductsScene(MainApp app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		if(!initialized) {
			this.setPadding(new Insets(25, 25, 25 ,25));
			initProductList();
			initProdViewPane();
			createProdScene = new CreateProductScene(app);
			initialized = true;
		}
		productList.updateItems();
		this.setCenter(prodPane);
		prodPane.setVisible(false);
		updateUiForUser();
	}
	
	@SuppressWarnings("unchecked")
	private void initProductList() {
		productList = new ProductListViewScene(app);
		productList.setListMouseListener(event -> {
			Product prod = productList.getSelectedItem();
			if(prod != null) {
				prodPane.setVisible(true);
				prodTitle.setText(prod.getTitle());
				prodType.setText(prod.getType());
				prodVendor.setText(prod.getVendor());
				prodQuantity.setText(((Integer)prod.getQuantity()).toString());
				prodCost.setText(((Integer)prod.getCost()).toString());
				quantity.getItems().clear();
				for(int i = 1; i <= prod.getQuantity(); i++) {
					quantity.getItems().add(i);
				}
				quantity.setValue(1);
				order.setDisable(!prod.isAvailable());
			}
		});
		this.setLeft(productList);
	}
	
	private void initProdViewPane() {
		prodPane = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 10, 10, 50));
		grid.setVgap(20);
		grid.setHgap(10);
		grid.add(makeLabel("Название Продукта:"), 0, 0);
		prodTitle = makeLabel();
		grid.add(prodTitle, 1, 0);
		grid.add(makeLabel("Тип Продукта"), 0, 1);
		prodType = makeLabel();
		grid.add(prodType, 1, 1);
		grid.add(makeLabel("Поставщик"), 0, 2);
		prodVendor = makeLabel();
		grid.add(prodVendor, 1, 2);
		grid.add(makeLabel("Стоимость"), 0, 3);
		prodCost = makeLabel();
		grid.add(prodCost, 1, 3);
		grid.add(makeLabel("Колличество"), 0, 4);
		prodQuantity = makeLabel();
		grid.add(prodQuantity, 1, 4);
		
		order = new Button("Заказать");
		order.setOnAction(event -> {
			orderSelectedBook();
		});
		grid.add(order, 0, 5);
		quantity = new ComboBox();
		grid.add(quantity, 1, 5);
		remove = new Button("Удалить");
		remove.setOnAction(event -> {
			removeSelectedBook();
		});
		grid.add(remove, 0, 6);
		
		prodPane.setLeft(grid);
		
		prodPane.setVisible(false);
		this.setCenter(prodPane);
		
		
		addProd = new Button("Добавить Товар");
		addProd.setOnAction(event -> {
			createProdScene.init();
			addProd.setVisible(false);
			this.setCenter(createProdScene);
		});
		this.setTop(addProd);
	}
	
	private void removeSelectedBook() {
		Product prod = productList.getSelectedItem();
		if(prod != null) {
			if(this.showConfirmation("Вы действительно хотите удалить \n\"" 
			+ prod.getTitle() + "\" by " + prod.getVendor() + "?")) {
				prodPane.setVisible(false);
				productList.removeSelectedItem();
				app.getShop().getProducts().removeItem(prod.getId());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void orderSelectedBook() {
		Product prod = productList.getSelectedItem();
		if(prod != null) {
			int quantityVal = (Integer)quantity.getValue();
			if(this.showConfirmation("Заказать продукт? \n\"" + 
			prod.getTitle() + "\"  " + prod.getVendor() + "(" + quantityVal + ")?")) {
				HashMap<String, String> props = new HashMap<String, String>();
				props.put("productId", ((Integer)prod.getId()).toString());
				props.put("userId", "1");
				props.put("status", "pending");
				props.put("quantity", ((Integer)quantityVal).toString());
				props.put("cost", ((Integer)(quantityVal * prod.getCost())).toString());
				app.getShop().getOrders().addItem(props);
				prod.setQuantity(prod.getQuantity() - quantityVal);
				prodQuantity.setText(((Integer)prod.getQuantity()).toString());
				app.getShop().getProducts().saveItems();
				quantity.getItems().clear();
				for(int i = 1; i <= prod.getQuantity(); i++) {
					quantity.getItems().add(i);
				}
				quantity.setValue(1);
				order.setDisable(!prod.isAvailable());
			}
		}
	}
	
	private void updateUiForUser() {
		addProd.setVisible(true);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
