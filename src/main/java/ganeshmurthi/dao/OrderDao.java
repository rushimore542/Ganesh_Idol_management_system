package ganeshmurthi.dao;

import java.sql.SQLException;
import java.util.List;

import ganeshmurthi.model.OrderBean;

public interface OrderDao {
OrderBean getOrderDetails(Long orderId) throws SQLException;
List<OrderBean> getAllOdersOfVendor(Long vid);
int regOrder(OrderBean orderBean);
}
