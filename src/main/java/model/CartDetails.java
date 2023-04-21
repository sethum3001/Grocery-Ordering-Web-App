package model;

public class CartDetails {
	
	public CartDetails() {}
	
	public CartDetails(int itemcode, int cartId, int price, int qty) {
		super();
		this.Itemcode = itemcode;
		this.cartId = cartId;
		this.price = price;
		this.qty = qty;
	}
	private int Itemcode;
	private int cartId;
	private int price;
	private int qty;
	public int getItemcode() {
		return Itemcode;
	}
	public void setItemcode(int itemcode) {
		Itemcode = itemcode;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
