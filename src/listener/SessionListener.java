package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	private int count = 1;
	
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionListener.sessionCreated()");
		ServletContext application = event.getSession().getServletContext();
		//application是大家共享的,整个项目只有1个,会存在线程安全问题
		synchronized (this) {
			application.setAttribute("count", count++);
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
