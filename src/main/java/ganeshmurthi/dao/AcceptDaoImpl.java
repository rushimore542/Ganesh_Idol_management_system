package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.model.OrderBean;
import ganeshmurthi.utils.JDBCUtils;

public class AcceptDaoImpl implements AcceptDao {

	private static final String CHECK_IDOL_ACCEPTED_BEFORE = "SELECT managementid ,idolid FROM ordertable where oid = ? ";
	private static  String GET_DISPOSABLE_STATUS = "SELECT isdisposable  FROM idoltable where idolid = ? ";
	private static final String INSERT_INTO_MANAGEMENT_TABLE = "INSERT INTO idolmanagement(cid,oid,qrid,qridused,orgid) VALUES (?,?,?,?,?) ";
	private static final String GET_IDOLID= "SELECT idolid From ordertable where oid = ?";
	private static final String Update_Management_Status = "UPDATE ordertable SET managementid = 1 where oid = ?";
	
	@Override
	public String acceptIdol(Long orderid) {
			
			String acceptResult = null;
			OrderBean order = new OrderBean();
			OrderDao orderAccess = new OrderDaoImpl();
			Long idolid =  null;
			try (Connection connection = JDBCUtils.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(CHECK_IDOL_ACCEPTED_BEFORE)) {
				preparedStatement.setLong(1,orderid);
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					// to indicate idol alreday accepted as 1 is bydefault value for int and long is 0
					if(rs.getLong("managementid")==0)
						break;
					acceptResult="idol already collected!";
					return acceptResult;
					
				}
				
				try ( PreparedStatement preparedStatement3 = connection.prepareStatement(GET_IDOLID)) {
					preparedStatement3.setLong(1, orderid);
					System.out.println(preparedStatement3);
					 rs = preparedStatement3.executeQuery();
					 while (rs.next()) {
						  idolid = rs.getLong("idolid");
					 }

				}

				
				try ( PreparedStatement preparedStatement1 = connection.prepareStatement(GET_DISPOSABLE_STATUS)) {
					preparedStatement1.setLong(1,idolid);
					System.out.println(preparedStatement1);

					 rs = preparedStatement1.executeQuery();
					 
					 while (rs.next()) {
						 String responseofQuery = rs.getString("isdisposable");
						 if(responseofQuery.equalsIgnoreCase("yes")) {
							 order = orderAccess.getOrderDetails(orderid);
								try ( PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_INTO_MANAGEMENT_TABLE)) {
									preparedStatement2.setLong(1, order.getCid());
									preparedStatement2.setLong(2, order.getOid());
									preparedStatement2.setLong(3, order.getQrid());
									preparedStatement2.setString(4, "yes");
									//NEED TO UPDATE ORAGANIZATION ID ONCE MODULE COMPLETED
									preparedStatement2.setLong(5, 12);
									System.out.println(preparedStatement2);
								    preparedStatement2.executeUpdate();
									try ( PreparedStatement preparedStatement5 = connection.prepareStatement(Update_Management_Status)) {
										preparedStatement5.setLong(1,orderid);
										System.out.println(preparedStatement5);
									    preparedStatement5.executeUpdate();

									}

								    return "idol accepted sucessfully";
								}
 
						 }
					 }
				}
			  

				
			} catch (SQLException exception) {
				JDBCUtils.printSQLException(exception);
			}		return "idol is non disposable, request rejected";
	}

}