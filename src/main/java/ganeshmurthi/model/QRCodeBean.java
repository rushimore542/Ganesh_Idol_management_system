package ganeshmurthi.model;

public class QRCodeBean {
private Long qrid;
private Long oid;
private String qrcodestring;
public Long getQrid() {
	return qrid;
}


public QRCodeBean(Long qrid, Long oid, String qrcodestring) {
	super();
	this.qrid = qrid;
	this.oid = oid;
	this.qrcodestring = qrcodestring;
}


public void setQrid(Long qrid) {
	this.qrid = qrid;
}
public Long getOid() {
	return oid;
}
public void setOid(Long oid) {
	this.oid = oid;
}
public String getQrcodestring() {
	return qrcodestring;
}
public void setQrcodestring(String qrcodestring) {
	this.qrcodestring = qrcodestring;
}



}
