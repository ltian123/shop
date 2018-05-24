<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  	function changecode(){
  		document.getElementById("image").src="<%=request.getContextPath()%>/code.user?id="+new Date().getTime();
  	}
  </script>
  </head>
   
  <body>
  	<h1>用户登录</h1>
  	<a href="<%=request.getContextPath() %>/shop/regist.jsp">返回注册</a>
  	<hr/>
    <form action="<%=request.getContextPath() %>/login.user" method="post">
    	用户名:<input type="text" name="username" /><br/>
    	密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password" /><br/>
    	验证码:<input type="text" name="code" />
    	<a href="javaScript:;" onclick="changecode()">
    		<img id="image" alt="图片加载失败" src="<%=request.getContextPath()%>/code.user">
    	</a>
    	<a href="javaScript:;">换一张</a><br/>
    	<input type="submit" value="登录"/>
    	<span style="color:red;">
    	<%
    		String loginMsg = (String)request.getAttribute("loginMsg");
    		if(loginMsg!=null){
    			out.print(loginMsg);
    		}
    	%>
    	</span>
    </form>
  </body>
</html>
