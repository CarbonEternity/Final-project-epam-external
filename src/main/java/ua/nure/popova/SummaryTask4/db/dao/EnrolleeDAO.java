package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDAO {

    private static final Logger LOG = Logger.getLogger(EnrolleeDAO.class);
    private static final String SQL_CHECK_ACCESS_ALLOWED = "SELECT accessAllowed from enrollees where id=?";
    private static final String SQL_SELECT_ALL_ENROLLEES = "select * from enrollees";

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
        for (Throwable e: ex) {
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
            rs=pstmt.executeQuery();

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

}
