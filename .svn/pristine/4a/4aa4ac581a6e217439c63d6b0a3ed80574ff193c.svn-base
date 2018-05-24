<%@ page language="java" import="java.util.*,entity.*,vo.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>确认订单</title>
  </head>
  
  <body>
    <h1>确认订单</h1>
    <hr/>
    
    <%
    	User user = (User)session.getAttribute("user");
    	if(user !=null){
    %>
           用户:<%=user.getUsername() %><br/>
           电话:<%=user.getPhone() %><br/>
           地址:<%=user.getAddress() %><br/>
           邮箱:<%=user.getEmail() %><br/>
     <%
    	}
     %>
    -------------------------------------------------------<br/>
            
     <table border="1">
		<tr>
			<th>序号</th>
			<th>商品</th>
			<th>数量</th>
			<th>价格</th>
		</tr>
		<%
			Cart cart = (Cart)session.getAttribute("cart");
			List<Item> items = new ArrayList<Item>();
			if(cart != null){
				items = cart.getItems();
			}
			int i = 1;
			for(Item item : items){
		%>
		<tr>
			<td><%=i++ %></td>
			<td><%=item.getProduct().getName() %></td>
			<td><%=item.getNum() %></td>
			<td><%=item.getPrice() %></td>
		</tr>
		<%
			}
		%>
	</table>
	-----------------------------------------------<br/>
	总计:
	<%
		double price = 0;
		if(cart != null){
			price = cart.getPrice();
			out.print(price);
		}
	%>
	元<br/>
	<a href="<%=request.getContextPath()%>/create.order">生成订单</a>
	<a href="<%=request.getContextPath()%>/shop/cart.jsp">返回</a>
  </body>
</html>
