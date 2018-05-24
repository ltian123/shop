package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JDBCUtil;
import vo.Cart;
import entity.Item;
import entity.Order;
import entity.Product;
import entity.User;

public class OrderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//指定编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String path = request.getServletPath();
		
		if("/create.order".equals(path)){
			createOrder(request, response);
		}
		if("/findOrders.order".equals(path)){
			findOrdersByUserId(request, response);
		}
		if("/findOrderByOrderId.order".equals(path)){
			findOrderByOrderId(request, response);
		}
		
		
	}
	
	protected void findOrderByOrderId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取请求中的订单编号
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			String sql = new StringBuffer()
					.append("select u.username, o.no,   ")
					.append("p.name,i.num,i.price,o.price,   ")
					.append("(select count(1) from t_item    ")
					.append("where order_id = o.id) as count   ")
					.append("from t_order o   ")
					.append("join t_item i    ")
					.append("on o.id = i.order_id   ")
					.append("join t_user u   ")
					.append("on u.id = o.user_id   ")
					.append("join t_product p    ")
					.append("on i.product_id = p.id    ")
					.append("where o.id = ?  ")
					.toString();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Order order = null;
			while(rs.next()){
				if(order == null){
					order = new Order();
					//不变的数据
					order.setNo(rs.getString("o.no"));
					order.setCount(rs.getInt("count"));
					order.setPrice(rs.getDouble("o.price"));
					User user = new User();
					user.setUsername(rs.getString("u.username"));
					order.setUser(user);
				}
				Item item = new Item();
				item.setNum(rs.getInt("i.num"));
				item.setPrice(rs.getDouble("i.price"));
				Product product = new Product();
				product.setName(rs.getString("p.name"));
				item.setProduct(product);
				order.addItem(item);
			}
			request.setAttribute("order", order);
			request.getRequestDispatcher("/shop/myOrder.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.closeAll(conn, ps, rs);
		}
		
		
	}
	
	protected void findOrdersByUserId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从session取出user信息,获取当前登录用户的id
		User user = (User) request.getSession().getAttribute("user");
		
		
		//根据当前用户的user_id获取对应的所有的订单信息
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = new StringBuffer()
						.append("select id,no ")
						.append("from t_order ")
						.append("where user_id = ? ")
						.toString();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setNo(rs.getString("no"));
				orders.add(order);
			}
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/shop/myOrders.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.closeAll(conn, ps, rs);
		}
		
		
		
		
	}
	
	/**
	 * 步骤
	 * 1.从session中获取购物车的信息以及用户信息
	 * 2.随机生成一个订单号
	 * 3.保存订单并返回其主键
	 * 4.保存明细
	 * 5.跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void createOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取session中的用户信息
		User user = (User) request.getSession().getAttribute("user");
		
		//获取购物车信息
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		//随机生成一个订单号
		String no = createNo();
		
		//保存订单,并返回主键
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			String sql = new StringBuffer()
					.append("insert into ")
					.append("t_order(user_id,no,price) ")
					.append("values(?,?,?) ")
					.toString();
//			ps = conn.prepareStatement(sql);
			//表示保存时返回主键
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getId());
			ps.setString(2, no);
			ps.setDouble(3, cart.getPrice());
			ps.executeUpdate();
			
			//获取返回的主键
			//因为只有主键是自动生成,所以该结果集中只有一列id的数据
			rs = ps.getGeneratedKeys();
			
			Integer orderId = null;
			if(rs.next()){
				orderId = rs.getInt(1);
			}
			System.out.println("orderId:"+orderId);
			
			sql = new StringBuffer()
					.append("insert into ")
					.append("t_item(product_id,num,order_id,price) ")
					.append("values(?,?,?,?) ")
					.toString();
			ps = conn.prepareStatement(sql);
			List<Item> items = cart.getItems();
			//遍历订单明细,每条数据保存一次
			for (Item item : items) {
				ps.setInt(1, item.getProduct().getId());
				ps.setInt(2, item.getNum());
				ps.setInt(3, orderId);
				ps.setDouble(4, item.getPrice());
				//每条数据保存一次
				ps.executeUpdate();
			}
			conn.commit();
			request.getSession().setAttribute("no", no);
			request.getSession().setAttribute("cart", new Cart());
			response.sendRedirect(request.getContextPath()+"/shop/success.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			JDBCUtil.closeAll(conn, ps, null);
		}
		
		
	}
	
	
	
	
	public static String createNo(){
		Random random = new Random();
		String s = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 4; i++){
			sb.append(s.charAt(random.nextInt(s.length())));
		}
		return sb.toString()+new Date().getTime();
		
	}
	
	public static void main(String[] args) {
		System.out.println(createNo());
	}
	
	
}
