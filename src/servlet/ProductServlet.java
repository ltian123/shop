package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JDBCUtil;

import entity.Product;

public class ProductServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String path = request.getServletPath();
		
		if("/findAll.pro".equals(path)){
			findAll(request, response);
		}
		
	}
	
	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<Product>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = new StringBuffer()
					.append("select id,name,price ")
					.append("from t_product ")
					.toString();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				products.add(product);
			}
			
			request.setAttribute("products", products);
			request.getRequestDispatcher("/shop/list.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.closeAll(conn, ps, rs);
		}
		
		
		
		
		
		
		
		
		
		
	}
}
