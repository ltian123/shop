package entity;

import java.util.HashSet;
import java.util.Set;

public class Order {

	private Integer id;
	private User user;
	private String no;
	private Double price;
	private Set<Item> items = new HashSet<Item>();
	
	//添加一条新的订单
	public void addItem(Item item){
		items.add(item);
	}
	
	//查询订单中包含多少条明细信息
	//非数据库中对应表的字段
	private Integer count;
	
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public Order() {
		super();
	}
	
}
