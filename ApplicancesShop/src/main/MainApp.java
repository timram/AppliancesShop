package main;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application{
	private Stage stage;
	private HashMap<String, AbstractScene> scenes;
	private Library library;
	private BaseScene baseScene;
	

	@Override
	public void start(Stage mainStage) throws Exception {
		try {
			stage = mainStage;
			stage.setWidth(1000);
			stage.setHeight(500);
			baseScene = new BaseScene(this);
			stage.setScene(new Scene(baseScene));
			initLibrary();
			initScenes();
			setScene("authorization");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initScenes() {
		scenes = new HashMap<String, AbstractScene>();
		scenes.put("authorization", new AuthorizationScene(this));
		scenes.put("main", new MainScene(this));
		scenes.put("profile", new ProfileScene(this));
		scenes.put("viewBooks", new ViewBooksScene(this));
		scenes.put("viewOrders", new ViewOrdersScene(this));
	}
	
	private void initLibrary() {
		library = new Library();
	}
	
	public void setScene(String name) {
		scenes.get(name).init();
		baseScene.setCenter(scenes.get(name));
	}
	
	public void setTitle(String title) {
		stage.setTitle(title);
	}
	
	public Library getLibrary() {
		return library;
	}
	
	public BaseScene getBaseScene() {
		return baseScene;
	}
}