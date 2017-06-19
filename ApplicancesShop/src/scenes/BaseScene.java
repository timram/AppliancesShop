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
		
		Menu profileMenu = new Menu("Profile");
		profileMenu.getItems().addAll(menuItems.get("viewProfile"), menuItems.get("logOut"), menuItems.get("exit"));
		
		Menu viewMenu = new Menu("View");
		viewMenu.getItems().addAll(menuItems.get("viewBooks"), menuItems.get("viewOrders"));
		
		menuBar.getMenus().addAll(profileMenu, viewMenu);
		this.setTop(menuBar);
	}
	
	private void initMenuItems() {	
		menuItems = new HashMap<String, MenuItem>();
		menuItems.put("viewProfile", new MenuItem("View Profile"));
		menuItems.get("viewProfile").setOnAction(event -> {
			app.setScene("profile");
		});
		
		menuItems.put("logOut", new MenuItem("Log Out"));
		menuItems.get("logOut").setOnAction(event -> {
			if(showConfirmation("Do you really want to log out?")) {
				app.setScene("authorization");
			}
		});
		
		menuItems.put("exit", new MenuItem("Exit"));
		menuItems.get("exit").setOnAction(event -> {
			if(showConfirmation("Do you really want to leave?")) {
				System.exit(0);
			}
		});
		
		menuItems.put("viewBooks", new MenuItem("View Books"));
		menuItems.get("viewBooks").setOnAction(event -> {
			app.setScene("viewBooks");
		});
		
		menuItems.put("viewOrders", new MenuItem("View Orders"));
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
