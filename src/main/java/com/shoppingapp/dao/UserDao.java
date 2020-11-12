package com.shoppingapp.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shoppingapp.authentication.Encrypt;
import com.shoppingapp.entity.Address;
import com.shoppingapp.entity.Invoice;
import com.shoppingapp.entity.Item;
import com.shoppingapp.entity.LoginState;
import com.shoppingapp.entity.User;

public class UserDao {
	// called by CommonDao.initialize
	static void initialize() {
		try {
			if(CommonDao.createTableIfMissing("user",
					"id int NOT NULL AUTO_INCREMENT, "
					+ "name varchar(255), "
					+ "username varchar(255), "
					+ "password varchar(255), "
					+ "email varchar(255), "
					+ "phone varchar(32), "
					+ "enabled boolean, "
					+ "privilage varchar(32), "
					+ "street_name varchar(128), "
					+ "appt_no varchar(32), "
					+ "city varchar(64), "
					+ "state varchar(16), "
					+ "PRIMARY KEY(id)")) {
				addUser(new User(-1, "admin", "admin", "admin", "noreply@mail.com", "999-999-9999", true, User.PRIVILAGE.ADMIN, new Address("George Street", "N/A", "George Town", "CA")), null);
			}
			
			CommonDao.createTableIfMissing("invoice",
					"user_id int NOT NULL, "
					+ "id int NOT NULL AUTO_INCREMENT, "
					+ "local_date date, "
					+ "PRIMARY KEY(id), "
					+ "FOREIGN KEY (user_id) REFERENCES user(id)");
			
			CommonDao.createTableIfMissing("invoice_item",
					"invoice_id int NOT NULL, "
					+ "item_code varchar(10), "
					+ "FOREIGN KEY (invoice_id) REFERENCES invoice(id), "
					+ "FOREIGN KEY (item_code) REFERENCES item(code)");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static LoginState getUser(String username, String password) {

		User result = null;
		
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt;
				
				// get fundamental user data
				stmt = conn.prepareStatement("select * from user where name = ?");
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					result = new User(rs.getInt("id"),
										rs.getString("name"),
										username,
										rs.getString("password"),
										rs.getString("email"),
										rs.getString("phone"),
										rs.getBoolean("enabled"),
										User.PRIVILAGE.valueOf(rs.getString("privilage")),
										new Address(rs.getString("street_name"),
													rs.getString("appt_no"),
													rs.getString("city"),
													rs.getString("state")
												));
					
                    
					// check now that user is valid, use it as an early out
					if(!result.password.equals(encryptPass(username, password)))
						return new LoginState("Invalid Credentials", null);
					else if(!result.isEnabled())
						return new LoginState("Account Disabilitation", null);

				}
				else
					return new LoginState("Invalid Credentials", null);
				
				// get invoice data
				stmt = conn.prepareStatement("select * from invoice where user_id = ?");
				stmt.setInt(1, result.id);
				rs = stmt.executeQuery();
				Invoice curInvoice;
				List<Item> items;
				ResultSet rsItemCode;
				ResultSet rsItem;
				while(rs.next()) {
					items = new ArrayList<Item>();
					curInvoice = new Invoice(result.getName(),
							rs.getDate("local_date").toLocalDate(),
							rs.getInt("id"),
							items);
					
					stmt = conn.prepareStatement("select item_code from invoice_item where invoice_id = ?");
					stmt.setInt(1, curInvoice.invoiceNum);
					rsItemCode = stmt.executeQuery();
					while(rsItemCode.next()) {
						stmt = conn.prepareStatement("select * from item where code = ?");
						stmt.setString(1, rsItemCode.getString("item_code"));
						rsItem = stmt.executeQuery();
						while(rsItem.next()) {
							items.add(new Item(rsItem.getString("name"),
												rsItem.getString("code"),
												rsItem.getString("category"),
												Item.CONDITION.valueOf(rsItem.getString("condition")),
												rsItem.getLong("price"),
												rsItem.getLong("stock"),
												rsItem.getString("description"),
												rsItem.getString("manufacturer")));
						}
						rsItem.close();
					}
					rsItemCode.close();
					result.purchases.add(curInvoice);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new LoginState(null, result);
	}
	
	// get all users
	public static List<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
			try {
				Connection conn = CommonDao.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id,  from user limit ?, ?");
				while(rs.next()) {
					users.add(new User(rs.getInt("id"),
										rs.getString("name"),
										rs.getString("username"),
										rs.getString("password"),
										rs.getString("email"),
										rs.getString("phone"),
										rs.getBoolean("enabled"),
										User.PRIVILAGE.valueOf(rs.getString("privilage")),
										new Address(rs.getString("street_name"),
													rs.getString("appt_no"),
													rs.getString("city"),
													rs.getString("state")
											)));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return users;
	}
	
	// get a certain number of users (pagination)
	public static List<User> getUsers(int startRow, int numRows) {
		ArrayList<User> users = new ArrayList<User>();
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt = conn.prepareStatement("select name from user limit ?, ?");
				stmt.setInt(0, startRow);
				stmt.setInt(1, numRows);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					users.add(new User(rs.getInt("id"),
										rs.getString("name"),
										rs.getString("username"),
										rs.getString("password"),
										rs.getString("email"),
										rs.getString("phone"),
										rs.getBoolean("enabled"),
										User.PRIVILAGE.valueOf(rs.getString("privilage")),
										new Address(rs.getString("street_name"),
													rs.getString("appt_no"),
													rs.getString("city"),
													rs.getString("state")
											)));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return users;
	}
	
	public static boolean userExists(String username) {

			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt;
				
				stmt = conn.prepareStatement("select exists(select 1 from user where username = ?)");
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				if(rs.next() && rs.getBoolean(1))
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public static void addUser(User user, String truePath) {

			if(userExists(user.name))
				return;
			user.password = encryptPass(user.username, user.password);
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt;
				
				stmt = conn.prepareStatement("insert into user(name, username, password, email, phone, enabled, privilage, street_name, appt_no, city, state) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, user.name);
				stmt.setString(2, user.username);
				stmt.setString(3, user.password);
				stmt.setString(4, user.email);
				stmt.setString(5, user.phone);
				stmt.setBoolean(6, user.enabled);
				stmt.setString(7, user.privilage.name());
				stmt.setString(8, user.billingAddress.streetName);
				stmt.setString(9, user.billingAddress.apptNo);
				stmt.setString(10, user.billingAddress.city);
				stmt.setString(11, user.billingAddress.state);
				stmt.executeUpdate();
				
//				+ "street_name varchar(128), "
//				+ "appt_no varchar(32), "
//				+ "city varchar(64), "
//				+ "state varchar(16), "
				
				// now add the avatar
				if(user.avatar != null) {
					try {
						user.avatar.transferTo(new File(getAvatarPath(truePath, user)));
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
				
				// new users don't have invoices, so we don't need to add them
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void updateUser(User user, String truePath) {

		// password must be re-encrypted, because the username may have changed
		user.password = encryptPass(user.username, user.password);
		try {
			Connection conn = CommonDao.getConnection();
			PreparedStatement stmt;
			
			stmt = conn.prepareStatement("update user set name=?, username=?, password=?, email=?, phone=?, enabled=?, privilage=?, street_name=?, appt_no=?, city=?, state=? where id=?");
			stmt.setString(1, user.name);
			stmt.setString(2, user.username);
			stmt.setString(3, user.password);
			stmt.setString(4, user.email);
			stmt.setString(5, user.phone);
			stmt.setBoolean(6, user.enabled);
			stmt.setString(7, user.privilage.name());
			stmt.setString(8, user.billingAddress.streetName);
			stmt.setString(9, user.billingAddress.apptNo);
			stmt.setString(10, user.billingAddress.city);
			stmt.setString(11, user.billingAddress.state);
			stmt.setInt(12, user.id);
			stmt.executeUpdate();
			
			// now add the avatar
			if(user.avatar != null) {
				try {
					user.avatar.transferTo(new File(getAvatarPath(truePath, user)));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			// new users don't have invoices, so we don't need to add them
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
}
	
	public static void addInvoice(User user, Invoice invoice) {
		user.addPurchase(invoice);
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt;
				
				stmt = conn.prepareStatement("insert into invoice(user_id, local_date) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, user.id);
				stmt.setDate(2, Date.valueOf(invoice.date));
				stmt.executeUpdate();
				
				ResultSet genKeys = stmt.getGeneratedKeys();
				if(genKeys.next())
					invoice.invoiceNum = genKeys.getInt(1);
				
				for(Item item : invoice.items) {
					stmt = conn.prepareStatement("insert into invoice_item(invoice_id, item_code) values(?, ?)");
					stmt.setInt(1, invoice.invoiceNum);
					stmt.setString(2, item.code);
					stmt.executeUpdate();
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void removeInvoiceItem(Invoice invoice, int itemIndex) {
			try {
				Connection conn = CommonDao.getConnection();
				PreparedStatement stmt;
				
				stmt = conn.prepareStatement("delete from invoice_item where invoice_id=? and item_code=? limit 1");
				stmt.setInt(1, invoice.invoiceNum);
				stmt.setString(2, invoice.items.get(itemIndex).code);
				stmt.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		invoice.items.remove(itemIndex);
	}
	
	private static String encryptPass(String name, String password) {
		return Encrypt.encrypt(password, name, password, 20);
	}
	
	private static String getAvatarPath(String truePath, User user) {
		return truePath + "\\images\\user\\" + user.id + ".png";
	}
}
