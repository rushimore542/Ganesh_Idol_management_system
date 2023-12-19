package ganeshmurthi.model;

public class CustomerBean {
	private Long cid;
	private int rid;
	private Long uid;
	private String name ;
	private String sirname;
	private String email;
	private String mobile;
	private String address;

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public CustomerBean(int rid, String name, String sirname, String email, String mobile, String address) {
		super();
		this.rid = rid;
		this.name = name;
		this.sirname = sirname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public CustomerBean() {
		
	}
	public CustomerBean(Long cid, int rid, Long uid, String name, String sirname, String email, String mobile,String address) {
		super();
		this.cid = cid;
		this.rid = rid;
		this.uid = uid;
		this.name = name;
		this.sirname = sirname;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
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
	

}
