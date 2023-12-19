package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.model.OrganizationBean;
import ganeshmurthi.utils.JDBCUtils;

public class OrganizationDao {
	public int registerOrg(OrganizationBean organizationBean) {
		 String INSERT_USERS_SQL = "INSERT INTO organizationtable" +
		            "  (rid,uid,name,email,mobile,address ) VALUES " +
		            " ( ?, ?, ?,?,?,?);";
            Long x = null;
		        int result = 0;


		        try {
		        		Connection connection= JDBCUtils.getConnection();
		        		PreparedStatement preparedStatement =connection.
		    			prepareStatement("select uid from usertable where username=?");
		    			preparedStatement.setString(1,organizationBean.getEmail());
		    			ResultSet rs=preparedStatement.executeQuery();
		    			while(rs.next())
		    			{
		    				x=rs.getLong("uid");
		    			}
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_USERS_SQL);
		            		 
		            preparedStatement1.setInt(1, organizationBean.getRid());
		            preparedStatement1.setLong(2,x);
		            preparedStatement1.setString(3, organizationBean.getName());
		            preparedStatement1.setString(4, organizationBean.getEmail());
		            preparedStatement1.setString(5, organizationBean.getMobile());
		            preparedStatement1.setString(6, organizationBean.getAddress());
		           
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
