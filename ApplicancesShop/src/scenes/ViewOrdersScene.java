package scenes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.MainApp;
import shop.Product;
import shop.ProductOrder;
import shop.UserProfile;

public class ViewOrdersScene extends AbstractScene{
	private OrdersListViewScene ordersList;
	private Label userName;
	private Label userAddress;
	private Label userMail;
	private Label prodTitle;
	private Label prodQuantity;
	private Label prodCost;
	private Label orderStatus;
	private Button remove;
	private Button confirm;
	private Button reject;
	@SuppressWarnings("rawtypes")
	private BorderPane orderPane;
	
	public ViewOrdersScene(MainApp app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		try{
			if(!initialized) {
				this.setPadding(new Insets(25, 25, 25 ,25));
				initOrderList();
				initOrderViewPane();
				initialized = true;
			}
			ordersList.updateItems();
			this.setCenter(orderPane);
			orderPane.setVisible(false);
			updateUiForUser();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initOrderList() {
		ordersList = new OrdersListViewScene(app);
		ordersList.setListMouseListener(event -> {
			ProductOrder order = ordersList.getSelectedItem();
			if(order != null) {
				orderPane.setVisible(true);
				UserProfile user = app.getShop().getUserById(order.getUserId()).getProfile();
				Product prod = app.getShop().getProducts().getItemById(order.getProductId());
				userName.setText(user.getFname() + " " + user.getLname());
				userAddress.setText(user.getAddress());
				userMail.setText(user.getEmail());
				prodTitle.setText(prod.getTitle());
				prodQuantity.setText(((Integer)order.getQuantity()).toString());
				prodCost.setText(((Integer)order.getCost()).toString());
				orderStatus.setText(order.getStatus());
				if(!order.getStatus().equals("pending")) {
					confirm.setDisable(true);
					reject.setDisable(true);
				} else {
					confirm.setDisable(false);
					reject.setDisable(false);
				}
			}
		});
		this.setLeft(ordersList);
	}
	
	private void initOrderViewPane() {
		orderPane = new BorderPane();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50, 10, 10, 50));
		grid.setVgap(20);
		grid.setHgap(10);
		grid.add(makeLabel("Имя Клиента:"), 0, 0);
		userName = makeLabel();
		grid.add(userName, 1, 0);
		grid.add(makeLabel("Адрес клиента"), 0, 1);
		userAddress = makeLabel();
		grid.add(userAddress, 1, 1);
		grid.add(makeLabel("Почта клиента"), 0, 2);
		userMail = makeLabel();
		grid.add(userMail, 1, 2);
		grid.add(makeLabel("Название товара"), 0, 3);
		prodTitle = makeLabel();
		grid.add(prodTitle, 1, 3);
		grid.add(makeLabel("Колличество"), 0, 4);
		prodQuantity = makeLabel();
		grid.add(prodQuantity, 1, 4);
		grid.add(makeLabel("Цена"), 0, 5);
		prodCost = makeLabel();
		grid.add(prodCost, 1, 5);
		grid.add(makeLabel("Статуc"), 0, 6);
		orderStatus = makeLabel();
		grid.add(orderStatus, 1, 6);
		initButtons();
		grid.add(remove, 0, 7);
		HBox box = new HBox(10);
		box.setPadding(new Insets(10, 10, 50, 50));
		box.setAlignment(Pos.TOP_CENTER);
		box.getChildren().addAll(confirm, reject, remove);
		orderPane.setBottom(box);
		orderPane.setLeft(grid);
		orderPane.setVisible(false);
		this.setCenter(orderPane);
	}
	
	private void initButtons() {
		confirm = new Button("Подтвердить");
		confirm.setOnAction(event -> {
			updateOrderStatus("confirm");
		});
		reject = new Button("Отклонить");
		reject.setOnAction(event -> {
			updateOrderStatus("reject");
		});
		remove = new Button("Удалить");
		remove.setOnAction(event -> {
			removeSelectedOrder();
		});
	}
	
	private void updateOrderStatus(String status) {
		ProductOrder order = ordersList.getSelectedItem();
		if(order != null) {
			order.setStatus(status);
			orderStatus.setText(order.getStatus());
			app.getShop().getOrders().saveItems();
			ordersList.updateItems();
			if(status.equals("reject")) {
				Product prod = app.getShop().getProducts().getItemById(order.getProductId());
				prod.setQuantity(prod.getQuantity() + order.getQuantity());
			}
			confirm.setDisable(true);
			reject.setDisable(true);
		}
	}
	
	private void removeSelectedOrder() {
		ProductOrder order = ordersList.getSelectedItem();
		if(order != null) {
			if(showConfirmation("Вы действительно хотите удалить заказ?")) {
				ordersList.removeSelectedItem();
				app.getShop().getOrders().removeItem(order.getId());
				orderPane.setVisible(false);
			}
		}
	}
	
	
	
	private void updateUiForUser() {
		//addProd.setVisible(true);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
