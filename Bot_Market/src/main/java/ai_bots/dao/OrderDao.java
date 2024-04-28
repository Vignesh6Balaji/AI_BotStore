package ai_bots.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ai_bots.model.Order;
import ai_bots.model.Products;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement prep;
	private ResultSet resset;
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		boolean result=false;
		try {
			query="insert into orders(product_id, user_id, order_quantity, order_date) values(?, ?, ?, ?)";
			prep=this.con.prepareStatement(query);
			prep.setInt(1, model.getId());
			prep.setInt(2, model.getuId());
			prep.setInt(3, model.getQ());
			prep.setString(4, model.getDate());
			prep.executeUpdate();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> userOrder(int id){
		List<Order> list=new ArrayList<Order>();
		try {
			query="select * from orders where user_id=? order by orders.order_id desc";
			prep=this.con.prepareStatement(query);
			prep.setInt(1, id);
			resset=prep.executeQuery();
			while(resset.next()) {
				Order order=new Order();
				ProductDao pdao=new ProductDao(this.con);
				int pid=resset.getInt("product_id");
				Products product=pdao.getSingleProduct(pid);
				order.setOrderId(resset.getInt("order_id"));
				order.setId(pid);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*resset.getInt("order_quantity"));
				order.setQ(resset.getInt("order_quantity"));
				order.setDate(resset.getString("order_date"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public void cancelOrder(int id) {
		try {
			query="delete from orders where order_id=?";
			prep=this.con.prepareStatement(query);
			prep.setInt(1, id);
			prep.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
