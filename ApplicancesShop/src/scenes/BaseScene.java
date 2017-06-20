package scenes;

import java.util.HashMap;

import main.MainApp;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class BaseScene extends AbstractScene{
	private HashMap<String, MenuItem> menuItems;
	
	public BaseScene(MainApp app) {
		super(app);
		initMenuBar();
	}
	
	private void initMenuBar() {
		initMenuItems();
		MenuBar menuBar = new MenuBar();
		
		Menu profileMenu = new Menu("Профиль");
		profileMenu.getItems().addAll(menuItems.get("exit"));
		
		Menu viewMenu = new Menu("Просмотр");
		viewMenu.getItems().addAll(menuItems.get("viewProducts"), menuItems.get("viewOrders"));
		
		menuBar.getMenus().addAll(profileMenu, viewMenu);
		this.setTop(menuBar);
	}
	
	private void initMenuItems() {	
		menuItems = new HashMap<String, MenuItem>();
		/*menuItems.put("viewProfile", new MenuItem("Войти в профиль"));
		menuItems.get("viewProfile").setOnAction(event -> {
			app.setScene("profile");
		});*/
		
		/*menuItems.put("logOut", new MenuItem("Выйти из профиля"));
		menuItems.get("logOut").setOnAction(event -> {
			if(showConfirmation("Вы действительно хотите выйти?")) {
				app.setScene("authorization");
			}
		});*/
		
		menuItems.put("exit", new MenuItem("Выход"));
		menuItems.get("exit").setOnAction(event -> {
			if(showConfirmation("Вы действительно хотите выйти?")) {
				System.exit(0);
			}
		});
		
		menuItems.put("viewProducts", new MenuItem("Просмотр товаров"));
		menuItems.get("viewProducts").setOnAction(event -> {
			app.setScene("viewProducts");
		});
		
		menuItems.put("viewOrders", new MenuItem("Просмотр заказов"));
		menuItems.get("viewOrders").setOnAction(event -> {
			app.setScene("viewOrders");
		});
	}
	
	public MenuItem getMenuItem(String name) {
		return menuItems.get(name);
	}
	
	public void enableUserMenuItems() {
		enableMenuItem("viewProfile");
		enableMenuItem("logOut");
		enableMenuItem("viewBooks");
		enableMenuItem("viewOrders");
	}
	
	public void disableUserMenuItems() {
		disableMenuItem("viewProfile");
		disableMenuItem("logOut");
		disableMenuItem("viewBooks");
		disableMenuItem("viewOrders");
	}
	
	public void disableMenuItem(String name) {
		menuItems.get(name).setDisable(true);
	}
	
	public void enableMenuItem(String name) {
		menuItems.get(name).setDisable(false);
	}

	@Override
	public void init() {
	}

	@Override
	public void reset() {
	}

}
