package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;

public class ControllerDoctor {

    public List<Doctor> findAll() {
        try {
            List<Doctor> listDoctors = new ArrayList<>();
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from DOCTOR");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDocid(rs.getInt("DOC_ID"));
                d.setDept(rs.getString("DEPT_NAME"));
                d.setName(rs.getString("docName"));
                d.setAddress(rs.getString("address"));
                d.setPhone(rs.getString("phone"));
                listDoctors.add(d);
            }
            return listDoctors;
        } catch (Exception e) {
            return null;
        }
    }

    public Doctor find(int id) {
        try {
            Doctor d = new Doctor();
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from DOCTOR where DOC_ID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                d.setDocid(rs.getInt("DOC_ID"));
                d.setDept(rs.getString("DEPT_NAME"));
                d.setName(rs.getString("docName"));
                d.setAddress(rs.getString("address"));
                d.setPhone(rs.getString("phone"));
            }
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean edit(Doctor d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                    "update DOCTOR set docName=?,address=?,phone=?,DEPT_NAME=? where DOC_ID=?");
            ps.setString(1, d.getName());
            ps.setString(2, d.getAddress());
            ps.setString(3, d.getPhone());
            ps.setString(4, d.getDept());
            ps.setInt(5, d.getDocid());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(Doctor d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                    "delete from DOCTOR where DOC_ID=?");
            ps.setInt(1, d.getDocid());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }

    }

    
    public boolean create(Doctor d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                "insert into DOCTOR values(?,?,?,?,?)");
            ps.setInt(1, d.getDocid());
            ps.setString(2, d.getDept());
            ps.setString(3, d.getName());
            ps.setString(4, d.getAddress());
            ps.setString(5, d.getPhone());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

	public List<Doctor> viewDocFromPatient() {
        try {
            List<Doctor> listDoctors = new ArrayList<>();
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from doctor_info");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDept(rs.getString("DEPT_NAME"));
                d.setName(rs.getString("docName"));
                d.setPhone(rs.getString("phone"));
                listDoctors.add(d);
            }
            return listDoctors;
        } catch (Exception e) {
            return null;
        }
    }
}

