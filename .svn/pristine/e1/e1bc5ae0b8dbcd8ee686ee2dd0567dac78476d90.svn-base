package servlet;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//在HttpServlet中可以直接调用
		ServletContext application = getServletContext();
		
		//还可以通过servletConfig获取
//		ServletContext application = getServletConfig().getServletContext();
		
		//储存数据
		application.setAttribute("", "");
		application.getAttribute("");
		
		
		//获取初始化参数值
		String abc = application.getInitParameter("abc");
		System.out.println("abc:"+abc);
		
		//获取指定文件所在应用所在的服务器所在的硬盘的具体路径
		String path = application.getRealPath("/jquery-1.4.1.min.js");
		System.out.println("path:"+path);
	}
}
