package ganeshmurthi.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ganeshmurthi.dao.AcceptDao;
import ganeshmurthi.dao.AcceptDaoImpl;

/**
 * Servlet implementation class AcceptIdol
 */
@WebServlet("/AcceptIdol")
public class AcceptIdol extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AcceptDao acceptDaoObj =null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptIdol() {
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
		
		 acceptDaoObj = new AcceptDaoImpl();
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
	       String responseFromServlet = acceptDaoObj.acceptIdol(orderid);
	       PrintWriter pw = response.getWriter();
	       response.setContentType("text/html");
	       pw.println(responseFromServlet);
	      
	}

}
