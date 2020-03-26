package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.web.util.SendMail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand extends Command {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);
    private EnrolleeDAO enrolleeDao = new EnrolleeDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String secName = request.getParameter("secName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String school = request.getParameter("school");
        String password = request.getParameter("password");

        Enrollee enrollee = new Enrollee();
        enrollee.setFirstName(firstName);
        enrollee.setSecName(secName);
        enrollee.setLastName(lastName);
        enrollee.setEmail(email);
        enrollee.setCity(city);
        enrollee.setRegion(region);
        enrollee.setSchool(school);
        enrollee.setPassword(password);

        try {
            enrolleeDao.registerEmployee(enrollee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SendMail sendMail = new SendMail();
        sendMail.send("University entrance", "You have successfully registered for university!", email);
        LOG.info("Mail was send");

        return "enrolleedetails.jsp";
    }
}
