package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String oraUser="hr";
	private static final String oraPw="hr";
	private static final String oraCS="jdbc:oracle:thin:@localhost:1521:xe";
	
	private static final String mysqlUser="root";
	private static final String mysqlPw="root";
	private static final String mysqlCS="jdbc:mysql://localhost:3306/world";

	private static final String huser = "dc8704";
	private static final String hdb = "narayan10";
	private static final String hpw = "K3ojwX4PL";
	private static final String hfinalcs = "jdbc:mysql://webdev.cislabs.uncw.edu/"+hdb+"?noAccessToProcedureBodies=true"+"&user="+huser+"&password="+hpw;


	public static Connection getConnection(DBType dbType) throws SQLException {
		switch (dbType) {
		case ORADB:
			return DriverManager.getConnection(oraCS, oraUser, oraPw);

		case MYSQLDB:
			return DriverManager.getConnection(mysqlCS, mysqlUser, mysqlPw);

		case HDB:
			return DriverManager.getConnection(hfinalcs);

		default:
			return null;
		}
	}
	
	public static void showErrorMessage(SQLException e) {
		System.err.println("Error :"+e.getMessage());
		System.err.println("Error code :"+e.getErrorCode());
	}
}
