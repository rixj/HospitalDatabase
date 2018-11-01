package model;

public class Appointment {
    Integer appid;
    Integer docid;
    Integer patid;
    String datetime;
    String docname;
    String patname;
    String illname;
	public Appointment() { }
    public Appointment(Integer appid, Integer docid, Integer patid, String datetime) {
        this.appid = appid;
        this.docid = docid;
        this.patid = patid;
        this.datetime = datetime;
    }
    public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public Integer getDocid() {
        return docid;
    }

    public void setDocid(Integer docid) {
        this.docid = docid;
    }

    public Integer getPatid() {
        return patid;
    }

    public void setPatid(Integer patid) {
        this.patid = patid;
    }
    
    public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getPatname() {
		return patname;
	}

	public void setPatname(String patname) {
		this.patname = patname;
	}

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
	public String getIllname() {
		return illname;
	}
	public void setIllname(String illname) {
		this.illname = illname;
	}

}
