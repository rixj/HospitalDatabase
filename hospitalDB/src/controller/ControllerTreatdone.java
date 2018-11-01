package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Treatdone;

public class ControllerTreatdone {

    public List<Treatdone> findAll() {
        try {
            List<Treatdone> listTreatdones = new ArrayList<>();
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from TREATDONE");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Treatdone d = new Treatdone();
                d.setTid(rs.getInt("TRD_ID"));
                d.setAppid(rs.getInt("APP_ID"));
                d.setTrename(rs.getString("TRE_NAME"));
                d.setIllname(rs.getString("ILL_NAME"));
                listTreatdones.add(d);
            }
            return listTreatdones;
        } catch (Exception e) {
            return null;
        }
    }

    public Treatdone find(int id) {
        try {
            Treatdone d = new Treatdone();
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement("select * from TREATDONE where TRD_ID=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                d.setTid(rs.getInt("TRD_ID"));
                d.setAppid(rs.getInt("APP_ID"));
                d.setTrename(rs.getString("TRE_NAME"));
                d.setIllname(rs.getString("ILL_NAME"));
            }
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean edit(Treatdone d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                    "update TREATDONE set APP_ID=?, ILL_NAME=?, TRE_NAME=? where TRD_ID=?");
            ps.setInt(1, d.getAppid());
            ps.setString(2, d.getIllname());
            ps.setString(3, d.getTrename());
            ps.setInt(4, d.getTid());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(Treatdone d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                    "delete from TREATDONE where TRD_ID=?");
            ps.setInt(1, d.getTid());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }

    }

    
    public boolean create(Treatdone d) {
        try {
            PreparedStatement ps = DBUtil.getConnection(DBType.HDB).prepareStatement(
                "insert into TREATDONE values(?,?,?,?,?)");
            ps.setInt(1, d.getTid());
            ps.setInt(2, d.getAppid());
            ps.setString(3, d.getIllname());
            ps.setString(4, d.getTrename());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

