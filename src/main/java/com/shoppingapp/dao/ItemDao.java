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
					+ "price bigint")) {
				addItem(new Item("Sofa, Leather", "Sl1", 200000));
				addItem(new Item("Sofa, Handwoven", "Sh1", 500000));
				addItem(new Item("Chair, Leather", "Cl1", 5000));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
			try {
				Connection conn = CommonDao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery("select * from item");
				items.clear();
				Item curItem;
				while(result.next()) {
					items.add(curItem = new Item());
					curItem.code = result.getString("code");
					curItem.name = result.getString("name");
					curItem.price = result.getLong("price");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return items;
	}
	
	public static List<Item> getItems(String code) {
		ArrayList<Item> items = new ArrayList<Item>();
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt = conn.prepareStatement("select * from item where code = ?");
				stmt.setString(1, code);
				ResultSet result = stmt.executeQuery();
				items.clear();
				Item curItem;
				while(result.next()) {
					items.add(curItem = new Item());
					curItem.code = result.getString("code");
					curItem.name = result.getString("name");
					curItem.price = result.getLong("price");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return items;
	}
	
	public static void addItem(Item item) {
		try {
			Connection conn = CommonDao.getConnection();
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("insert into item(code, name, price) values(?, ?, ?)");
			stmt.setString(1, item.code);
			stmt.setString(2, item.name);
			stmt.setLong(3, item.price);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
