package shop;

public interface FilterItems<T> {
	public boolean checkItem(T item, String value);
}
