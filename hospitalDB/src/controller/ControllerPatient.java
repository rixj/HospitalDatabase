package controller;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import controller.DBType;
import controller.DBUtil;
import model.Patient;

public class ControllerPatient {

	public List<Patient> findAll() {
		try {
			List<Patient> listPatients = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from PATIENT");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Patient p = new Patient();
				p.setPatid(rs.getInt("PAT_ID"));
				p.setName(rs.getString("patName"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				p.setCurrentDues(rs.getInt("currentDues"));
				listPatients.add(p);
			}
			return listPatients;
		} catch (Exception e) {
			return null;
		}
	}

	public Patient find(int id) {
		try {
			Patient p = new Patient();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from PATIENT where PAT_ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				p.setPatid(rs.getInt("PAT_ID"));
				p.setName(rs.getString("patName"));
				p.setAddress(rs.getString("address"));
				p.setPhone(rs.getString("phone"));
				p.setCurrentDues(rs.getInt("currentDues"));
			}
			return p;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean edit(Patient p) {
		try {
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"update PATIENT set patName=?, address=?, phone=? where PAT_ID=?");
			ps.setString(1, p.getName());
			ps.setString(2, p.getAddress());
			ps.setString(3, p.getPhone());
			ps.setInt(4, p.getPatid());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean delete(Patient p) {
		try {
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"delete from PATIENT where PAT_ID=?");
			ps.setInt(1, p.getPatid());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean create(Patient p){
		// STORED PROCEDURE
		Connection conn = null;
		CallableStatement callableStatement = null;
		try {
			conn = DBUtil.getConnection(DBType.HDB);
			callableStatement = conn.prepareCall("{call AddPatient(?,?,?,?,?)}");
			callableStatement.setInt(1, p.getPatid());
			callableStatement.setString(2, p.getName());
			callableStatement.setString(3, p.getAddress());
			callableStatement.setString(4, p.getPhone());
			callableStatement.setInt(5, p.getCurrentDues());
			callableStatement.execute();
			callableStatement.close();
			conn.close();
			return true;
		}
		catch (SQLException ex) {
			DBUtil.showErrorMessage(ex);
			return false;
		}  
	}
}

