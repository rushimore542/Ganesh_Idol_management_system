package ganeshmurthi.model;

public class UserBean {
	private int roleid;
	private String username;
	private String password;
	private Long uid;
	
	public UserBean() {}
	
    public UserBean(Long uid, int roleid, String username, String password) {
    	super();
    	this.uid=uid;
        this.roleid = roleid;
        this.username = username;
        this.password = password;
    }
    
    public UserBean(int roleid, String username, String password) {
    	super();
        this.roleid = roleid;
        this.username = username;
        this.password = password;
    }
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
