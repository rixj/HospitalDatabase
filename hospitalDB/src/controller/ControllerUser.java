package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class ControllerUser {

	public User find(String user, String pw) {
		try {
			User u = new User();
			PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(""
					+ "SELECT * FROM USER NATURAL JOIN USERACCESS WHERE username=? AND password=?");
			ps.setString(1, user);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setUser(rs.getString("username"));
				u.setPw(rs.getString("password"));
				u.setId(rs.getInt("id"));
				u.setAccess(rs.getInt("access"));
			}
			return u;
		} catch (Exception e) {
			return null;
		}
	}
}