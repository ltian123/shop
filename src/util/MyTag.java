package util;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MyTag extends SimpleTagSupport {
	
	private int num;
	private String info;
	

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	@Override
	public void doTag() throws JspException, IOException {
		PageContext context = (PageContext) getJspContext();
		JspWriter out = context.getOut();
		for(int i = 0; i < num; i++){
			out.print(info+"<br/>");
		}
	}
}
