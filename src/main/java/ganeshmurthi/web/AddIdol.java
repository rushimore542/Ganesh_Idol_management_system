package ganeshmurthi.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.json.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import ganeshmurthi.dao.ContentDao;
import ganeshmurthi.dao.ContentDaoImpl;
import ganeshmurthi.dao.IdolDao;
import ganeshmurthi.dao.IdolDaoImpl;
import ganeshmurthi.dao.ImageDao;
import ganeshmurthi.dao.ImageDaoImpl;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.ContentBean;
import ganeshmurthi.model.IdolBean;
import ganeshmurthi.model.Imagebean;

/**
 * Servlet implementation class AddIdol
 */
@WebServlet("/AddIdol")
@MultipartConfig
public class AddIdol extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private ContentDao contentDao;
	private IdolDao idolDao;
	private VendorDao vendorDao;
	private ImageDao imageDao;
	
    public AddIdol() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("VendorDashboard.html");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
//		String Type=request.getParameter("type");
//		String Size=request.getParameter("size");
//		String Colour =  request.getParameter("colour");
//		String Content1 = request.getParameter("content1");
//		String Content2 = request.getParameter("content2");
//		String Content3 = request.getParameter("content3");
//		String Price = request.getParameter("price");
//		String Base64String = request.getParameter("jsonString.type");
		//System.out.println("type selected "+Base64String);
		//System.out.println("image string "+ Base64String);
		//System.out.println("Content3 "+Content3);
		//Part part=request.getPart("imgfile");
		//String filename=part.getSubmittedFileName();
		//System.out.println("file"+filename);
		//String path="GaneshMurthi\\src\\main\\webapp\\assets\\"+"images"+File.separator+filename;
		//System.out.println(path);
		//part.write(path);
		
		
		StringBuilder stringBuilder = new StringBuilder();
	       BufferedReader bufferedReader = null;
	       try {
	         InputStream inputStream = request.getInputStream();
	         if (inputStream != null) {
	           bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	           char[] charBuffer = new char[128];
	           int bytesRead = -1;
	           while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	             stringBuilder.append(charBuffer, 0, bytesRead);
	           }
	         } else {
	           stringBuilder.append("");
	         }
	       } catch (IOException ex) {
	           throw ex;
	       } finally {
	         if (bufferedReader != null) {
	           try {
	             bufferedReader.close();
	           } catch (IOException ex) {
	             throw ex;
	           }
	         }
	       }
	       String body = stringBuilder.toString();
	       //System.out.println(body);
	       
	       ObjectMapper mapper = new ObjectMapper();
	       Map<String,Object> map = mapper.readValue(body, Map.class);
	       
	       System.out.println(map.get("type"));
	       
	       String type = (String) map.get("type");
	       String colour = (String) map.get("colour");
	       String sizeOfIdol = (String) map.get("sizeOfIdol");
	       String Price = (String) map.get("price");
	       String content = (String) map.get("content");
	       String base64String = (String) map.get("base64String1");
		
		String vusername = request.getSession(false).getAttribute("vendor").toString();
		
		long size = 0L;
		
		//System.out.println("con1 "+Content1);
		
		try {
		      vendorDao = new VendorDaoImpl();
		      Long vid = vendorDao.getVidFromVendor(vusername);
		      
		      int price = Integer.parseInt(Price);
			
//			String Content = null;
//			if(Content1 == null && Content2 != null && Content3 !=null) {
//				Content = Content2.concat(",");
//				Content = Content.concat(Content3);
//			}else if(Content2 == null && Content1 != null && Content3 !=null) {
//				Content = Content1.concat(",");
//				Content = Content.concat(Content3);
//			}else if(Content3 == null && Content1 != null && Content2 !=null) {
//				Content = Content1.concat(",");
//				Content = Content.concat(Content1);
//			}else if(Content1 == null && Content2==null && Content3 !=null) {
//				Content = Content3;
//			}else if(Content1 == null && Content3==null && Content2 !=null) {
//				Content = Content2;
//			}else if(Content2 == null && Content3==null && Content1 !=null) {
//				Content = Content1;
//			}else {
//				
//			}
			
			if(sizeOfIdol == "small") {
				size = 1;
			}else if(sizeOfIdol == "medium") {
				size = 2;
			}else {
				size =3;
			}
			ContentBean contentBean = new ContentBean(content);
			contentDao = new ContentDaoImpl();
			contentDao.regContent(contentBean);
			Long contentid = contentDao.getContentIdFromContent(content);
			
			Imagebean imageBean = new Imagebean(type, base64String);
			imageDao = new ImageDaoImpl();
			imageDao.regImage(imageBean);
			Long imageid = imageDao.getImageIdFromImageTable(base64String);
			
			IdolBean idolBean = new IdolBean(price,colour,size);
			idolDao = new IdolDaoImpl();
			idolDao.regIdol(idolBean,vid,contentid,imageid);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("VendorDashboard.html");
		dispatcher.forward(request, response);
	}

}
