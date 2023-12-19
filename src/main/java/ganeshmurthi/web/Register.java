package ganeshmurthi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ganeshmurthi.dao.CustomerDao;
import ganeshmurthi.dao.CustomerDaoImpl;
import ganeshmurthi.dao.OrganizationDao;
import ganeshmurthi.dao.UserDao;
import ganeshmurthi.dao.VendorDao;
import ganeshmurthi.dao.VendorDaoImpl;
import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.model.OrganizationBean;
import ganeshmurthi.model.UserBean;
import ganeshmurthi.model.VendorBean;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserDao userDao= new UserDao();
	private CustomerDao customerdao ;
	private VendorDao vendorDao;
	private OrganizationDao orgDao = new OrganizationDao();
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("register.html");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String FirstName=request.getParameter("firstName");
		String LastName=request.getParameter("lastName");
		String Email=request.getParameter("email");
		String Phone=request.getParameter("phone");
		String Address=request.getParameter("address");
		String Role=request.getParameter("role");
		String password=request.getParameter("password");
		String cpassword=request.getParameter("cpassword");
		
		
		int RollId = Integer.parseInt(Role);
		
			UserBean user = new UserBean(RollId,Email,password);
			CustomerBean customer = new CustomerBean(RollId, FirstName, LastName, Email, Phone, Address);
			customerdao = new CustomerDaoImpl();
			
			VendorBean vendor = new VendorBean(RollId, FirstName, LastName, Email, Phone, Address);
			vendorDao = new VendorDaoImpl();
		    
			String Name = FirstName.concat(LastName);
			OrganizationBean org = new OrganizationBean(RollId, Name, Email, Phone, Address);
			
		
		try {
			if(userDao.userAlreadyPresent(Email)==false)
			{
				//request.setAttribute("errMessage", "Registered successfully");
				userDao.registerUser(user);
				if(RollId == 1) {
					customerdao.registerCustomer(customer);
				}else if(RollId == 2) {
					vendorDao.registerVendor(vendor);
				}else {
					orgDao.registerOrg(org);
				}
			}
			else
			{
				//request.setAttribute("errMessage", "email already registered");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
