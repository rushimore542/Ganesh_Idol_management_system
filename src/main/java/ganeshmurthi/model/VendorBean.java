package ganeshmurthi.model;

public class VendorBean {
	private Long vid;
	private int rid;
	private Long uid;
	private String name ;
	private String sirname;
	private String email;
	private String mobile;
	private String address;

	public VendorBean() {}
	
	public VendorBean(int rid,String name, String sirname, String email, String mobile,
			String address) {
		super();
		
		this.rid = rid;
		
		this.name = name;
		this.sirname = sirname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	public VendorBean(Long vid, int rid, Long uid, String name, String sirname, String email, String mobile,String address) {
		super();
		this.vid = vid;
		this.rid = rid;
		this.uid = uid;
		this.name = name;
		this.sirname = sirname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	public Long getVid() {
		return vid;
	}
	
	
	
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSirname() {
		return sirname;
	}
	public void setSirname(String sirname) {
		this.sirname = sirname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
