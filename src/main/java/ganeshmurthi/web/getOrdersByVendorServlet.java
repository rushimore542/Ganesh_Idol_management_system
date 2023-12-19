package ganeshmurthi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ganeshmurthi.dao.OrderDao;
import ganeshmurthi.dao.OrderDaoImpl;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.OrderBean;
import ganeshmurthi.model.VendorBean;

/**
 * Servlet implementation class CreateBarcode
 */
@WebServlet("/getOrdersByVendorServlet")
public class getOrdersByVendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public OrderDao orders = null; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrdersByVendorServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		orders = new OrderDaoImpl();
		String jsonStr = null;
	        List<OrderBean> orderList = orders.getAllOdersOfVendor(Long.parseLong(request.getParameter("vid")));
			
	        response.setContentType("application/json");
	     // Get the printwriter object from response to write the required json object to the output stream      
	     PrintWriter out = response.getWriter();
	     // Assuming your json object is **jsonObject**, perform the following, it will return your json object  
	     ObjectMapper Obj = new ObjectMapper();  
         try {  
             // Converting the Java object into a JSON string  
              jsonStr = Obj.writeValueAsString(orderList);  
             // Displaying Java object into a JSON string  
             System.out.println(jsonStr);  
         }  
         catch (IOException e) {  
             e.printStackTrace();  
         }  
       
	     
	     
	     out.print(jsonStr);
	     out.flush();
		//doGet(request, response);
	}

}
