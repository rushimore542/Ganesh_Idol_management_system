package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.utils.JDBCUtils;

public class QRCodeDaoImpl implements QRCodeDao {

	private static final String INSERT_IMAGE = "INSERT INTO qrcodetable(oid,qrcodestring) VALUES (?,?)";
	private static final String Update_QR_IN_ORDER_TABLE =  "UPDATE ordertable SET qrid = ? WHERE oid = ?";
	private static final String GET_QRID =	"SELECT qrid From qrcodetable where oid = ?";
	@Override
	public int qrcodeStore(String base64Img,Long orderId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		base64Img="data:image/png;base64,"+base64Img;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(GET_QRID)) {
			preparedStatement.setLong(1,orderId);
			System.out.println(GET_QRID);
			ResultSet rs1 = preparedStatement.executeQuery();
			while(rs1.next()) {
				return 2;
			}
			flag=true;
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if(flag) {
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_IMAGE)) {
			preparedStatement.setLong(1,orderId);
			preparedStatement.setString(2, base64Img);
			System.out.println(preparedStatement);
			int rs = preparedStatement.executeUpdate();
			if (rs == 1) {

				try (Connection connectionnew = JDBCUtils.getConnection();
						PreparedStatement preparedStatementNew = connection.prepareStatement(GET_QRID)){
					preparedStatementNew.setLong(1,orderId);
					System.out.println(GET_QRID);
					ResultSet rs1 = preparedStatementNew.executeQuery();
					rs1.next();
					try (Connection connectionnew1 = JDBCUtils.getConnection();
							PreparedStatement preparedStatementNew1 = connection.prepareStatement(Update_QR_IN_ORDER_TABLE)){
						preparedStatementNew1.setLong(1,rs1.getLong("qrid"));
						preparedStatementNew1.setLong(2,orderId);
						System.out.println(Update_QR_IN_ORDER_TABLE);
						if( 1 == preparedStatementNew1.executeUpdate()) {
							return 1;
						}
						
					}

				}
				return -1;
			}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}
	return -1;
	}
}
	
