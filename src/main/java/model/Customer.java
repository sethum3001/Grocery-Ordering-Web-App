package model;

public class Customer {
	
	public Customer() {}
	
	public Customer(String id, String name, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
	}
	private String id;
	private String name;
	private String username;
	private String password;

	public String getCusId() {
		return id;
	}

	public void setCusId(String cusId) {
		this.id = cusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
