package service;

public interface OrderController {
	public String setOrder(String cusId, String date);
	public String getTheLastEntry();
	public boolean setOrderDetails(String cusId, String orderId);
}
