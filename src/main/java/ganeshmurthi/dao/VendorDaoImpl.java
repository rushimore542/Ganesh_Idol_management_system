package ganeshmurthi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.model.VendorBean;
import ganeshmurthi.utils.JDBCUtils;

public class VendorDaoImpl implements VendorDao{

	private static final String VENDOR_DETAILS_BY_ID = "SELECT vid,rid,uid,name,sirname,email,address,mobile FROM vendortable where vid = ?";
	private static final String GET_ALL_VENDORS = "SELECT * FROM vendortable";
	
	@Override
	public Long getVidFromVendor(String email){

        try {
        	Connection connection= JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.
            prepareStatement("select vid from vendortable  where email = ?");
            preparedStatement.setString(1, email);         		 
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
	public VendorBean getVendorDetails(Long vendorId) {
		VendorBean vendor = null;

		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(VENDOR_DETAILS_BY_ID)) {
			preparedStatement.setLong(1,vendorId);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int rid = rs.getInt("rid");
				Long uid = rs.getLong("uid");
				Long vid = rs.getLong("vid");
				String name = rs.getString("name");
				String sirname = rs.getString("sirname");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String address = rs.getString("address");
				vendor  =  new VendorBean(vid,rid,uid,name,sirname,email,mobile,address);	
				}
			 connection.close();
			}catch (SQLException exception) {
				JDBCUtils.printSQLException(exception);
			}
		return vendor;
	}
	
	@Override
	public List<VendorBean> getAllVendors() {
		// TODO Auto-generated method stub
		
		List<VendorBean> vendors = new ArrayList<>();

		// Step 1: Establishing a Connection
		try (Connection connection = JDBCUtils.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_VENDORS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int rid = rs.getInt("rid");
				Long uid = rs.getLong("uid");
				Long vid = rs.getLong("vid");
				String name = rs.getString("name");
				String sirname = rs.getString("sirname");
				String email = rs.getString("email");
				String mobile = rs.getString("mobile");
				String address = rs.getString("address");
			
				vendors.add(new VendorBean(vid,rid,uid,name,sirname,email,mobile,address));
			}
		} catch (SQLException exception) {
			JDBCUtils.printSQLException(exception);
		}
		return vendors;
	}

	@Override
	public int registerVendor(VendorBean Vendor) {
		 String INSERT_USERS_SQL = "INSERT INTO vendortable" +
		            "  (rid,uid,name,sirname,email,mobile,address ) VALUES " +
		            " ( ?, ?, ?,?,?,?,?);";
                Long x = null;
		        int result = 0;


		        try {
		        		Connection connection= JDBCUtils.getConnection();
		        		PreparedStatement preparedStatement =connection.
		    			prepareStatement("select uid from usertable where username=?");
		    			preparedStatement.setString(1,Vendor.getEmail());
		    			ResultSet rs=preparedStatement.executeQuery();
		    			while(rs.next())
		    			{
		    				x=rs.getLong("uid");
		    			}
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_USERS_SQL);
		            		 
		            preparedStatement1.setLong(1, Vendor.getRid());
		            preparedStatement1.setLong(2,x);
		            preparedStatement1.setString(3, Vendor.getName());
		            preparedStatement1.setString(4, Vendor.getSirname());
		            preparedStatement1.setString(5, Vendor.getEmail());
		            preparedStatement1.setString(6, Vendor.getMobile());
		            preparedStatement1.setString(7, Vendor.getAddress());
		           
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
