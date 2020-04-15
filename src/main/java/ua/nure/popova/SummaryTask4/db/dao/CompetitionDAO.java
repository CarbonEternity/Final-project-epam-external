package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompetitionDAO {

    private static final Logger LOG = Logger.getLogger(CompetitionDAO.class);
    private static final String SQL_ADD_ENROLEE_TO_COMPETITION = "insert into statement (id_application, id_fac) values ((select applications.id_app from applications where id_enrollee=? and id_faculty=?), ?)";


    public void addEnrolleeToCompetition(int enrolleeId, int facultyId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ADD_ENROLEE_TO_COMPETITION);

            pstmt.setInt(1, enrolleeId);
            pstmt.setInt(2, facultyId);
            pstmt.setInt(3, facultyId);

            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }

    }


}
