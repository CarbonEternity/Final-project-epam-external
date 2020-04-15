package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDAO {

    private static final Logger LOG = Logger.getLogger(EnrolleeDAO.class);
    private static final String SQL_CHECK_ACCESS_ALLOWED = "SELECT accessAllowed from enrollees where id=?";
    private static final String SQL_SELECT_ALL_ENROLLEES = "select * from enrollees";
    private static final String SQL_FIND_ENROLEES_IN_APPLICATIONS = "select id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from applications inner join enrollees e on applications.id_enrollee = e.id where id_faculty=?";
    private static final String SQL_BLOCK_ENROLLEE_WHERE_ID = "UPDATE enrollees SET accessAllowed=false where id =?";
    private static final String SQL_UNBLOCK_ENROLLEE_WHERE_ID = "UPDATE enrollees SET accessAllowed=true where id =?";
    private static final String SQL_FIND_ENROLEE_BY_ID = "select id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from enrollees where id=?";
    private static final String SQL_FIND_CERTIFICATE_BY_ENROLEE_ID = "select id, discipline_name, mark from disciplines inner join certificates c on disciplines.id_d = c.id_subject where id_enrollee=?";
    private static final String SQL_FIND_ZNO_BY_ENROLEE_AND_FACULTY_IDS = "select zno.id, discipline_name, mark from zno inner join disciplines d on zno.id_subject = d.id_d inner join requirements r on d.id_d = r.id_subject where id_enrollee=? and id_faculty=?";
    private static final String SQL_FIND_ADMITTED_ENROLLEES_FOR_FACULTY = "select enrollees.id, first_name, sec_name, last_name,city,email, school, region, accessAllowed from enrollees inner join applications a on enrollees.id = a.id_enrollee inner join statement c on a.id_app = c.id_application where id_fac=?";

    public int registerEmployee(Enrollee enrollee) {
        String INSERT_USERS_SQL = "INSERT INTO enrollees" +
                "  (first_name, sec_name, last_name, email, city, region, school) VALUES " +
                " (?, ?, ?, ?, ?,?,?);";

        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/selection_сommittee", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, enrollee.getFirstName());
            preparedStatement.setString(2, enrollee.getSecName());
            preparedStatement.setString(3, enrollee.getLastName());
            preparedStatement.setString(4, enrollee.getEmail());
            preparedStatement.setString(5, enrollee.getCity());
            preparedStatement.setString(6, enrollee.getRegion());
            preparedStatement.setString(7, enrollee.getSchool());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
            e.printStackTrace();
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                LOG.error("SQLState: " + ((SQLException) e).getSQLState());
                LOG.error("Error Code: " + ((SQLException) e).getErrorCode());
                LOG.error("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    LOG.error("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public boolean checkEnrolleeAccess(User user) throws DBException {
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
                accessAllowed = rs.getBoolean("accessAllowed");
            }
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return accessAllowed;
    }

    public List<Enrollee> findAllEnrollees() throws DBException {
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
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Enrollee> findAdmittedEnrolleesByFacultyId(int facultyId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        ResultSet rs;
        List<Enrollee> list = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ADMITTED_ENROLLEES_FOR_FACULTY);

            pstmt.setInt(1, facultyId);

            rs = pstmt.executeQuery();

            if(rs.isBeforeFirst()) {
                while (rs.next()) {
                    list.add(extractEnrollee(rs));
                }
            }
                pstmt.close();
        } catch (SQLException ex) {
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
        enrollee.setFirstName(rs.getString("first_name"));
        enrollee.setSecName(rs.getString("sec_name"));
        enrollee.setLastName(rs.getString("last_name"));
        enrollee.setEmail(rs.getString("email"));
        enrollee.setSchool(rs.getString("school"));
        enrollee.setCity(rs.getString("city"));
        enrollee.setRegion(rs.getString("region"));
        enrollee.setAccessAllowed(rs.getBoolean("accessAllowed"));

        return enrollee;
    }

    public void blockEnrolleeByid(int enrolleeId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_BLOCK_ENROLLEE_WHERE_ID);

            pstmt.setInt(1, enrolleeId);

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


    public void unblockEnrolleeByid(int enrolleeId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UNBLOCK_ENROLLEE_WHERE_ID);
            pstmt.setInt(1, enrolleeId);
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

    public List<Enrollee> sortEnrollees(String query) throws DBException {
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
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Enrollee> findEnroleesFromApplicationsByFacultyId(int facultyId) throws DBException {
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
        } catch (SQLException ex) {
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
            try {
                con = DBManager.getInstance().getConnection();
            } catch (DBException e) {
                e.printStackTrace();
            }
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

    public List<Discipline> findCertificateByEnroleeId(int enrolleeId) throws DBException {
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
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;

    }

    public List<Discipline> findZnoByEnroleeId(int enrolleeId, int facId) throws DBException {
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
        } catch (SQLException ex) {
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
        discipline.setId(rs.getLong("id"));
        discipline.setDisciplineName(rs.getString("discipline_name"));
        discipline.setMark(rs.getInt(Fields.ENTITY_MARK));
        return discipline;
    }
}
