package model;

public class OrderDetails {

	public OrderDetails(String id, String itemId, int qty, float price) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.qty = qty;
		this.price = price;
	}
	private String id;
	private String itemId;
	private int qty;
	private float price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
