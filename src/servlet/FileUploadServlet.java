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
		
		
		//׼��һ������
		//�����ṩ�����ļ�ʱ��һЩ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		//ͨ��������ȡ�������󹤾�
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//��������
		try {
			//��������뵽�����н��н���
			List<FileItem> items = fileUpload.parseRequest(request);
//			for (FileItem item : items) {
//				System.out.println(item.getFieldName());
//				System.out.println(item.getSize());
//			}
			
			
			//��Ϊlist��������������
			//��Ҫʹ�ü�ֵ�Եķ�ʽ���洢
			//��listת����map
			//key��Ӧԭ����name����
			Map<String,FileItem> map = new HashMap<String, FileItem>();
			for (FileItem item : items) {
				map.put(item.getFieldName(), item);
			}
			
			//��ȡ����
			String name = map.get("name").getString();
			System.out.println("name:"+name);
			
			FileItem file = map.get("file");
			System.out.println("�ļ���:"+file.getName());
			System.out.println("�ļ�����:"+file.getContentType());
			System.out.println("�ļ���С:"+file.getSize());
			
			//��ȡ�ļ�������
//			BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"utf-8"));
//			System.out.println(br.readLine());
			
			if(file.getName().endsWith(".jsp")){
				request.setAttribute("msg", "�����ϴ�.jsp���ļ�");
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
