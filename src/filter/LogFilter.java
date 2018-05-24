package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String ip = req.getRemoteAddr();
		String uri = req.getRequestURI();
		System.out.println(ip+"��"+new Date()+"������"+uri+"����");
		//����,ִ����һ�����,��һ����servlet,Ҳ��������һ��������
		chain.doFilter(request, response);
		System.out.println(ip+"��"+new Date()+"������"+uri+"����");
		
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
