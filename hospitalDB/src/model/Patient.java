package model;
public class Patient {
	
	Integer patid;
	String name;
	String fname;
	String address;
	String phone;
	Integer currentDues;

	public Patient() { }

	public Patient(Integer patid, String name, String address, String phone, Integer currentDues) {
		this.patid = patid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.currentDues = currentDues;
	}
	
	public Integer getPatid() {
		return patid;
	}

	public void setPatid(Integer patid) {
		this.patid = patid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFname() {
		setFname();
		return fname;
	}

	public void setFname() {
		String[] arr=this.name.split(" ");
        String fname=arr[0];
		this.fname = fname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getCurrentDues() {
		return currentDues;
	}

	public void setCurrentDues(Integer currentDues) {
		this.currentDues = currentDues;
	}
	
	
}