package ganeshmurthi.model;

public class IdolBean {
	private Long idolid;
	private Long vid;
	private Long contentid;
	private int priceofidol ;
	private String colorofidol;
	private Long sizeofidol;
	private Long imageid;
	private String isdisposable;

	
	public String getIsdisposable() {
		return isdisposable;
	}
	public void setIsdisposable(String isdisposable) {
		this.isdisposable = isdisposable;
	}
	public Long getImageid() {
		return imageid;
	}
	public void setImageid(Long imageid) {
		this.imageid = imageid;
	}
	public Long getIdolid() {
		return idolid;
	}
	public void setIdolid(Long idolid) {
		this.idolid = idolid;
	}
	public IdolBean(Long idolid, Long vid, Long contentid, int priceofidol, String colorofidol, Long sizeofidol,
			Long imageid,String isdisposable) {
		super();
		this.idolid = idolid;
		this.vid = vid;
		this.contentid = contentid;
		this.priceofidol = priceofidol;
		this.colorofidol = colorofidol;
		this.sizeofidol = sizeofidol;
		this.imageid = imageid;
		this.isdisposable = isdisposable;
	}
	
	public IdolBean(int price, String colorofidol,Long size) {
		super();
		
		this.vid = vid;
		this.contentid = contentid;
		this.priceofidol = price;
		this.colorofidol = colorofidol;
		this.sizeofidol = size;
		this.imageid = imageid;
		this.isdisposable = isdisposable;
	}
	
	public Long getVid() {
		return vid;
	}
	public void setVid(Long vid) {
		this.vid = vid;
	}
	public Long getContentid() {
		return contentid;
	}
	public void setContentid(Long contentid) {
		this.contentid = contentid;
	}
	public int getPriceofidol() {
		return priceofidol;
	}
	public void setPriceofidol(int priceofidol) {
		this.priceofidol = priceofidol;
	}
	public String getColorofidol() {
		return colorofidol;
	}
	public void setColorofidol(String colorofidol) {
		this.colorofidol = colorofidol;
	}
	public Long getSizeofidol() {
		return sizeofidol;
	}
	public void setSizeofidol(Long sizeofidol) {
		this.sizeofidol = sizeofidol;
	}
}
