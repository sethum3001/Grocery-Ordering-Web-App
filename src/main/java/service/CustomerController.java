package service;

import java.sql.ResultSet;

import model.Customer;

public interface CustomerController {
	public ResultSet loginValidate(Customer cus);
	public void customerRegister(Customer cus);
	public void createCusCart();
}
