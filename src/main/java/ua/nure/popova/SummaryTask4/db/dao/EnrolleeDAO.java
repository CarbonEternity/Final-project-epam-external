package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrolleeDAO {

    private static final Logger LOG = Logger.getLogger(EnrolleeDAO.class);

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
//            printSQLException(e);
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
}
