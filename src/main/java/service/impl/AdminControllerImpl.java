package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.AdminController;
import util.DBConnection;

public class AdminControllerImpl implements AdminController{
	private Connection connection;
	private PreparedStatement statement;

	public ResultSet loginValidate(String userName,String pw) {
		String username =userName;
		String password =pw;
		ResultSet value = null ;
		
		try {
			statement = DBConnection.getDBConnection().prepareStatement("SELECT * FROM admin WHERE username="+"'"+username+"'"+" AND password="+"'"+password+"'");
			//statement.setString(1,username);									//"SELECT * FROM customer WHERE username=? AND password=?" - doesn't work
			//statement.setString(2,password);	
			
			ResultSet rst = statement.executeQuery();
			value = rst;
			
		}catch(ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return value;
	}

}
