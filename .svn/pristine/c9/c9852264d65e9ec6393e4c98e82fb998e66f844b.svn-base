<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>EL表达式</title>
  </head>
  
  <body>
  	<%
  		pageContext.setAttribute("msg", "Hello pageContext");
  		request.setAttribute("msg", "Hello request");
  		session.setAttribute("msg", "Hello session");
  		application.setAttribute("msg", "Hello application");
  		
  		
  		User user = new User();
  		user.setUsername("张三");
  		user.setPhone("119");
  		request.setAttribute("user", user);
  		
  		User user1 = new User();
  		user1.setUsername("李四");
  		user1.setPhone("110");
  		
  		User user2 = new User();
  		user2.setUsername("王五");
  		user2.setPhone("120");
  		
  		User user3 = new User();
  		user3.setUsername("赵六");
  		user3.setPhone("114");
  		
  		List<User> users = new ArrayList<User>();
  		users.add(user);
  		users.add(user1);
  		users.add(user2);
  		users.add(user3);
  		
  		request.setAttribute("users", users);
  		
  		
  		User user4 = null;
  		request.setAttribute("user4", user4);
  	%>
    <h1>EL表达式</h1>
    <hr/>
    
    <h2>访问作用域中的数据</h2>
    request中的数据:${requestScope.msg }<br/>
    session中的数据:${sessionScope.msg }<br/>
    application中的数据:${applicationScope.msg }<br/>
    pageContext中的数据:${pageScope.msg }<br/>
    ${msg }
    
    <hr/>
    <h2>访问对象中的属性</h2>
    ${user.username }--${user.phone }--${user.password }<br/>
    ${users[0].username }--${users[1].username }
    <hr/>
    
    
    <h2>计算</h2>
    ${"aaa" eq "bbb" }<br/>
    ${"11" == 11 }<br/>
    ${1>5}<br/>
    ${1>5 ? "aaa" : "bbb" }<br/>
    ${empty user }<br/>
    ${empty user4 }<br/>
    
    <hr/>
<%--    <%=request.getContextPath() %>--%>
    ${pageContext.request.contextPath}/login.user
    
  </body>
</html>
