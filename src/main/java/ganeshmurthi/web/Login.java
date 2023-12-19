package ganeshmurthi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ganeshmurthi.dao.UserDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserDao userDao= new UserDao();
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
		//dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
        try {
        	HttpSession session;
            if (userDao.validate(email,password).equals("CUSTOMER_ROLE")) {
               
            	  session = request.getSession();
                 session.setAttribute("customer", email); 
            	RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerDashboard.html");
        		dispatcher.forward(request, response);
            }
            else if (userDao.validate(email,password).equals("VENDOR_ROLE")) {
               
            	 session = request.getSession();
                session.setAttribute("vendor", email);
                request.getRequestDispatcher("./VendorDashboard.html").forward(request, response);

            }
            else if (userDao.validate(email,password).equals("ORG_ROLE")) {
                
            	 session = request.getSession();
                session.setAttribute("org", email);
                request.getRequestDispatcher("OrganizationDashboard.html").forward(request, response);

            }
            else {
            	System.out.println("Error message = "+userDao.validate(email,password));
                request.setAttribute("errMessage", userDao.validate(email,password));
                RequestDispatcher dispatcher=request.getRequestDispatcher("login.html");
        		dispatcher.forward(request, response);
            
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
	}

}
