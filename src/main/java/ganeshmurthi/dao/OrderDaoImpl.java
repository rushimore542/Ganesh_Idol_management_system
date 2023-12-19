package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ganeshmurthi.model.OrderBean;
import ganeshmurthi.utils.JDBCUtils;


public class OrderDaoImpl implements OrderDao {

	private static final String SELECT_ORDER_BY_ID = "SELECT oid,vid,cid,managementid,imageid,qrid,orderstatus,idolid,managedwell  FROM ordertable where oid = ? ";
	private static final String SELECT_ALL_ORDERS_OF_VENDOR_BY_ID= "select * from ordertable where vid = ?";
	@Override
	public OrderBean getOrderDetails(Long orderId)  {
		OrderBean order = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
			preparedStatement.setLong(1,orderId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("oid");
				Long vid = rs.getLong("vid");
				Long cid = rs.getLong("cid");
				Long managementid = rs.getLong("managementid");
				Long imageid = rs.getLong("imageid");
				Long qrid = rs.getLong("qrid");
				Long idolid = rs.getLong("idolid");
				String orderstatus = rs.getString("orderstatus");
				String managedwell = rs.getString("managedwell");

				order= new OrderBean(id,cid,vid,idolid,imageid,qrid,managementid,managedwell,orderstatus);
				
			}
			 connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return order;
	}
	
	@Override
	public int regOrder(OrderBean orderBean) {
		// TODO Auto-generated method stub
		 String INSERT_ORDER_SQL = "INSERT INTO ordertable" +
		            "  (cid,vid,orderstatus,idolid,managedwell,imageid,qrid,managementid ) VALUES " +
		            " ( ?, ?, ?,?,?,?,?,?);";
             Long x = null;
		        int result = 0;


		        try {
		        		Connection connection= JDBCUtils.getConnection();
		        
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_ORDER_SQL);
		            		 
		            preparedStatement1.setLong(1, orderBean.getCid());
		            preparedStatement1.setLong(2, orderBean.getVid());
		            preparedStatement1.setString(3, orderBean.getOrderstatus());
		            preparedStatement1.setLong(4, orderBean.getIdolid());
		            preparedStatement1.setString(5, orderBean.getManagedwell());
		            preparedStatement1.setLong(6, orderBean.getImageid());
		            preparedStatement1.setLong(7, orderBean.getQrid());
		            preparedStatement1.setLong(8, orderBean.getManagementid());
		           
		            System.out.println(preparedStatement1);
		            // Step 3: Execute the query or update query
		            result = preparedStatement1.executeUpdate();
		            connection.close();
		        } catch (SQLException e) {
		            // process sql exception
		            printSQLException(e);
		        }
		        return result;
	}

	private void printSQLException(SQLException ex) {
		// TODO Auto-generated method stub
		 for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	}

	
	@Override
	public List<OrderBean> getAllOdersOfVendor(Long vid) {
		// TODO Auto-generated method stub
		List<OrderBean> orders = new ArrayList<>();
		OrderBean order = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS_OF_VENDOR_BY_ID);) {
			preparedStatement.setLong(1,vid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				Long id = rs.getLong("oid");
				Long vid1 = rs.getLong("vid");
				Long cid = rs.getLong("cid");
				Long managementid = rs.getLong("managementid");
				Long imageid = rs.getLong("imageid");
				Long qrid = rs.getLong("qrid");
				Long idolid = rs.getLong("idolid");
				String orderstatus = rs.getString("orderstatus");
				String managedwell = rs.getString("managedwell");

				 order = new OrderBean(id,cid,vid1,idolid,imageid,qrid,managementid,managedwell,orderstatus);
				
				orders.add(order);
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}

		return orders;
	}


}
