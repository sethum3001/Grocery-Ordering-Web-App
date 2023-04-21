package service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.OrderController;
import util.DBConnection;

public class OrderControllerImpl implements OrderController {
	private PreparedStatement statement;
//========================================================================set order=============================================================
	public String setOrder(String cusId, String date) {
		try {
			statement = DBConnection.getDBConnection().prepareStatement("INSERT INTO orders(cus_id,date) VALUES(?,NOW())");
			statement.setString(1, cusId);
		//	statement.setString(2, date); used NOW() instead
			statement.executeUpdate();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("set order method");
		return getTheLastEntry();	
	}
	//========================================================================get the last entry=============================================================
	
	public String getTheLastEntry() {
		String orderId = null;
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT MAX(order_id) FROM orders");
			ResultSet rst = statement.executeQuery();
			if(rst.next()) {
				orderId = rst.getString(1);
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("get the last entry method");
		return orderId;
	}
	//========================================================================set the order details=============================================================

	public boolean setOrderDetails(String cusId, String orderId) {
		int count = 0;
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM cart WHERE cus_id="+"'"+cusId+"'");
			ResultSet rst1 = statement.executeQuery();	
			if(rst1.next()) {
				String cartId = rst1.getString(1);
				statement =DBConnection.getDBConnection().prepareStatement("SELECT * FROM cart_details WHERE cart_id="+"'"+cartId+"'");
				ResultSet rst2 = statement.executeQuery();
				while(rst2.next()) {
					statement = DBConnection.getDBConnection().prepareStatement("INSERT INTO order_details VALUES(?,?,?,?)");
					statement.setString(1, orderId);
					statement.setString(2, rst2.getString(1));
					statement.setString(3, rst2.getString(4));
					statement.setString(4, rst2.getString(3));
					int num = statement.executeUpdate();
					if(num>0) {
						statement = DBConnection.getDBConnection().prepareStatement("UPDATE item SET qtyOnHand=(qtyOnHand-"+rst2.getString(4)+") WHERE item_code="+rst2.getString(1)+";");
						statement.execute();
					}
				}
				statement = DBConnection.getDBConnection().prepareStatement("DELETE FROM cart_details WHERE cart_id="+"'"+cartId+"'");
				count = statement.executeUpdate();
				
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("set order details method");
		if(count>0) {
			return true;
		}else {
			return false;
		}
		
	}
}
