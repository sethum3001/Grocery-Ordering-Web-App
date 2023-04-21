package service;

import java.util.ArrayList;

import model.item;

public interface ItemController {
	public void addItem(item item);
	public ArrayList<item> getItemList();
	public ArrayList<item> searchItems(String itemName);
	public item getItem(String itemId);
	public void updateItem(item item);
	public void deleteItem(item item);
}
