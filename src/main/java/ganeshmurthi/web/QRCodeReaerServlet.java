package ganeshmurthi.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import ganeshmurthi.dao.ContentDao;
import ganeshmurthi.dao.ContentDaoImpl;
import ganeshmurthi.dao.CustomerDao;
import ganeshmurthi.dao.CustomerDaoImpl;
import ganeshmurthi.dao.IdolDao;
import ganeshmurthi.dao.IdolDaoImpl;
import ganeshmurthi.dao.OrderDao;
import ganeshmurthi.dao.OrderDaoImpl;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.ContentBean;
import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.model.IdolBean;
import ganeshmurthi.model.OrderBean;
import ganeshmurthi.model.VendorBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QRCodeReaerServlet
 */
@WebServlet("/QRCodeReaerServlet")
public class QRCodeReaerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao order;
	private CustomerDao customer;
    private VendorDao vendor;   
    private IdolDao idol;
    private ContentDao content;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRCodeReaerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       System.out.println("reached to post");
       OrderBean orderData = null;
       CustomerBean customerData = null;
       VendorBean vendorData = null;
       IdolBean idolData =  null;
       ContentBean contentData =  null;
      //read body 
       
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
       Long orderid = Long.parseLong(body);
       order = new OrderDaoImpl();
       customer = new CustomerDaoImpl();
       vendor = new VendorDaoImpl();
       idol = new IdolDaoImpl();
       content = new  ContentDaoImpl();
       
       
	try {
		orderData = order.getOrderDetails(orderid);
		customerData  = customer.getCustomerDetails(orderData.getCid()) ;
		vendorData = vendor.getVendorDetails(orderData.getVid());
		idolData = idol.getIdolInfo(orderData.getIdolid());
		contentData = content.getContent(idolData.getIdolid());
	} catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       RequestDispatcher dispatcher = request.getRequestDispatcher("/barcodescan/orderinfo.jsp");
		request.setAttribute("orderData", orderData);
		request.setAttribute("customerData", customerData);
		request.setAttribute("vendorData", vendorData);
		request.setAttribute("idolData", idolData);
		request.setAttribute("contentData", contentData);
		dispatcher.forward(request, response);


	//	doGet(request, response);
	}

}
