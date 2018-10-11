package printqr;

public class Information {
	private String uuid;
	private String batch;
	private String location;
	private String date;
	private String username;
	
	public String getuuid() {
		return uuid;
	}
	
	public void setuuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getbatch() {
		return batch;
	}
	
	public void setbatch(String batch) {
		this.batch = batch;
	}
	
	public String getlocation() {
		return location;
	}
	
	public void setlocation(String location) {
		this.location = location;
	}
	
	public String getdate() {
		return date;
	}
	
	public void setdate(String date) {
		this.date = date;
	}
	
	public String username() {
		return username;
	}
	
	public void setusername(String username) {
		this.username = username;
	}

}
