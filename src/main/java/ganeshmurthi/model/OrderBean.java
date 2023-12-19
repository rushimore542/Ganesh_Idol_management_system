package ganeshmurthi.model;


public class OrderBean {

	private Long oid;
	private Long cid;
	private Long vid;
	private Long idolid;
	private Long imageid;
	private Long qrid;
	private Long managementid;
	private String managedwell;
	private String orderstatus;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	
	public OrderBean() {
		
	}
	
	public OrderBean(Long oid, Long cid, Long vid, Long idolid, Long imageid, Long qrid, Long managementid,
			String managedwell, String orderstatus) {
		super();
		this.oid = oid;
		this.cid = cid;
		this.vid = vid;
		this.idolid = idolid;
		this.imageid = imageid;
		this.qrid = qrid;
		this.managementid = managementid;
		this.managedwell = managedwell;
		this.orderstatus = orderstatus;
	}
	
	public OrderBean(Long cid, Long vid, Long idolid, Long imageid, Long qrid, Long managementid,
			String managedwell, String orderstatus) {
		super();
		this.cid = cid;
		this.vid = vid;
		this.idolid = idolid;
		this.imageid = imageid;
		this.qrid = qrid;
		this.managementid = managementid;
		this.managedwell = managedwell;
		this.orderstatus = orderstatus;
	}
	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public Long getIdolid() {
		return idolid;
	}
	public void setIdolid(Long idolid) {
		this.idolid = idolid;
	}
	public Long getImageid() {
		return imageid;
	}
	public void setImageid(Long imageid) {
		this.imageid = imageid;
	}
	public Long getQrid() {
		return qrid;
	}
	public void setQrid(Long qrid) {
		this.qrid = qrid;
	}
	public Long getManagementid() {
		return managementid;
	}
	public void setManagementid(Long managementid) {
		this.managementid = managementid;
	}
	public String getManagedwell() {
		return managedwell;
	}
	public void setManagedwell(String managedwell) {
		this.managedwell = managedwell;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
}
