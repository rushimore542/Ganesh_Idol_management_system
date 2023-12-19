package ganeshmurthi.model;

public class Imagebean {
	private Long imageid;
	private String description;
	private String imageString;
	
	public Imagebean() {
		
	}
	
	public Imagebean(String type, String path) {
		// TODO Auto-generated constructor stub
		this.description = type;
		this.imageString = path;
	}
	
	public Imagebean(Long imageid, String type, String path) {
		// TODO Auto-generated constructor stub
		this.imageid = imageid;
		this.description = type;
		this.imageString = path;
	}
	
	public Long getImageid() {
		return imageid;
	}
	public void setImageid(Long imageid) {
		this.imageid = imageid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}

}
