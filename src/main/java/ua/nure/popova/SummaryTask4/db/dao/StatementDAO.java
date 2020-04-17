package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementDAO {

    private static final Logger LOG = Logger.getLogger(StatementDAO.class);
    private static final String SQL_ADD_ENROLEE_TO_COMPETITION = "insert into statement (id_application, id_fac) values ((select applications.id_app from applications where id_enrollee=? and id_faculty=?), ?)";
    private static final String SQL_REMOVE_COMPETITION = "DELETE FROM statement";


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

    public Map<Faculty, List<Enrollee>> getStatement() throws DBException {
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        EnrolleeDAO enrolleeDAO = new EnrolleeDAO();

        Map<Faculty, List<Enrollee>> listMap = new HashMap<>();
        List<Faculty> faculties = facultiesDAO.findAllFaculties();

        faculties.forEach(x -> {
            try {
                List<Enrollee> enrolleesByFaculty = enrolleeDAO.findAdmittedEnrolleesByFacultyId(Math.toIntExact(x.getId()));
                listMap.put(x,enrolleesByFaculty);
            } catch (DBException e) {
                e.printStackTrace();
            }
        });
        return listMap;
    }

    public Map<Faculty, List<Enrollee>> getResult(boolean allowed) throws DBException {
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        EnrolleeDAO enrolleeDAO = new EnrolleeDAO();

        Map<Faculty, List<Enrollee>> listMap = new HashMap<>();
        List<Faculty> faculties = facultiesDAO.findAllFaculties();

        faculties.forEach(x -> {
            try {
                List<Enrollee> enrolleesByFaculty = enrolleeDAO.findEnrolledByFacultyId(Math.toIntExact(x.getId()), allowed);
                listMap.put(x,enrolleesByFaculty);
            } catch (DBException e) {
                e.printStackTrace();
            }
        });
        return listMap;
    }


    public void removeStetement() throws DBException {
        PreparedStatement pstmt;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_REMOVE_COMPETITION);

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
