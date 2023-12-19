package ganeshmurthi.model;

public class OrganizationBean {
	private Long orgid;
	private int rid;
	private Long uid;
	private String name ;
	private String email;
	private String mobile;
	private String address;
	
	
	public OrganizationBean(int rid, String name, String email, String mobile, String address) {
		super();
		this.rid = rid;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public OrganizationBean() {
		
	}
	public OrganizationBean(Long orgid, int rid, Long uid, String name, String email, String mobile, String address) {
		super();
		this.orgid = orgid;
		this.rid = rid;
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
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
