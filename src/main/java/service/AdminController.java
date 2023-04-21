package service;

import java.sql.ResultSet;

public interface AdminController {
	public ResultSet loginValidate(String userName,String pw);
}
