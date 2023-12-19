package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.model.UserBean;
import ganeshmurthi.utils.JDBCUtils;

public class UserDao {
	public String validate(String email,String password) throws ClassNotFoundException {
        String userNameDB = "";
        int roleDB = 0;

        try {Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select * from usertable  where username = ? and password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                userNameDB = rs.getString("username");
                roleDB = rs.getInt("roleid");
     
                if(roleDB==1)
                	return "CUSTOMER_ROLE";

                else if( roleDB==2)
                	return "VENDOR_ROLE";
                else
                	return "ORG_ROLE";
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return "Invalid login credentials";
    }
	public int registerUser(UserBean user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO usertable" +
            "  (roleid,username,password ) VALUES " +
            " ( ?, ?, ?);";

        int result = 0;


        try {
        		Connection connection= JDBCUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            		 
            preparedStatement.setInt(1, user.getRoleid());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
           
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
	
	public boolean userAlreadyPresent(String email) throws ClassNotFoundException {

        try {
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            		prepareStatement("select * from usertable  where username = ?");
            preparedStatement.setString(1, email);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
            	return true;
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
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
	