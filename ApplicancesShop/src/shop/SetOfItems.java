package shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class SetOfItems<T> implements UpdateableSetOfItems<T> {
	protected ArrayList<T> items;
	protected File itemsFile;
	protected int maxId;
	protected HashMap<String, FilterItems<T>> filters;
	protected Shop shop;
	
	public SetOfItems(Shop shop) {
		try {
			initItemsFile();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.shop = shop;
		initItems();
		initFilters();
		updateMaxItemId();
	}
	
	@Override
	public void saveItems() {
		PrintWriter writer;
		try {
			writer = new PrintWriter(itemsFile);
			for(T item: items) {
				writer.print(item);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public T getLastItem() {
		if(items.size() > 0) {
			return items.get(items.size() - 1);
		}
		return null;
	}
	
	@Override
	public T getFirstItem() {
		if(items.size() > 0) {
			return items.get(0);
		}
		return null;
	}
	
	@Override
	public void removeItem(int id) {
		T item = getItemById(id);
		if(item != null)
			items.remove(item);
		updateMaxItemId();
		saveItems();
	}
	
	protected abstract void initItemsFile() throws URISyntaxException;
	protected abstract void initItems();
	protected abstract void initFilters();
	protected abstract void updateMaxItemId();

}
