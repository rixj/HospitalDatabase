package model;

public class Doctor {
    Integer docid;
    String dept;
    String name;
    String lname;
    String address;
    String phone;
    public Doctor() {}
    public Doctor(Integer docid, String dept, String name, String address, String phone) {
        this.docid = docid;
        this.dept = dept;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public Integer getDocid() {
        return docid;
    }
    public void setDocid(Integer docid) {
        this.docid = docid;
    }
    public String getDept() {
        return dept;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getLname() {
        this.setLname();
        return lname;
    }
    public void setLname() {
        String[] arr = this.name.split(" ");
        String last = arr[1];
        this.lname = last;
    }
}
