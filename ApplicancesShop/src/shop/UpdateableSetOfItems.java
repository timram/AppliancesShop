package shop;

import java.util.ArrayList;
import java.util.HashMap;

public interface UpdateableSetOfItems<T> {
	public T getLastItem();
	public T getFirstItem();
	public T getItemById(int id);
	public ArrayList<T> getItemsByType(String type, String value);
	public void removeItem(int id);
	public void addItem(HashMap<String, String> params);
	public ArrayList<T> getCloneOfSet();
	public void saveItems();
}
