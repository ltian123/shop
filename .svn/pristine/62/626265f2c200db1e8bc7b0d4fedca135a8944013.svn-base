<%@ page language="java" import="java.util.*,vo.*,entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车</title>
<%--    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.4.1.min.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#c").click(function(){
    			var allCheck = $("input[type='checkbox']");
    			//1.4版本
<%--    			$(allCheck).attr("checked",$("#c").attr("checked"));--%>
    			//1.9版本
    			$(allCheck).prop("checked",$("#c").prop("checked"));
    		});
    		
    		$("#remove").click(function(){
    			$("#cartForm").submit();
    		});
    		
    		$("a[name='modify']").click(function(){
    			var productId = $("input[type='checkbox']",$(this).parent().parent()).val();
    			var num = $("input[type='text']",$(this).parent().parent()).val();
<%--    			alert(productId+"*******"+num);--%>
    			$(this).attr("href","<%=request.getContextPath() %>/modify.cart?productId="+productId+"&num="+num);
    		});
    	});
    </script>
  </head>
  
  <body>
    <h1>我的购物车</h1>
    <hr/>
    
    <%
    	Cart cart = (Cart)session.getAttribute("cart");
    	List<Item> items = new ArrayList<Item>();
    	if(cart!=null){
    		items = cart.getItems();
    	}
    %>
    <form id="cartForm" action="<%=request.getContextPath() %>/removeByIds.cart" method="post">
    <table border="1">
		<tr>
			<th><input type="checkbox" id="c" />全选</th>
			<th>序号</th>
			<th>商品</th>
			<th>数量</th>
			<th>价格</th>
			<th>操作</th>
		</tr>
		<%
			int i = 1;
			for(Item item : items){
		%>
		<tr>
			<td><input type="checkbox" name="productIds" value="<%=item.getProduct().getId() %>" /></td>
			<td><%=i++ %></td>
			<td><%=item.getProduct().getName() %></td>
			<td><input type="text" name="num" value="<%=item.getNum() %>" style="border:0" /></td>
			<td><%=item.getPrice() %></td>
			<td>
				<a href="<%=request.getContextPath() %>/removeById.cart?id=<%=item.getProduct().getId() %>" onclick="return confirm('是否确认删除')">删除</a>
				<a href="#" name="modify">修改</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	-------------------------------------------------------------<br/>
	总计:
	<%
		double price = 0;
		if(cart != null){
			price = cart.getPrice();
		}
		out.print(price);
	%>
	元<br/>
	<a href="javascript:;" id="remove">删除选中项</a>
	<a href="<%=request.getContextPath() %>/clear.cart">清空购物车</a>
	<a href="<%=request.getContextPath()%>/findAll.pro">继续购物</a>
	<a href="<%=request.getContextPath()%>/shop/order.jsp">结算</a>
	</form>
  </body>
</html>
