package ai_bots.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import ai_bots.model.Cart;
import ai_bots.model.Products;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement prep;
	private ResultSet resset;

	public ProductDao(Connection con) {
		this.con = con;
	}

	public List<Products> getAllProducts() {
		List<Products> prods = new ArrayList<Products>();
		try {
			query = "select * from products";
			prep = this.con.prepareStatement(query);
			resset = prep.executeQuery();
			while (resset.next()) {
				Products row = new Products();
				row.setId(resset.getInt("id"));
				row.setName(resset.getString("name"));
				row.setCategory(resset.getString("category"));
				row.setPrice(resset.getDouble("price"));
				row.setImage(resset.getString("image"));

				prods.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prods;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartlist) {
		List<Cart> products = new ArrayList<Cart>();
		try {
			if (cartlist.size() > 0) {
				for (Cart item : cartlist) {
					query = "select * from products where id=?";
					prep = this.con.prepareStatement(query);
					prep.setInt(1, item.getId());
					resset = prep.executeQuery();
					while (resset.next()) {
						Cart row = new Cart();
						row.setId(resset.getInt("id"));
						row.setName(resset.getString("name"));
						row.setCategory(resset.getString("category"));
						row.setPrice(resset.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}

	public double getTotalPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (!cartList.isEmpty()) {
				for (Cart item : cartList) {
					query = "select price from products where id=?";
					prep = this.con.prepareStatement(query);
					prep.setInt(1, item.getId());
					resset = prep.executeQuery();
					while (resset.next()) {
						sum += resset.getDouble("price") * item.getQuantity();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sum;
	}
	public Products getSingleProduct(int id) {
		Products row=null;
		try {
			query="select * from products where id=?";
			prep=this.con.prepareStatement(query);
			prep.setInt(1, id);
			resset=prep.executeQuery();
			if(resset.next()) {
				row=new Products();
				row.setId(id);
				row.setCategory(resset.getString("category"));
				row.setName(resset.getString("name"));
				row.setImage(resset.getString("image"));
				row.setPrice(resset.getDouble("price"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
