package model;

public class Treatdone {
	Integer tid;
	Integer appid;
	String illname;
	String trename;
	public Treatdone() {}
	public Treatdone(Integer tid, Integer appid, String illname, String trename) {
		this.tid = tid;
		this.appid = appid;
		this.illname = illname;
		this.trename = trename;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public String getIllname() {
		return illname;
	}
	public void setIllname(String illname) {
		this.illname = illname;
	}
	public String getTrename() {
		return trename;
	}
	public void setTrename(String trename) {
		this.trename = trename;
	}

}
