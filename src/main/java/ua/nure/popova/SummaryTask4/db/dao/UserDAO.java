package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.DBException;
import ua.nure.popova.SummaryTask4.exception.Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAO {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String SQL_FIND_ADMIN_BY_EMAIL = "SELECT * FROM admins WHERE email=? ";
    private static final String SQL_FIND_ENROLLEE_BY_EMAIL = "SELECT * FROM enrollees WHERE email=?";

    private static final String SQL_SELECT_ALL_ENROLLEES = "select * from enrollees";
    private static final String SQL_CHECK_ACCESS_ALLOWED = "SELECT accessAllowed from enrollees where id=?";
    private static final String SQL_BLOCK_ENROLLEE_WHERE_ID = "UPDATE enrollees SET accessAllowed=false where id =?";
    private static final String SQL_UNBLOCK_ENROLLEE_WHERE_ID = "UPDATE enrollees SET accessAllowed=true where id =?";

    private static final String SQL_FIND_ENROLEES_IN_APPLICATIONS = "select id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from applications inner join enrollees e on applications.id_enrollee = e.id where id_faculty=?";
    private static final String SQL_FIND_ENROLEE_BY_ID = "select id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from enrollees where id=?";

    private static final String SQL_FIND_CERTIFICATE_BY_ENROLEE_ID = "select id, discipline_name, mark from disciplines inner join certificates c on disciplines.id_d = c.id_subject where id_enrollee=?";
    private static final String SQL_FIND_ZNO_BY_ENROLEE_AND_FACULTY_IDS = "select zno.id, discipline_name, mark from zno inner join disciplines d on zno.id_subject = d.id_d inner join requirements r on d.id_d = r.id_subject where id_enrollee=? and id_faculty=?";
    private static final String SQL_FIND_ENROLLEE_FROM_STATEMENT_BY_FACULTY_ID = "select e.id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from statement join applications a on statement.id_application = a.id_app  JOIN enrollees e on a.id_enrollee = e.id where a.id_faculty=?";
    private static final String SQL_FIND_ADMITTED_ENROLLEES_FOR_FACULTY = "select enrollees.id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from enrollees inner join applications a on enrollees.id = a.id_enrollee inner join statement c on a.id_app = c.id_application  where id_fac=?";
    private static final String SQL_SET_RESULT = "insert into result (id_faculty, id_enrolee, allowed) VALUES (?,?,?) ";

    private static final String SQL_FIND_ENROLLED_BY_FACULTY_ID = "select e.id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from result join enrollees e on result.id_enrolee = e.id where id_faculty=? and allowed=?";
    private static final String SQL_CHECK_ENTERED = "SELECT entered from enrollees where id=?";
    private static final String SQL_SET_ENTERED = "update enrollees set entered=? where enrollees.id=?";
    private static final String SQL_REGISTER_ENROLLEE = "INSERT INTO enrollees (first_name, sec_name, last_name, email, city, region, school) VALUES (?, ?, ?, ?, ?,?,?)";

    public int registerEmployee(Enrollee enrollee) {
        PreparedStatement pstmt;
        Connection con;
        int result = 0;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_REGISTER_ENROLLEE);

            pstmt.setString(1, enrollee.getFirstName());
            pstmt.setString(2, enrollee.getSecName());
            pstmt.setString(3, enrollee.getLastName());
            pstmt.setString(4, enrollee.getEmail());
            pstmt.setString(5, enrollee.getCity());
            pstmt.setString(6, enrollee.getRegion());
            pstmt.setString(7, enrollee.getSchool());
            result = pstmt.executeUpdate();
        } catch (SQLException | DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns a user with the given login.
     *
     * @param email Enrollee email.
     * @return Enrollee entity.
     * @throws DBException
     */
    public User findSomebodyByEmail(String email) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ENROLLEE_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                LOG.info("FIND USER");
                user = extractUser(rs);
            } else {
                pstmt = con.prepareStatement(SQL_FIND_ADMIN_BY_EMAIL);
                pstmt.setString(1, email);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    LOG.info("FIND ADMIN");
                    user = extractUser(rs);
                }
            }
            LOG.info("COMMIT");
            con.commit();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            DBManager.getInstance().close(con, pstmt, rs);
        }
        return user;
    }


    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        return user;
    }


    public boolean checkEnrolleeAccess(User user) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean accessAllowed = false;
        ResultSet rs;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CHECK_ACCESS_ALLOWED);

            pstmt.setInt(1, Math.toIntExact(user.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                accessAllowed = rs.getBoolean(Fields.USER_ACCESS_ALLOWED);
            }
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return accessAllowed;
    }


    public boolean checkEnrolleeEntered(User user) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean accessAllowed = false;
        ResultSet rs;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CHECK_ENTERED);

            pstmt.setInt(1, Math.toIntExact(user.getId()));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                accessAllowed = rs.getBoolean(Fields.USER_ENTERED_BEFORE);
            }
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return accessAllowed;
    }

    public List<Enrollee> findAllEnrollees() {
        List<Enrollee> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_ENROLLEES);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractEnrollee(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Enrollee> findAdmittedEnrolleesByFacultyId(int facultyId) {
        PreparedStatement pstmt;
        Connection con = null;
        ResultSet rs;
        List<Enrollee> list = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ADMITTED_ENROLLEES_FOR_FACULTY);

            pstmt.setInt(1, facultyId);

            rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    list.add(extractEnrollee(rs));
                }
            }
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    private Enrollee extractEnrollee(ResultSet rs) throws SQLException {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(rs.getLong(Fields.ENTITY_ID));
        enrollee.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        enrollee.setSecName(rs.getString(Fields.USER_SECOND_NAME));
        enrollee.setLastName(rs.getString(Fields.USER_LAST_NAME));
        enrollee.setEmail(rs.getString(Fields.USER_EMAIL));
        enrollee.setSchool(rs.getString(Fields.USER_SCHOOL));
        enrollee.setCity(rs.getString(Fields.USER_CITY));
        enrollee.setRegion(rs.getString(Fields.USER_REGION));
        enrollee.setAccessAllowed(rs.getBoolean(Fields.USER_ACCESS_ALLOWED));
        return enrollee;
    }

    public int blockEnrolleeByid(int enrolleeId) {
        PreparedStatement pstmt;
        Connection con = null;
        int result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_BLOCK_ENROLLEE_WHERE_ID);

            pstmt.setInt(1, enrolleeId);

            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    public int unblockEnrolleeByid(int enrolleeId) {
        PreparedStatement pstmt;
        Connection con = null;
        int result = 0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UNBLOCK_ENROLLEE_WHERE_ID);
            pstmt.setInt(1, enrolleeId);
            result = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    public List<Enrollee> sortEnrollees(String query) {
        List<Enrollee> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_ENROLLEES + query);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractEnrollee(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Enrollee> findEnroleesFromApplicationsByFacultyId(int facultyId) {
        List<Enrollee> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ENROLEES_IN_APPLICATIONS);
            pstmt.setInt(1, facultyId);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractEnrollee(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;

    }

    public Enrollee findEnroleeById(int enroleeId) throws DBException {
        Enrollee enrollee = new Enrollee();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ENROLEE_BY_ID);
            pstmt.setInt(1, enroleeId);
            rs = pstmt.executeQuery();
            while (rs.next())
                enrollee = extractEnrollee(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return enrollee;

    }

    public List<Discipline> findCertificateByEnroleeId(int enrolleeId) {
        List<Discipline> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_CERTIFICATE_BY_ENROLEE_ID);
            pstmt.setInt(1, enrolleeId);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractDiscipline(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;

    }

    public List<Discipline> findZnoByEnroleeId(int enrolleeId, int facId) {
        List<Discipline> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ZNO_BY_ENROLEE_AND_FACULTY_IDS);
            pstmt.setInt(1, enrolleeId);
            pstmt.setInt(2, facId);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractDiscipline(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;

    }


    private Discipline extractDiscipline(ResultSet rs) throws SQLException {
        Discipline discipline = new Discipline();
        discipline.setId(rs.getLong(Fields.ENTITY_ID));
        discipline.setDisciplineName(rs.getString(Fields.DISCIPLINE_NAME));
        discipline.setMark(rs.getInt(Fields.DISCIPLINE_MARK));
        return discipline;
    }

    public List<Enrollee> findAllEnrolleesFromStatementByFacultyId(Long id) {
        List<Enrollee> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ENROLLEE_FROM_STATEMENT_BY_FACULTY_ID);
            pstmt.setInt(1, Math.toIntExact(id));
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractEnrollee(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;

    }

    public boolean setResult(Map<Faculty, List<Enrollee>> mapOfList, boolean allowed) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean result = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SET_RESULT);

            mapOfList.forEach((k, v) -> v.forEach(enrollee -> {
                try {
                    pstmt.setInt(1, Math.toIntExact(k.getId()));
                    pstmt.setInt(2, Math.toIntExact(enrollee.getId()));
                    pstmt.setBoolean(3, allowed);

                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                setEntered(enrollee.getId(), allowed);
            }));
            result = true;
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    private boolean setEntered(Long enrolleeId, boolean allowed) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean result = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SET_ENTERED);
            pstmt.setBoolean(1, allowed);
            pstmt.setInt(2, Math.toIntExact(enrolleeId));

            pstmt.executeQuery();
            pstmt.close();
            result = true;
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }

    public List<Enrollee> findEnrolledByFacultyId(int facultyId, boolean allowed) {
        PreparedStatement pstmt;
        Connection con = null;
        ResultSet rs;
        List<Enrollee> list = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ENROLLED_BY_FACULTY_ID);

            pstmt.setInt(1, facultyId);
            pstmt.setBoolean(2, allowed);

            rs = pstmt.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    list.add(extractEnrollee(rs));
                }
            }
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }
}
