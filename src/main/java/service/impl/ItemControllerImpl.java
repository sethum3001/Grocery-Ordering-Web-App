package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.item;
import service.ItemController;
import util.DBConnection;

public class ItemControllerImpl implements ItemController{
	
	private Connection connection;
	private PreparedStatement statement;
	
	public static final Logger log = Logger.getLogger(ItemControllerImpl.class.getName());
	
	//==================================================================add an item=====================================================================
	
	public void addItem(item item) {
		
		String itemCode = generateId();
		
//		String itemCode = "CD1000"; //make item code generator
		
		try {
			connection = DBConnection.getDBConnection();
			statement = connection.prepareStatement("insert into item values(?,?,?,?)");
						statement.setObject(1, itemCode);
						statement.setObject(2, item.getName());
						statement.setObject(3, item.getUnitPrice());
						statement.setObject(4, item.getQtyOnHand());
						statement.executeUpdate();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
		}
	}
	
	//==================================================================generate an id=====================================================================
	
	private String generateId() {
		
		String id;
		int code = 1;
		
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT MAX(item_code) FROM item");
			ResultSet rst = statement.executeQuery();
			
			if(rst.next()) {
				if(rst.getString(1)!=null) {
					code = Integer.parseInt(rst.getString(1));
					code++;
				}
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.getStackTrace();
		}
		
		id = "" + code;
		return id;
	}
	
	//==================================================================generate a itemList=====================================================================

	
	public ArrayList<item> getItemList(){
		ArrayList<item> arrayList = new ArrayList<item>();
		
		try {
			connection = DBConnection.getDBConnection();
			statement = connection.prepareStatement("SELECT * FROM item;");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				arrayList.add(new item(resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getFloat(3),
						resultSet.getInt(4))
						);
			}
		}catch(SQLException | ClassNotFoundException e) {
			System.out.println(e);
		}
		return arrayList;
	}
	
	//==================================================================search an item=====================================================================
	
	public ArrayList<item> searchItems(String itemName){
		String name = "'%"+itemName+"%'";
		ArrayList<item> list = new ArrayList<>();
		try {
			ResultSet rst = DBConnection.getDBConnection().prepareStatement("SELECT * FROM item WHERE Name LIKE "+name).executeQuery();
			while(rst.next()) {
				list.add(new item(
						rst.getString(1),
						rst.getString(2),
						rst.getFloat(3),
						rst.getInt(4)));
			}
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return list;
	
	}
	
	public item getItem(String itemId) {
		String itemCode = "'"+itemId+"';";
		item item = new item();
		try {
			ResultSet rst = DBConnection.getDBConnection().prepareStatement("SELECT * FROM item WHERE item_code ="+itemCode).executeQuery();
			if(rst.next()) {
				item Item = new item(										//do something about variable item & Item
						rst.getString(1),
						rst.getString(2),
						rst.getFloat(3),
						rst.getInt(4));
				item = Item;
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
	
	//==================================================================update an item=====================================================================
	
	public void updateItem(item item) {

		try {
			statement = DBConnection.getDBConnection().prepareStatement("UPDATE item SET name=?, unit_price=?, qtyOnHand=? WHERE item_code=?");
			statement.setString(1, item.getName());
			statement.setString(2, String.valueOf(item.getUnitPrice()));
			statement.setString(3, String.valueOf(item.getQtyOnHand()));
			statement.setString(4, item.getCode());
			
			statement.execute();

		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	//==================================================================delete an item=====================================================================

	public void deleteItem(item item) {
		String code = "'"+item.getCode()+"'";
		System.out.println(code);
		try {
			statement = DBConnection.getDBConnection().prepareStatement("DELETE FROM item WHERE item_code="+code);
			statement.execute();
		}catch(ClassNotFoundException | SQLException e ) {
			System.out.println(e);
		}
		
	}
}



















