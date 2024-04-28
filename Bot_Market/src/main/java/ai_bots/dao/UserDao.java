package ai_bots.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ai_bots.model.Users;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement prep;
	private ResultSet resset;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public Users userLogin(String email, String password) {
		Users user=null;
		try {
			query="select * from users where email=? and password=?";
			prep=this.con.prepareStatement(query);
			prep.setString(1, email);
			prep.setString(2, password);
			resset=prep.executeQuery();
			
			if(resset.next()) {
				user=new Users();
				user.setId(resset.getInt("id"));
				user.setName(resset.getString("name"));
				user.setEmail(resset.getString("email"));
				user.setPassword(resset.getString("password"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	public boolean userReg(Users u) {
		boolean f=false;
		try {
			query="insert into users(name, email, password) values(?, ?, ?)";
			prep=con.prepareStatement(query);
			prep.setString(1, u.getName());
			prep.setString(2, u.getEmail());
			prep.setString(3, u.getPassword());
			prep.executeUpdate();
			f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
