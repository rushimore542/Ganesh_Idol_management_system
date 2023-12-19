package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.utils.JDBCUtils;

public class CustomerDaoImpl implements CustomerDao {

	private static final String CUSTOMER_DETAILS_BY_ID = "SELECT cid,rid,uid,name,sirname,email,address,mobile FROM customertable where cid = ?";
	
	@Override
	public Long getCidFromVendor(String email){

        try {
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement("select cid from customertable  where email = ?");
            preparedStatement.setString(1, email);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
            	return rs.getLong("cid");
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0L;
    }
	
	@Override
	public CustomerBean getCustomerDetails(Long id) {
		CustomerBean customer = null;

		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CUSTOMER_DETAILS_BY_ID)) {
			preparedStatement.setLong(1,id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int rid = rs.getInt("rid");
				Long uid = rs.getLong("uid");
				Long cid = rs.getLong("cid");
				String name = rs.getString("name");
				String sirname = rs.getString("sirname");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String address = rs.getString("address");
				customer  =  new CustomerBean(cid,rid,uid,name,sirname,email,mobile,address);	
				}
			 connection.close();
			}catch (SQLException exception) {
				JDBCUtils.printSQLException(exception);
			}
		
		return customer;
	}
	@Override
	public int registerCustomer(CustomerBean Customer) {
		// TODO Auto-generated method stub
		//Long uid = "SELECT uid from usertable"
		 String INSERT_USERS_SQL = "INSERT INTO customertable" +
		            "  (rid,uid,name,sirname,email,mobile,address ) VALUES " +
		            " ( ?, ?, ?,?,?,?,?);";
                Long x = null;
		        int result = 0;


		        try {
		        		Connection connection= JDBCUtils.getConnection();
		        		PreparedStatement preparedStatement =connection.
		    			prepareStatement("select uid from usertable where username=?");
		    			preparedStatement.setString(1,Customer.getEmail());
		    			ResultSet rs=preparedStatement.executeQuery();
		    			while(rs.next())
		    			{
		    				x=rs.getLong("uid");
		    			}
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_USERS_SQL);
		            		 
		            preparedStatement1.setInt(1, Customer.getRid());
		            preparedStatement1.setLong(2,x);
		            preparedStatement1.setString(3, Customer.getName());
		            preparedStatement1.setString(4, Customer.getSirname());
		            preparedStatement1.setString(5, Customer.getEmail());
		            preparedStatement1.setString(6, Customer.getMobile());
		            preparedStatement1.setString(7, Customer.getAddress());
		           
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

}
