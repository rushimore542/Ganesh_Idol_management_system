package ganeshmurthi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ganeshmurthi.dao.CustomerDao;
import ganeshmurthi.dao.CustomerDaoImpl;
import ganeshmurthi.dao.IdolDao;
import ganeshmurthi.dao.IdolDaoImpl;
import ganeshmurthi.dao.ImageDao;
import ganeshmurthi.dao.ImageDaoImpl;
import ganeshmurthi.dao.OrderDao;
import ganeshmurthi.dao.OrderDaoImpl;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.model.OrderBean;

/**
 * Servlet implementation class Order
 */
@WebServlet("/order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	OrderDao orderDao;
	CustomerDao customerDao;
	IdolDao idolDao;
	ImageDao imageDao;
	
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("CheckImages.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String Type=request.getParameter("type");
		String Colour=request.getParameter("colour");
		String Size=request.getParameter("size");
		String Desposible=request.getParameter("desposible");
		
		int size = Integer.parseInt(Size);
		
		String cusername = request.getSession(false).getAttribute("customer").toString();
		
		try {
			customerDao = new CustomerDaoImpl();
		    Long cid = customerDao.getCidFromVendor(cusername);
		    
		    CustomerBean customerDetails;
		    customerDetails = customerDao.getCustomerDetails(cid);
		    
		    idolDao = new IdolDaoImpl();
		    
		    Long imageId = idolDao.getImageIdfromIdolTable(Size,Colour);
		   
		    Long vid = idolDao.getVidFromImageId(imageId);
		    
		    Long idolId = idolDao.getIdolIdfromIdolTable(Size,Colour);
		    
		    OrderBean orderBean = new OrderBean(cid,vid,idolId,imageId,1L,1L,"yes","complete");
		    orderDao = new OrderDaoImpl();
			orderDao.regOrder(orderBean);
			
			ArrayList<String> lst1 = new ArrayList<String>();
			//ArrayList<String> lst2 = new ArrayList<String>();
			
			imageDao = new ImageDaoImpl();
			lst1 = imageDao.getImageFromType(Type);
			
//			for(int i=0;i<lst1.size();i++) {
//				String pathString = lst1.get(i);
//				String newPathString = pathString.replace("GaneshMurthi\\src\\main\\webapp", ".");
//				lst2.add(newPathString);
//			}
			
			request.setAttribute("list", lst1);
			
			System.out.println(lst1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("./CheckImages.jsp");
		dispatcher.forward(request, response);
	}

}
