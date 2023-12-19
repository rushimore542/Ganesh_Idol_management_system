package ganeshmurthi.model;

public class IdolManagementBean {
private Long managementid;
private Long cid;
private Long oid;
private Long qrid;
private String qridused;
private Long orgid;

public IdolManagementBean(Long managementid, Long cid, Long oid, Long qrid, String qridused, Long orgid) {
	super();
	this.managementid = managementid;
	this.cid = cid;
	this.oid = oid;
	this.qrid = qrid;
	this.qridused = qridused;
	this.orgid = orgid;
}

public Long getManagementid() {
	return managementid;
}

public void setManagementid(Long managementid) {
	this.managementid = managementid;
}

public Long getCid() {
	return cid;
}

public void setCid(Long cid) {
	this.cid = cid;
}

public Long getOid() {
	return oid;
}

public void setOid(Long oid) {
	this.oid = oid;
}

public Long getQrid() {
	return qrid;
}

public void setQrid(Long qrid) {
	this.qrid = qrid;
}

public String getQridused() {
	return qridused;
}

public void setQridused(String qridused) {
	this.qridused = qridused;
}

public Long getOrgid() {
	return orgid;
}

public void setOrgid(Long orgid) {
	this.orgid = orgid;
}





}
