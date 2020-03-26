package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;

import java.sql.*;

public class LoginDAO {

    private static final Logger LOG = Logger.getLogger(LoginDAO.class);

    public Enrollee checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/selection_сommittee", "root", "root");
        String sql = "SELECT * FROM enrollees WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        Enrollee enrollee = null;

        if (result.next()) {
            enrollee = new Enrollee();
            String firstName = result.getString("first_name");
            String secName = result.getString("sec_name");
            String lastName = result.getString("last_name");
            String city = result.getString("city");
            String region =result.getString("region");
            String school = result.getString("school");

            enrollee.setFirstName(firstName);
            enrollee.setSecName(secName);
            enrollee.setLastName(lastName);
            enrollee.setEmail(email);
            enrollee.setCity(city);
            enrollee.setRegion(region);
            enrollee.setSchool(school);
            enrollee.setPassword(password);
        }

        connection.close();

        return enrollee;
    }
}
