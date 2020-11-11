package com.shoppingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.entity.Item;



public class ItemDao {
	
	// called by DaoManager.initialize
	static void initialize(){
		try {
			if(CommonDao.createTableIfMissing("item",
					"code varchar(10) PRIMARY KEY, "
					+ "name varchar(255), "
					+ "category varchar(255), "
					+ "state varchar(32), "
					+ "price bigint")) {
				addItem(new Item("Sofa, Leather", "Sl1", "Couch", Item.CONDITION.NEW, 200000));
				addItem(new Item("Sofa, Handwoven", "Sh1", "Couch", Item.CONDITION.USED, 500000));
				addItem(new Item("Chair, Leather", "Cl1", "Chair", Item.CONDITION.NEW, 5000));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get all items
	public static List<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
			try {
				Connection conn = CommonDao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("select * from item");
				Item curItem;
				while(result.next()) {
					items.add(curItem = new Item());
					curItem.code = result.getString("code");
					curItem.name = result.getString("name");
					curItem.price = result.getLong("price");
					curItem.category = result.getString("category");
					curItem.condition = Item.CONDITION.valueOf(result.getString("state"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return items;
	}
	
	// get a certain number of items (pagination)
	public static List<Item> getItems(int startRow, int numRows) {
		ArrayList<Item> items = new ArrayList<Item>();
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt = conn.prepareStatement("select * from item limit ?, ?");
				stmt.setInt(0, startRow);
				stmt.setInt(1, numRows);
				ResultSet result = stmt.executeQuery();
				items.clear();
				Item curItem;
				while(result.next()) {
					items.add(curItem = new Item());
					curItem.code = result.getString("code");
					curItem.name = result.getString("name");
					curItem.price = result.getLong("price");
					curItem.category = result.getString("category");
					curItem.condition = Item.CONDITION.valueOf(result.getString("state"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return items;
	}
	
	public static Item getItem(String code) {
		Item item = new Item();
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt = conn.prepareStatement("select * from item where code = ?");
				stmt.setString(1, code);
				ResultSet result = stmt.executeQuery();
				if(result.next()) {
					item.setCode(result.getString("code"));
					item.setName(result.getString("name"));
					item.setPrice(result.getLong("price"));
					item.setCategory(result.getString("category"));
					item.setCondition(Item.CONDITION.valueOf(result.getString("state")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return item;
	}
	
	public static void addItem(Item item) {
		try {
			Connection conn = CommonDao.getConnection();
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("insert into item(code, name, price, category, state) values(?, ?, ?, ?, ?)");
			stmt.setString(1, item.code);
			stmt.setString(2, item.name);
			stmt.setLong(3, item.price);
			stmt.setString(4, item.category);
			stmt.setString(5, item.condition.name());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateItem(Item item) {
		try {
			Connection conn = CommonDao.getConnection();
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("update item set name=?, price=?, category=?, state=? where code=?");
			stmt.setString(1, item.name);
			stmt.setLong(2, item.price);
			stmt.setString(3, item.category);
			stmt.setString(4, item.condition.name());
			stmt.setString(5, item.code);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
