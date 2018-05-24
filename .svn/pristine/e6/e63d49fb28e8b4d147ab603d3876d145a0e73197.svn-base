<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <%
  	Map<String,User> map = new HashMap<String,User>();
  
  	User user1 = new User();
  	user1.setUsername("张三");
  	
  	
  	User user2 = new User();
  	user2.setUsername("李四");
  	
  	
  	User user3 = new User();
  	user3.setUsername("王五");
  	
  	
  	User user4 = new User();
  	user4.setUsername("赵六");
  	
  	map.put("aa", user1);
  	map.put("bb", user2);
  	map.put("cc", user3);
  	map.put("dd", user4);
  	
  	request.setAttribute("map", map);
  %>
  
  ${map.aa.username }
  <hr/>
  
  
  <c:forEach items="${map }" var="entry">
  	${entry.key }--${entry.value.username }<br/>
  </c:forEach>
  
  
  
  
  
  
  
  </body>
</html>
