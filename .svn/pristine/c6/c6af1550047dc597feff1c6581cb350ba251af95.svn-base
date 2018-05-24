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
		System.out.println(ip+"在"+new Date()+"发送了"+uri+"请求");
		//放行,执行下一个组件,不一定是servlet,也可能是下一个过滤器
		chain.doFilter(request, response);
		System.out.println(ip+"在"+new Date()+"处理了"+uri+"请求");
		
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
