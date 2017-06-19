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

public abstract class ListViewScene extends AbstractScene{
	protected ListView<String> list;
	protected ObservableList<String> mappingItems;
	protected ArrayList<Object> orderedItems;
	@SuppressWarnings("rawtypes")
	protected HashMap<String, ComboBox> options;
	protected GridPane gridOptions;
	
	public ListViewScene(MainApp app) {
		super(app);
		initListView();
		initGridOptions();
	}
	
	private void initListView() {
		list = new ListView<String>();
		list.setPadding(new Insets(10, 10, 10, 10));
		mappingItems = FXCollections.observableArrayList();
		list.setItems(mappingItems);
		orderedItems = new ArrayList<Object>();
		this.setLeft(list);
	}
	
	protected void initGridOptions() {
		gridOptions = new GridPane();
		gridOptions.setPadding(new Insets(10, 10, 10, 10));
		gridOptions.setHgap(10);
		initOptions();
		this.setTop(gridOptions);
	}
	
	public void setListMouseListener(EventHandler<? super MouseEvent> handler) {
		list.setOnMouseClicked(handler);
	} 
	
	public Object getSelectedItem() {
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
