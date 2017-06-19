package shop;


public abstract class User {
	protected UserProfile profile;
	protected int id;
	protected String login;
	protected String password;
	protected String type;
	
	public User(String login, String password, int id) {
		this.login = login;
		this.password = password;
		this.id = id;
	}
	
	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	public UserProfile getProfile() {
		return profile;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return profile.getFname() + " " + profile.getLname() + " " + type;
	}
}