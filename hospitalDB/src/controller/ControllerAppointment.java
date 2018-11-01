package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;

public class ControllerAppointment {

	public List<Appointment> findAllAppointment() {
		try {
			List<Appointment> listAppointments = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"SELECT d.docName, p.patName, a.app_id, a.doc_id, a.pat_id, a.appDateTime FROM APPOINTMENT a, DOCTOR d, PATIENT p WHERE a.doc_id = d.doc_id and a.pat_id = p.pat_id order by a.appDateTime");
			//                    "SELECT d.docName, p.patName, a.app_id, a.doc_id, a.pat_id, a.appDateTime FROM APPOINTMENT a, DOCTOR d, PATIENT p WHERE a.doc_id = d.doc_id and a.pat_id = p.pat_id order by a.appDateTime Desc");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Appointment a = new Appointment();
				a.setAppid(rs.getInt("APP_ID"));
				a.setPatid(rs.getInt("PAT_ID"));
				a.setDocid(rs.getInt("DOC_ID"));
				a.setDatetime(rs.getString("appDatetime"));
				a.setDocname(rs.getString("docName"));
				a.setPatname(rs.getString("patName"));
				listAppointments.add(a);
			}
			return listAppointments;
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> findAllDoctors() {
		try {
			List<String> doctorChoices = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"Select distinct concat(docName, ' - ', doc_id) docNameId from DOCTOR order by docNameId");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				doctorChoices.add(rs.getString("docNameId"));
			}
			return doctorChoices;
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> findAllPatients() {
		try {
			List<String> patientChoices = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"Select distinct concat(patName, ' - ', pat_id) patNameId from PATIENT order by patNameId");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				patientChoices.add(rs.getString("patNameId"));
			}
			return patientChoices;
		} catch (Exception e) {
			return null;
		}
	}

	public Appointment find(int id) {
		try {
			Appointment a = new Appointment();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from APPOINTMENT where APP_ID=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				a.setAppid(rs.getInt("APP_ID"));
				a.setPatid(rs.getInt("PAT_ID"));
				a.setDocid(rs.getInt("DOC_ID"));
				a.setDatetime(rs.getString("appDatetime"));
			}
			return a;
		} catch (Exception e) {
			return null;
		}
	}


	public boolean edit(Appointment a) {
		try {
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"update APPOINTMENT set DOC_ID=?, PAT_ID=?, appDatetime=? where APP_ID=?");
			ps.setInt(1, a.getDocid());
			ps.setInt(2, a.getPatid());
			ps.setString(3, a.getDatetime());
			ps.setInt(4, a.getAppid());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean delete(Appointment a) {
		try {
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"delete from APPOINTMENT where APP_ID=?");
			ps.setInt(1, a.getAppid());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean create(Appointment a) {
		try {
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"insert into APPOINTMENT values(?,?,?,?)");       
			ps.setInt(1, a.getAppid());
			ps.setInt(2, a.getDocid());
			ps.setInt(3, a.getPatid());

			//          DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			//          Date myDate = formatter.parse(a.getDatetime());
			//          java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

			ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Appointment> findAllforPatient(int patid) {
		try {
			List<Appointment> listAppointments = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					"SELECT APP_ID,docName,appDatetime FROM APPOINTMENT NATURAL JOIN DOCTOR WHERE PAT_ID=?");
			ps.setInt(1, patid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Appointment a = new Appointment();
				a.setAppid(rs.getInt("APP_ID"));
				a.setDocname(rs.getString("docName"));
				a.setDatetime(rs.getString("appDatetime"));
				listAppointments.add(a);
			}
			return listAppointments;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Appointment>  findAllforDoctor(int docid) {
		try {
			List<Appointment> listAppointments = new ArrayList<>();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
					//					SELECT patName,APP_ID,P.PAT_ID as patid,DOC_ID,TRD_ID,TRE_NAME,ILL_NAME,docName,appDatetime FROM PATIENT P JOIN (SELECT * FROM APPOINTMENT NATURAL JOIN DOCTOR NATURAL JOIN TREATDONE WHERE DOC_ID=122) AS E WHERE P.PAT_ID = E.PAT_ID;
					"SELECT APP_ID,patName,ILL_NAME,appDatetime FROM PATIENT P JOIN (SELECT * FROM APPOINTMENT NATURAL JOIN DOCTOR NATURAL JOIN TREATDONE WHERE DOC_ID=?) AS E WHERE P.PAT_ID = E.PAT_ID ORDER BY appDatetime DESC;");
			ps.setInt(1, docid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Appointment a = new Appointment();
				a.setAppid(rs.getInt("APP_ID"));
				a.setPatname(rs.getString("patName"));
				a.setIllname(rs.getString("ILL_NAME"));
				a.setDatetime(rs.getString("appDatetime"));
				listAppointments.add(a);
			}
			return listAppointments;
		} catch (Exception e) {
			return null;
		}
	}
}