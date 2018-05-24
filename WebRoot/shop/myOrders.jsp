<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <h1>历史订单</h1>
    <a href="<%=request.getContextPath() %>/findAll.pro">返回商品列表</a>
    <hr/>
    
    <table border="1">
    	<tr>
    		<th>序号</th>
    		<th>订单号</th>
    	</tr>
    	<%
    		List<Order> orders = (List<Order>)request.getAttribute("orders");
    		int i = 1;
    		for(Order order : orders){
    	%>
    	<tr>
    		<td><%=i++ %></td>
    		<td><a href="<%=request.getContextPath() %>/findOrderByOrderId.order?id=<%=order.getId() %>"><%=order.getNo() %></a></td>
    	</tr>
    	<%
    		}
    	%>
    </table>
    
  </body>
</html>
