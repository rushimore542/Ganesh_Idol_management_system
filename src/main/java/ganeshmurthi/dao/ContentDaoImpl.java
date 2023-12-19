package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ganeshmurthi.model.ContentBean;
import ganeshmurthi.model.IdolBean;
import ganeshmurthi.utils.JDBCUtils;

public class ContentDaoImpl implements ContentDao{
	private static final String SELECT_CONTENTS_BY_ID = "SELECT contentid,contents FROM contenttable where contentid = ? ";
	@Override
	public Long getContentIdFromContent(String content)  {

        try {
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement("select contentid from contenttable  where contents = ?");
            preparedStatement.setString(1, content);         		 
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next())
            {
            	return rs.getLong("contentid");
            }
            connection.close();

        } catch (SQLException e) {
            printSQLException(e);
        } 
       
        return 0L;
    }
	
	@Override
	public ContentBean getContent(Long contentid) {
		
		ContentBean content = null;
		try {
			Connection connection = JDBCUtils.getConnection();
		
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTENTS_BY_ID); 
			preparedStatement.setLong(1,contentid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long contentId = rs.getLong("contentid");		
				String contents = rs.getString("contents");
				content = new ContentBean(contentId,contents);
				
			}
			connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		
		return content;
	}

	@Override
	public int regContent(ContentBean Content) {
		// TODO Auto-generated method stub
		String INSERT_CONTENT_SQL = "INSERT INTO contenttable" +
	            "  (contents ) VALUES " +
	            " ( ?);";
		
		int result = 0;
		try {
			Connection connection= JDBCUtils.getConnection();
			 PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_CONTENT_SQL);
			 preparedStatement1.setString(1, Content.getContents());
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

}
