package model;

public class item {
	
	private String code;
	private String name;
	private float unitPrice;
	private int qtyOnHand;
	
	public item() {
	}
	public item(String code, String name, float unitPrice, int qtyOnHand) {
		this.code = code;
		this.name = name;
		this.unitPrice = unitPrice;
		this.qtyOnHand = qtyOnHand;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQtyOnHand() {
		return qtyOnHand;
	}
	public void setQtyOnHand(int qtyOnHand) {
		this.qtyOnHand = qtyOnHand;
	}
	
}
