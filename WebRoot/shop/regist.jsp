<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  <body>
  	<h1>用户注册</h1>
  	<a href="login.jsp">返回登录</a>
  	<hr/>
  	
  	
  	<form action="<%=request.getContextPath() %>/regist.user" method="post">
  		用户名:<input type="text" name="username" /><br/>
  		密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" /><br/>
  		手机号:<input type="text" name="phone" /><br/>
  		邮&nbsp;&nbsp;&nbsp;箱:<input type="text" name="email" /><br/>
  		地&nbsp;&nbsp;&nbsp;址:<input type="text" name="address" /><br/>
  		<input type="submit" value="注册">
  		<span style="color: red;font-size: 12px;">
  		<%
  			String registMsg = (String)request.getAttribute("registMsg");
  			if(registMsg!=null){
  				out.print(registMsg);
  			}
  		%>
  		</span>
  	</form>
  </body>
</html>
