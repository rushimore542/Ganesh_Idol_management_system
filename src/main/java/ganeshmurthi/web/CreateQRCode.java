package ganeshmurthi.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ganeshmurthi.dao.OrderDaoImpl;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.OrderBean;
import ganeshmurthi.model.VendorBean;

/**
 * Servlet implementation class CreateBarcode
 */
@WebServlet("/createQRcode")
public class CreateQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VendorDao vendors = null; 
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateQRCode() {
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
		vendors = new VendorDaoImpl();
		  
	        List<VendorBean> vendorList = vendors.getAllVendors();
			request.setAttribute("vendorList", vendorList);
 
			RequestDispatcher dispatcher = request.getRequestDispatcher("./qrreader/qrcodecreatevendor.jsp");
			dispatcher.forward(request, response);
		
		doGet(request, response);
	}

}