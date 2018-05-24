package servlet;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String name = request.getParameter("name");
//		String file = request.getParameter("file");
//		System.out.println("name:"+name+",file:"+file);
//		
//		InputStream in = request.getInputStream();
//		byte[] b = new byte[20480];
//		int i = in.read(b);
//		System.out.println(i);
//		System.out.println(new String(b,0,i));
		
		
		//准备一个工厂
		//用来提供解析文件时的一些参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//通过工厂获取解析请求工具
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//解析请求
		try {
			//将请求放入到工具中进行解析
			List<FileItem> items = fileUpload.parseRequest(request);
//			for (FileItem item : items) {
//				System.out.println(item.getFieldName());
//				System.out.println(item.getSize());
//			}
			
			
			//因为list操作起来不方便
			//需要使用键值对的方式来存储
			//将list转换成map
			//key对应原来的name属性
			Map<String,FileItem> map = new HashMap<String, FileItem>();
			for (FileItem item : items) {
				map.put(item.getFieldName(), item);
			}
			
			//获取数据
			String name = map.get("name").getString();
			System.out.println("name:"+name);
			
			FileItem file = map.get("file");
			System.out.println("文件名:"+file.getName());
			System.out.println("文件类型:"+file.getContentType());
			System.out.println("文件大小:"+file.getSize());
			
			//读取文件的内容
//			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"utf-8"));
//			System.out.println(br.readLine());
			
			if(file.getName().endsWith(".jsp")){
				request.setAttribute("msg", "不能上传.jsp的文件");
				request.getRequestDispatcher("upload.jsp").forward(request, response);
				return;
			}
			
			String path = getServletContext().getRealPath("upload");
			System.out.println("path:"+path);
			file.write(new File(path,file.getName()));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
