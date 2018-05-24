package servlet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JDBCUtil;
import util.MD5Util;
import vo.Cart;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import entity.User;

public class UserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if("/login.user".equals(path)){
			login(request, response);
		}
		if("/code.user".equals(path)){
			show(request, response);
		}
		if("/logout.user".equals(path)){
			logout(request, response);
		}
		if("/regist.user".equals(path)){
			regist(request, response);
		}
		
	}
	
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		//连接数据库
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			
			//判断用户名是否存在
			//若存在,抛出异常
			//若不存在,执行保存操作
			
			String sql = new StringBuffer()
					.append("select * ")
					.append("from t_user ")
					.append("where username = ? ")
					.toString();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			//若存在,给出错误信息
			if(rs.next()){
				request.setAttribute("registMsg", "用户名已经存在");
				request.getRequestDispatcher("/shop/regist.jsp").forward(request, response);
				return;
			}
			
			//若不存在,保存
			sql = new StringBuffer()
					.append("insert into ")
					.append("t_user(username,password,phone,email,address) ")
					.append("values(?,?,?,?,?) ")
					.toString();
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, MD5Util.md5(password));
			ps.setString(3, phone);
			ps.setString(4, email);
			ps.setString(5, address);
			
			ps.executeUpdate();
			conn.commit();
			
			response.sendRedirect(request.getContextPath()+"/shop/login.jsp");
			
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
	
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/findAll.pro");
		
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		//获取参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String codeInput = request.getParameter("code");
		String code = (String) request.getSession().getAttribute("code");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		//先比较验证码是否正确,若正确,再查询用户名与密码是否正确
		//若验证码不正确,不需要操作数据库
		if(codeInput.equals(code)){
			try {
				conn = JDBCUtil.getConnection();
				conn.setAutoCommit(false);
				String sql = new StringBuffer()
						.append("select id,username, ")
						.append("password,phone, ")
						.append("address,email ")
						.append("from t_user ")
						.append("where username = ? ")
						.append("and password = ? ")
						.toString();
				ps = conn.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, MD5Util.md5(password));
				rs = ps.executeQuery();
				if(rs.next()){
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setPhone(rs.getString("phone"));
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					conn.commit();
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("cart", new Cart());
					response.sendRedirect(request.getContextPath()+"/findAll.pro");
					return;
				}
				request.setAttribute("loginMsg", "用户名或密码错误");
				request.getRequestDispatcher("/shop/login.jsp").forward(request, response);
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
		}else{
			request.setAttribute("loginMsg", "验证码错误");
			request.getRequestDispatcher("/shop/login.jsp").forward(request, response);
		}
		
	}
	
	protected void show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Random random = new Random();
		
		//在内存中创建画板
		BufferedImage image = new BufferedImage(60, 25, BufferedImage.TYPE_INT_RGB);
		
		//创建画笔
		Graphics graphics = image.getGraphics();
		
		//填充背景
		graphics.fillRect(0, 0, 60, 25);
		
		
		//设置画笔颜色
		//颜色
		//new Random().nextInt(2)
		graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
		
		//设置字体
		graphics.setFont(new Font("宋体", Font.BOLD+Font.ITALIC, 18));
		
		
		//生成一个随机数
		String str = "0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuffer code = new StringBuffer();
		for(int i = 0; i < 4; i++){
			code.append(str.charAt(new Random().nextInt(str.length())));
		}
		
		//将生成的字符串写入到图片上
		graphics.drawString(code.toString(), 10, 20);
		
		//将生成的随机数放入到session中,方便后期校验
		request.getSession().setAttribute("code", code.toString());
		System.out.println("当前验证码:"+code.toString());
		
		OutputStream out = response.getOutputStream();
		
		//将图片压缩成JPEG格式写入到out中
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		
		encoder.encode(image);
		
		
	}
}
