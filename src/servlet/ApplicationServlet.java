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
		//��HttpServlet�п���ֱ�ӵ���
		ServletContext application = getServletContext();
		
		//������ͨ��servletConfig��ȡ
//		ServletContext application = getServletConfig().getServletContext();
		
		//��������
		application.setAttribute("", "");
		application.getAttribute("");
		
		
		//��ȡ��ʼ������ֵ
		String abc = application.getInitParameter("abc");
		System.out.println("abc:"+abc);
		
		//��ȡָ���ļ�����Ӧ�����ڵķ��������ڵ�Ӳ�̵ľ���·��
		String path = application.getRealPath("/jquery-1.4.1.min.js");
		System.out.println("path:"+path);
	}
}
