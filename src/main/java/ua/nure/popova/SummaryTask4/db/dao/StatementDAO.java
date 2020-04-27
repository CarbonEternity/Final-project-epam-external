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

/**
 * The type Statement dao.
 *
 * @author A.Popova
 */
public class StatementDAO {

    private static final Logger LOG = Logger.getLogger(StatementDAO.class);

    private static final String SQL_ADD_ENROLEE_TO_COMPETITION = "insert into statement (id_application, id_fac) values ((select applications.id_app from applications where id_enrollee=? and id_faculty=?), ?)";
    private static final String SQL_REMOVE_COMPETITION = "DELETE FROM statement";


    /**
     * Add enrollee to competition.
     *
     * @param enrolleeId the enrollee id
     * @param facultyId  the faculty id
     * @return int
     * @throws DBException the db exception
     */
    public int addEnrolleeToCompetition(int enrolleeId, int facultyId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        int result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ADD_ENROLEE_TO_COMPETITION);

            pstmt.setInt(1, enrolleeId);
            pstmt.setInt(2, facultyId);
            pstmt.setInt(3, facultyId);

            result = pstmt.executeUpdate();
            pstmt.close();
            LOG.info("enrollee was added to competition, id enrollee  -> "+enrolleeId);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    /**
     * Gets statement.
     *
     * @return the statement
     * @throws DBException the db exception
     */
    public Map<Faculty, List<Enrollee>> getStatement() throws DBException {
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        UserDAO userDAO = new UserDAO();

        Map<Faculty, List<Enrollee>> listMap = new HashMap<>();
        List<Faculty> faculties = facultiesDAO.findAllFaculties();

        faculties.forEach(x -> {
            List<Enrollee> enrolleesByFaculty = userDAO.findAdmittedEnrolleesByFacultyId(Math.toIntExact(x.getId()));
            listMap.put(x, enrolleesByFaculty);
        });
        return listMap;
    }

    /**
     * Gets result.
     *
     * @param allowed the allowed
     * @return the result
     * @throws DBException the db exception
     */
    public Map<Faculty, List<Enrollee>> getResult(boolean allowed) throws DBException {
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        UserDAO userDAO = new UserDAO();

        Map<Faculty, List<Enrollee>> listMap = new HashMap<>();
        List<Faculty> faculties = facultiesDAO.findAllFaculties();

        faculties.forEach(x -> {
            List<Enrollee> enrolleesByFaculty = userDAO.findEnrolledByFacultyId(Math.toIntExact(x.getId()), allowed);
            listMap.put(x, enrolleesByFaculty);
        });
        return listMap;
    }


    /**
     * Remove stetement.
     *
     * @return int
     * @throws DBException the db exception
     */
    public int removeStetement() throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        int result = 0;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_REMOVE_COMPETITION);

            result=pstmt.executeUpdate();
            pstmt.close();
            LOG.info("all statements were removed success");
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }
}
