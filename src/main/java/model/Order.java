package model;

public class Order {
	public Order(String id, String cusId, String date) {
		super();
		this.id = id;
		this.cusId = cusId;
		this.date = date;
	}
	private String id;
	private String cusId;
	private String date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
