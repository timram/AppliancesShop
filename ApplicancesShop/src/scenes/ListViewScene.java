package scenes;

import java.util.ArrayList;
import java.util.HashMap;

import main.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class ListViewScene<T> extends VBox{
	protected ListView<String> list;
	protected ObservableList<String> mappingItems;
	protected ArrayList<T> orderedItems;
	@SuppressWarnings("rawtypes")
	protected HashMap<String, ComboBox> options;
	protected GridPane gridOptions;
	protected MainApp app;
	
	public ListViewScene(MainApp app) {
		this.app = app;
		initGridOptions();
		initListView();
	}
	
	protected void initGridOptions() {
		gridOptions = new GridPane();
		gridOptions.setPadding(new Insets(10, 10, 10, 10));
		gridOptions.setHgap(10);
		initOptions();
		this.getChildren().add(gridOptions);
	}
	
	private void initListView() {
		list = new ListView<String>();
		list.setPadding(new Insets(10, 10, 10, 10));
		list.setPrefWidth(200);
		mappingItems = FXCollections.observableArrayList();
		list.setItems(mappingItems);
		orderedItems = new ArrayList<T>();
		this.getChildren().add(list);
	}
	
	public void setListMouseListener(EventHandler<? super MouseEvent> handler) {
		list.setOnMouseClicked(handler);
	} 
	
	public T getSelectedItem() {
		int index = list.getSelectionModel().getSelectedIndex();
		if(index < 0) {
			return null;
		}
		return orderedItems.get(index);
	}
	
	public void removeSelectedItem() {
		int index = list.getSelectionModel().getSelectedIndex();
		if(index < 0) {
			return;
		}
		orderedItems.remove(index);
		mappingItems.remove(index);
	}
	
	protected abstract void initOptions();
	protected abstract void sortOrderedItems(String type);
	protected abstract void filterOrderedItems(String type);
	public abstract void updateItems();
	protected abstract void updateOrderedItems();
	protected abstract void updateMappingItems();
}
