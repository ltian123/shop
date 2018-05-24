package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlackFilter implements Filter{

	private FilterConfig config;
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String ip = req.getRemoteAddr();
		String black = config.getInitParameter("black");
		System.out.println("black:"+black+",ip:"+ip);
//		if(black.equals(ip)){
//			System.out.println("你是黑名单用户,无法访问该服务");
//			resp.sendRedirect(req.getContextPath()+"/black.html");
//			return;
//		}
		
		if(black.indexOf(ip) != -1){
			System.out.println("你是黑名单用户,无法访问该服务");
			resp.sendRedirect(req.getContextPath()+"/black.html");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config=config;
	}

}
