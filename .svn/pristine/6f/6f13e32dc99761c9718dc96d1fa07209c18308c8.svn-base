package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.Cart;

public class CartServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Ö¸¶¨±àÂë
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String path = request.getServletPath();
		if("/add.cart".equals(path)){
			addCart(request, response);
		}
		
		if("/removeById.cart".equals(path)){
			removeById(request, response);
		}
		if("/removeByIds.cart".equals(path)){
			removeByIds(request, response);
		}
		if("/clear.cart".equals(path)){
			clear(request, response);
		}
		if("/modify.cart".equals(path)){
			modifyCart(request, response);
		}
		
	}
	
	protected void modifyCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("productId"));
		int num = Integer.parseInt(request.getParameter("num"));
//		System.out.println("productId:"+productId+",num:"+num);
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.modifyNumById(id, num);
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
	}
	
	protected void removeByIds(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] productIds = request.getParameterValues("productIds");
		System.out.println("ids===="+Arrays.toString(productIds));
		List<Integer> ids = new ArrayList<Integer>();
		for(String id : productIds){
			ids.add(Integer.parseInt(id));
		}
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeByIds(ids);
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
	}
	
	protected void removeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.removeById(Integer.parseInt(id));
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/shop/cart.jsp");
		
	}
	
	protected void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart = new Cart();
		}
		
		cart.add(id);
		request.getSession().setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/findAll.pro");
	}
	
}
