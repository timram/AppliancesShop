package shop;

import javafx.scene.image.Image;

public class UserProfile {
	private String fname;
	private String lname;
	private String email;
	private String address;
	
	public UserProfile(String fname, String lname, String email, String address) {
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.address = address;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
