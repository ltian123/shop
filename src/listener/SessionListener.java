package listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener{

	private int count = 1;
	
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("SessionListener.sessionCreated()");
		ServletContext application = event.getSession().getServletContext();
		//application�Ǵ�ҹ����,������Ŀֻ��1��,������̰߳�ȫ����
		synchronized (this) {
			application.setAttribute("count", count++);
		}
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
