package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import service.CustomerController;
import util.DBConnection;

public class CustomerControllerImpl implements CustomerController{
	private Connection connection;
	private PreparedStatement statement;
	
	public ResultSet loginValidate(Customer cus) {
		
		String username = "'"+cus.getUsername()+"'";
		String password = "'"+cus.getPassword()+"'";
		ResultSet value = null ;
		
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM customer WHERE username="+username+" AND password="+password);
			//statement.setString(1,username);									//"SELECT * FROM customer WHERE username=? AND password=?" - doesn't work
			//statement.setString(2,password);	
			
			ResultSet rst = statement.executeQuery();
			value = rst;
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return value;
	}
	
	public void customerRegister(Customer cus) {
		try {
			statement = DBConnection.getDBConnection().prepareStatement("INSERT INTO customer(name,username,password) VALUES(?,?,?)");
			statement.setString(1, cus.getName());
			statement.setString(2, cus.getUsername());
			statement.setString(3, cus.getPassword());
			statement.execute();
			
			createCusCart();
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createCusCart() {
		String cusId;
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT MAX(cus_Id) FROM customer");
			ResultSet rst = statement.executeQuery();
			if(rst.next()) {
				cusId = rst.getString(1);
				statement = DBConnection.getDBConnection().prepareStatement("INSERT INTO cart(cus_id) VALUES("+cusId+")");
				statement.execute();
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
