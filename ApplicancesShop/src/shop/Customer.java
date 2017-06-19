package shop;

public class Customer extends User{

	public Customer(String login, String password, int id) {
		super(login, password, id);
		setType("customer");
	}

}
