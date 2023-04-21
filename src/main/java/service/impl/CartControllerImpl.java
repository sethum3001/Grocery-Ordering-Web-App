package service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartDetails;
import model.Customer;
import model.item;
import service.CartController;
import util.DBConnection;

public class CartControllerImpl implements CartController{
	
	private PreparedStatement statement;
	
	public boolean addToCart(CartDetails cartDetails,Customer customer) {
		
		int count = 0;
		String cartId;
		String itemId = "'"+cartDetails.getItemcode()+"'";
		float itemPrice = 0;
		
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM item WHERE item_code="+itemId); //try to find another way to get the total price of the item.
			ResultSet rst1 = statement.executeQuery();
			
			while(rst1.next()) {
				itemPrice = rst1.getFloat(3);
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		String cusId = "'"+customer.getCusId()+"'";
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM cart WHERE cus_id ="+cusId);
			ResultSet rst2 = statement.executeQuery();
			
			if(rst2.next()) {
				cartId = rst2.getString(1);
				
				statement = DBConnection.getDBConnection().prepareStatement("INSERT INTO cart_details VALUES(?,?,?,?)");
				statement.setString(1, String.valueOf(cartDetails.getItemcode()));
				statement.setString(2, cartId);
				statement.setString(3, String.valueOf(itemPrice*cartDetails.getQty()));
				statement.setString(4, String.valueOf(cartDetails.getQty()));
				count = statement.executeUpdate();
				
				//System.out.println("CartControllerImpl"+value);
				
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(count>0) {
			return true;
		}else {
			return false;
		}
			
	}
	
	public ArrayList<ArrayList<Object>> getCartList(String cusId){
		ArrayList<ArrayList<Object>> list = new ArrayList();
		String cart_id;
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT cart_id FROM cart WHERE cus_id ="+"'"+cusId+"';");
			ResultSet rst1 = statement.executeQuery();
			if(rst1.next()) {
				cart_id = rst1.getString(1);
				statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM item i,cart_details cd WHERE i.item_code=cd.item_code AND cart_id="+"'"+cart_id+"'");
				ResultSet rst2 = statement.executeQuery();
				while(rst2.next()) {
					ArrayList<Object> objs = new ArrayList();
					objs.add(new item(rst2.getString(1),rst2.getString(2),rst2.getFloat(3),rst2.getInt(4)));
					objs.add(new CartDetails(rst2.getInt(5),rst2.getInt(6),rst2.getInt(7),rst2.getInt(8)));
					list.add(objs);
				}
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<ArrayList<Object>> cartUpdate(CartDetails cartDetails,String cusId) {
		try {
			statement = DBConnection.getDBConnection().prepareStatement("UPDATE cart_details SET price=?, quantity=? WHERE cart_id=? AND item_code=?");
			statement.setString(1,String.valueOf(cartDetails.getPrice()));
			statement.setString(2,String.valueOf(cartDetails.getQty()));
			statement.setString(3,String.valueOf(cartDetails.getCartId()));
			statement.setString(4,String.valueOf(cartDetails.getItemcode()));
			statement.execute();

		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		ArrayList<ArrayList<Object>> cartList = getCartList(cusId);
		return cartList;
	}

	public ArrayList<ArrayList<Object>> cartDeleteItem(String cartId, String itemId, String cusId) {
		try {
			statement = DBConnection.getDBConnection().prepareStatement("DELETE FROM cart_details WHERE cart_id=? AND item_code=?");
			statement.setString(1, cartId);
			statement.setString(2, itemId);
			statement.execute();				
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<ArrayList<Object>> list = getCartList(cusId);
		return list;
		
	}
	
	
}
