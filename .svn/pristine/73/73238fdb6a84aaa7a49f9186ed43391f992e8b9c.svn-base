<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商品列表</title>

  </head>
  
  <body>
    <h1>商品列表</h1>
    <%
    	User user = (User)session.getAttribute("user");
    	if(user!=null){
    %>
    	欢迎您:<span style="color:green;"><%=user.getUsername() %></span>
    	<a href="<%=request.getContextPath()%>/logout.user">注销</a>
    <%
    	}else{
    %>
        <a href="<%=request.getContextPath()%>/shop/login.jsp">登陆</a>
    	<a href="<%=request.getContextPath()%>/shop/regist.jsp">注册</a>
    <%
    	}
    %>
	<a href="<%=request.getContextPath() %>/findOrders.order">我的订单</a>
	当前在线人数:<%=application.getAttribute("count") %>人
	<hr/>
	
	
	<table border="1">
		<tr>
			<th>序号</th>
			<th>商品</th>
			<th>价格</th>
			<th>操作</th>
		</tr>
		<%
			int i = 1;
			List<Product> products = (List<Product>)request.getAttribute("products");
			for(Product p : products){
		%>
		<tr>
			<td><%=i++ %></td>
			<td><%=p.getName() %></td>
			<td><%=p.getPrice() %></td>
			<td><a href="<%=request.getContextPath() %>/add.cart?id=<%=p.getId() %>">添加购物车</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<a href="<%=request.getContextPath()%>/shop/cart.jsp">查看购物车</a>
  </body>
</html>