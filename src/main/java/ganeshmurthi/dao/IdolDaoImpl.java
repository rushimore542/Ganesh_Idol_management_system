package ganeshmurthi.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ganeshmurthi.model.ContentBean;
import ganeshmurthi.model.IdolBean;
import ganeshmurthi.model.VendorBean;
import ganeshmurthi.utils.JDBCUtils;

public class IdolDaoImpl implements IdolDao {
	private static final String SELECT_IDOL_BY_ID = "SELECT idolid,vid,contentid,priceofidol,sizeofidol,colorofidol,imageid,isdisposable  FROM idoltable where idolid = ? ";
	
	@Override
	public Long getVidFromImageId(Long imageid) {
		
		  try {
	        	Connection connection= JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.
	            prepareStatement("select vid from idoltable  where imageid = ?");
	            preparedStatement.setLong(1, imageid);      
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            if(rs.next())
	            {
	            	return rs.getLong("vid");
	            }
	            connection.close();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
		
		return 0L;	
	}
	
	@Override
	public Long getIdolIdfromIdolTable(String Size, String Colour) {
		
		  try {
	        	Connection connection= JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.
	            prepareStatement("select idolid from idoltable  where sizeofidol = ? and colorofidol = ?");
	            preparedStatement.setString(1, Size);     
	            preparedStatement.setString(2, Colour); 
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            if(rs.next())
	            {
	            	return rs.getLong("idolid");
	            }
	            connection.close();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
		
		return 0L;	
	}
	
	@Override
	public Long getImageIdfromIdolTable(String Size, String Colour) {
		
		  try {
	        	Connection connection= JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.
	            prepareStatement("select imageid from idoltable  where sizeofidol = ? and colorofidol = ?");
	            preparedStatement.setString(1, Size);     
	            preparedStatement.setString(2, Colour); 
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            if(rs.next())
	            {
	            	return rs.getLong("imageid");
	            }
	            connection.close();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
		
		return 0L;	
	}
	
	@Override
	public IdolBean getIdolInfo(Long idolid) {
		IdolBean idol = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_IDOL_BY_ID)) {
			preparedStatement.setLong(1,idolid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long idolId = rs.getLong("idolid");
				Long vid = rs.getLong("vid");
				Long contentId = rs.getLong("contentid");
				Long imageId = rs.getLong("imageid");
				Long sizeofidol = rs.getLong("sizeofidol");
				String colorofidol = rs.getString("colorofidol");
				int priceofidol = rs.getInt("priceofidol");
				String isdisposable = rs.getString("isdisposable");

				idol = new IdolBean(idolId,vid,contentId,priceofidol,colorofidol,sizeofidol,imageId,isdisposable);
				
			}
			
			 connection.close();
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		
		return idol;
	}
	@Override
	public int regIdol(IdolBean idolBean,Long vid,Long contentid,Long imageid) {
		// TODO Auto-generated method stub
		
		 String INSERT_IDOL_SQL = "INSERT INTO idoltable" +
		            "  (vid,contentid,priceofidol,sizeofidol,colorofidol,imageid,isdisposable ) VALUES " +
		            " ( ?, ?, ?,?,?,?,?);";
		 
		 String isdisposable = null;
		 int result = 0;
		 
		 try {
			 Connection connection= JDBCUtils.getConnection();
			 PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_IDOL_SQL);
			 preparedStatement1.setLong(1,vid);
			 preparedStatement1.setLong(2,contentid);
			 preparedStatement1.setLong(3,idolBean.getPriceofidol());
			 preparedStatement1.setLong(4,idolBean.getSizeofidol());
			 preparedStatement1.setString(5,idolBean.getColorofidol());
			 preparedStatement1.setLong(6,imageid);
			 preparedStatement1.setString(7,"yes");
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
