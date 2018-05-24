<%@ page language="java" import="java.util.*,entity.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  	<%
  		People p = new People();
  		p.setAge(20);
  		p.setName("张三");
  		request.setAttribute("p", p);
  	%>
    <h1>JSTL标签库</h1>
    ${p.age <22 }
    <c:choose>
    	<c:when test="${p.age < 18 }">未成年</c:when>
    	<c:when test="${p.age < 30 }">青年</c:when>
    	<c:when test="${p.age < 50 }">中年</c:when>
    	<c:otherwise>老年</c:otherwise>
    </c:choose>
    <hr/>
    <c:if test="${p.age < 18 }">未成年</c:if>
   	<c:if test="${p.age > 17 or p.age < 30 }">青年</c:if>
   	<c:if test="${p.age > 29 or p.age < 50 }">中年</c:if>
    
    
    <hr/>
    
<%--    <c:catch var="exc">${1/0 }</c:catch>--%>
<%--    ${exc }<br/>--%>
<%--    ${exc.message }--%>
    
    
    <a href='<c:url value="index.jsp"></c:url>'>aaaa</a><br/>
    
    <%
    	request.setAttribute("now", new Date());
    %>
    
    time:<fmt:formatDate value="${now }" type="time"/><br/>
    date:<fmt:formatDate value="${now }" type="date"/><br/>
    both:<fmt:formatDate value="${now }" type="both"/><br/>
    pattern:<fmt:formatDate value="${now }" pattern="yyyy/MM/dd HH:mm:ss Z"/><br/>
    
    
    
    
    
    
    
    
  </body>
</html>
