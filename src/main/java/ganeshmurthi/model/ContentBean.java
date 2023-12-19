package ganeshmurthi.model;

import java.util.List;

public class ContentBean {
private Long contentid;
public ContentBean(Long contentid, String contents2) {
	super();
	this.contentid = contentid;
	this.contents = contents2;
}
public ContentBean(String list) {
	// TODO Auto-generated constructor stub
	this.contents = list;
}
String contents;
public Long getContentid() {
	return contentid;
}
public void setContentid(Long contentid) {
	this.contentid = contentid;
}
public String getContents() {
	return contents;
}
public void setContents(String contents) {
	this.contents = contents;
}

}
