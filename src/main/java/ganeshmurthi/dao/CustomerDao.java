package ganeshmurthi.dao;
import ganeshmurthi.model.CustomerBean;

public interface CustomerDao {
CustomerBean getCustomerDetails(Long id);
int registerCustomer(CustomerBean Customer);
Long getCidFromVendor(String email);
}
