package ganeshmurthi.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import ganeshmurthi.dao.QRCodeDao;
import ganeshmurthi.dao.QRCodeDaoImpl;
 

/**
 * Servlet implementation class QRCodeCreator
 */
@WebServlet("/QRCodeCreator")
public class QRCodeCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QRCodeCreator() {
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
	
		 // The data that the QR code will contain
        String data = request.getParameter("oid");
         QRCodeDao qr = new QRCodeDaoImpl();
        // Encoding charset
        String charset = "ISO-8859-1";
        
        int width = 200;
        int height = 200;
 
        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
            = new HashMap<EncodeHintType,
                          ErrorCorrectionLevel>();
 
        hashMap.put(EncodeHintType.ERROR_CORRECTION,
                    ErrorCorrectionLevel.L);
		
		BitMatrix matrix = null;
		try {
			matrix = new MultiFormatWriter().encode(
			        new String(data.getBytes(charset), charset),
			        BarcodeFormat.QR_CODE, width, height);
		} catch (UnsupportedEncodingException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		BufferedImage bi= MatrixToImageWriter.toBufferedImage(matrix);
		
	      /*  MatrixToImageWriter.writeToFile(
	            matrix,
	            path.substring(path.lastIndexOf('.') + 1),
	            new File(path));
	        */
		
	        PrintWriter out = response.getWriter();

	        ImageIO.write(bi, "png", baos);
	        byte[] bytes = baos.toByteArray(); 
	        String bytesBase64 = Base64.encodeBase64String(bytes);
	        int res = qr.qrcodeStore(bytesBase64,Long.parseLong(data));
	        if(res == 1 || res == 2) {
	        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	        out.write(bytesBase64);       // Write response body.	   
		     out.flush();
	        }else {
	  	       response.setContentType("text/html");
	  	       out.print("QR NOT CREATED");
	        }
	        
		//doGet(request, response);
	}

}
