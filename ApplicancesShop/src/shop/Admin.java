package shop;

public class Admin extends User{

	public Admin(String login, String password, int id) {
		super(login, password, id);
		setType("admin");
	}

}
