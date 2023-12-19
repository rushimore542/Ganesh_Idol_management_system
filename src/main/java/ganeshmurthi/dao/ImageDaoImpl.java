package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import ganeshmurthi.model.Imagebean;
import ganeshmurthi.utils.JDBCUtils;

public class ImageDaoImpl implements ImageDao {
	
	@Override
	public Long getImageIdFromImageTable(String image) {
		// TODO Auto-generated method stub

        try {
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement("select imgid from imagetable  where imagedata = ?");
            preparedStatement.setString(1, image);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next())
            {
            	return rs.getLong("imgid");
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return 0L;
	}


	@Override
	public int regImage(Imagebean Image) {
		// TODO Auto-generated method stub
		String INSERT_IMAGE_SQL = "INSERT INTO imagetable" +
	            "  (description,imagedata ) VALUES " +
	            " ( ?, ?);";
		
		int result = 0;
		try {
			Connection connection= JDBCUtils.getConnection();
			 PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_IMAGE_SQL);
			 preparedStatement1.setString(1,Image.getDescription());
			 preparedStatement1.setString(2,Image.getImageString());
			 result  = preparedStatement1.executeUpdate();
			 connection.close();
		}catch (SQLException e) {
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
	public ArrayList<String> getImageFromType(String type) {
		// TODO Auto-generated method stub
		ArrayList<String> lst = new ArrayList<String>();
        try {
        	
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement("select imagedata from imagetable  where description = ?");
            preparedStatement.setString(1, type);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            java.sql.ResultSetMetaData meta = rs.getMetaData();
            int column_Count = meta.getColumnCount();
            while(rs.next())
            {
            	
            		lst.add(rs.getString("imagedata"));
            
            	//return rs.getString("imagedata");
            }
           // return lst;
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
		return lst;
  
	}

}
