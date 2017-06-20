package scenes;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.MainApp;

public class CreateProductScene extends AbstractScene{
	private GridPane grid;
	private TextField title;
	private TextField vendor;
	private TextField cost;
	@SuppressWarnings("rawtypes")
	private ComboBox type;
	@SuppressWarnings("rawtypes")
	private ComboBox quantity;
	private Text errorText;
	
	public CreateProductScene(MainApp app) {
		super(app);
	}

	@Override
	public void init() {
		if(!initialized) {
			initGrid();
			initialized = true;
		}
		reset();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGrid() {
		grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setVgap(20);
		grid.setHgap(10);
		
		grid.add(makeLabel("Название Товара:"), 0, 0);
		title = new TextField();
		title.setPrefWidth(400);
		addTextLimiter(title, 25);
		title.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		grid.add(title, 1, 0);
		
		grid.add(makeLabel("Поставщик:"), 0, 1);
		vendor = new TextField();
		vendor.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		addTextLimiter(vendor, 25);
		grid.add(vendor, 1, 1);
		
		grid.add(makeLabel("Стоимость:"), 0, 2);
		cost = new TextField();
		cost.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		addTextLimiter(cost, 6);
		grid.add(cost, 1, 2);
		
		grid.add(makeLabel("Тип:"), 0, 3);
		type = new ComboBox();
		type.getItems().addAll("Fridge", "Microwave", "TV");
		type.setValue("Fridge");
		grid.add(type, 1, 3);
		
		grid.add(makeLabel("Колличество:"), 0, 4);
		quantity = new ComboBox();
		for(int i = 1; i < 30; i++) {
			quantity.getItems().add(((Integer)i).toString());
		}
		quantity.setDisable(false);
		quantity.setValue("1");
		grid.add(quantity, 1, 4);
		
		Button createBtn = new Button("Добавить Товар");
		createBtn.setFont(Font.font("SansSerif", FontWeight.NORMAL, 15));
		createBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleProductData();
			}
		});
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.getChildren().add(createBtn);
		grid.add(hbox, 1, 5);
		
		errorText = new Text("");
		errorText.setFont(Font.font("SansSerif", FontWeight.NORMAL, 20));
		errorText.setFill(Color.CRIMSON);
		grid.add(errorText, 1, 6);
		
		bindTextFieldClearText(title, errorText);
		bindTextFieldClearText(vendor, errorText);
		bindTextFieldClearText(cost, errorText);
		
		this.setCenter(grid);
	}
	
	private void handleProductData() {
		String titleText = title.getText();
		String vendorText = vendor.getText();
		String costText = cost.getText();
		try {
			Double.parseDouble(costText);
			if(titleText.length() == 0 || vendorText.length() == 0) {
				throw new Exception();
			}
		} catch(Exception e) {
			errorText.setText("Заполните необходимые поля");
			return;
		}
		HashMap<String, String> props = new HashMap<String, String>();
		props.put("title", titleText);
		props.put("type", (String)type.getValue());
		props.put("vendor", vendorText);
		props.put("quantity", (String)quantity.getValue());
		props.put("cost", costText);
		app.getShop().getProducts().addItem(props);
		app.setScene("viewProducts");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reset() {
		title.setText("");
		vendor.setText("");
		cost.setText("");
		type.setValue("Fridge");
		quantity.setValue("1");
	}

}
