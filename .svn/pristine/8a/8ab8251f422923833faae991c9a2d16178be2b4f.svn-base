<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h1>我的订单</h1>
    <a href="<%=request.getContextPath() %>/findOrders.order">返回历史订单</a>
    <hr/>
    <%
    	Order order = (Order)request.getAttribute("order");
    %>
    
    <%=order.getUser().getUsername() %>用户<%=order.getNo() %>订单
    <table border="1">
    	<tr>
    		<th>序号</th>
    		<th>商品</th>
    		<th>数量</th>
    		<th>价钱</th>
    	</tr>
    	<%
    		Set<Item> items = order.getItems();
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
    --------------------------------<br/>
    共<%=order.getCount() %>个,总计<%=order.getPrice() %>元
  </body>
</html>
