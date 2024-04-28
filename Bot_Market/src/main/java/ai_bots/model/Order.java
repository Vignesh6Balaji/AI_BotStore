package ai_bots.model;

public class Order extends Products{
	private int orderId;
	private int uId;
	private int q;
	private String date;
	
	public Order(int id, String name, String category, double price, String image, int orderId, int uId, int q,
			String date) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.uId = uId;
		this.q = q;
		this.date = date;
	}
	
	public Order(int id, String name, String category, double price, String image, int uId, int q, String date) {
		super(id, name, category, price, image);
		this.uId = uId;
		this.q = q;
		this.date = date;
	}
	
	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "BuyNow [orderId=" + orderId + ", uId=" + uId + ", q=" + q + ", date=" + date + "]";
	}
	
}
