package ganeshmurthi.dao;

import java.util.List;

import ganeshmurthi.model.CustomerBean;
import ganeshmurthi.model.VendorBean;

public interface VendorDao {
VendorBean getVendorDetails(Long vid);
int registerVendor(VendorBean Vendor);
Long getVidFromVendor(String email);
List<VendorBean> getAllVendors(); 
}
