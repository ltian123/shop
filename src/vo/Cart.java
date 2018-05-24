package vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

import entity.Item;
import entity.Product;

public class Cart {

	private List<Item> items = new ArrayList<Item>();
	private Double price = 0.0;
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void add(int productId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = new StringBuffer()
					.append("select * ")
					.append("from t_product ")
					.append("where id = ? ")
					.toString();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			Product p = null;
			if(rs.next()){
				p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
			}
			
			//总价钱=当前价钱加上新增的商品价钱
			price+=p.getPrice();
			
			//遍历当前购物车中的订单明细,判断新添加的商品是否在购物车中已经存在
			//若存在,改变购物车中订单明细的数量
			//若不存在,创建一个新的明细
			for(Item item : items){
				//若存在
				if(item.getProduct().getName().equals(p.getName())){
					//该数量
					item.setNum(item.getNum()+1);
					//该明细中的总价
					item.setPrice(item.getPrice()+p.getPrice());
					conn.commit();
					return;
				}
			}
			
			//若不存在,创建一个新的明细
			Item item = new Item();
			item.setNum(1);
			item.setProduct(p);
			item.setPrice(p.getPrice());
			items.add(item);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			JDBCUtil.closeAll(conn, ps, rs);
		}
		
		
		
	}
	
	public void removeByIds(List<Integer> ids){
		for(Integer id : ids){
			removeById(id);
		}
	}
	
	
	
	public void removeById(int id){
//		for(Item item : items){
//			if(item.getProduct().getId().equals(id)){
//				price-=item.getPrice();
//				items.remove(item);
//			}
//		}
		for(int i = 0; i < items.size();i++){
			Item item = items.get(i);
			if(item.getProduct().getId().equals(id)){
				price-=item.getPrice();
				items.remove(item);
			}
		}
	}
	
	public void clear(){
		items = new ArrayList<Item>();
		price = 0.0;
	}
	
	
	public void modifyNumById(int id, int num){
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = new Product();
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			
			//根据id查询对应的商品
			String sql = new StringBuffer()
					.append("select id,name,price ")
					.append("from t_product ")
					.append("where id = ? ")
					.toString();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
			}
			
			
			//遍历订单,找到需要修改的商品所对应的明细信息
			for(Item item : items){
				//找到对应商品
				if(item.getProduct().getId().equals(id)){
					
					//先在总价的基础上减去原来的数量所对应的明细价钱
					price-=item.getPrice();
					
					//改数量
					item.setNum(num);
					//改价钱
					item.setPrice(product.getPrice()*num);
					//改总价
					//在减去原来价钱的基础上加上新数量所对应的明细价钱
					price+=item.getPrice();
					
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			JDBCUtil.closeAll(conn, ps, rs);
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(new Integer(1));
		list.add(new Integer(2));
		list.add(new Integer(3));
//		for (Integer i : list) {
//			if(i==3){
//				System.out.println(i);
//				list.remove(i);
//			}
//			
//		}
		
		for(int i = 0; i < list.size();i++){
			System.out.println(i);
			if(i==2){
				System.out.println(i);
				list.remove(i);
			}
		}
		
		
	}
	
}
