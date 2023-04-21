package service;

import java.util.ArrayList;

import model.CartDetails;
import model.Customer;

public interface CartController {
	public boolean addToCart(CartDetails cartDetails,Customer customer);
	public ArrayList<ArrayList<Object>> getCartList(String cusId);
	public ArrayList<ArrayList<Object>> cartUpdate(CartDetails cartDetails,String cusId);
	public ArrayList<ArrayList<Object>> cartDeleteItem(String cartId, String itemId, String cusId);
	
}
