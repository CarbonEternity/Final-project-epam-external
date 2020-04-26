package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.web.command.Command;
import ua.nure.popova.SummaryTask4.web.util.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig(maxFileSize = 5000000)
public class RegistrationCommand extends Command {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    private UserDAO userDao;
    private SendMail sendMail;

    public RegistrationCommand(UserDAO userDao, SendMail sendMail) {
        this.userDao = userDao;
        this.sendMail = sendMail;
    }

    public RegistrationCommand() {
        this.userDao = new UserDAO();
        this.sendMail = new SendMail();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Enrollee enrollee = extractEnrollee(request);
        Part image = getPart(request);

        userDao.registerEmployee(enrollee, image);


        sendMail.send("University entrance", "You have successfully registered for university!", enrollee.getEmail());

       LOG.info("Mail was send");

        return Path.PAGE_LOGIN;
    }

    private Part getPart(HttpServletRequest req) {

        Part commandMultipart = null;
        try {
            commandMultipart = req.getPart("img");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }

        return commandMultipart;
    }

    private Enrollee extractEnrollee(HttpServletRequest request) {
        Enrollee enrollee = new Enrollee();

        String firstName = request.getParameter("firstName");
        String secName = request.getParameter("secName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String school = request.getParameter("school");
        String password = request.getParameter("password");

        enrollee.setFirstName(firstName);
        enrollee.setSecName(secName);
        enrollee.setLastName(lastName);
        enrollee.setEmail(email);
        enrollee.setCity(city);
        enrollee.setRegion(region);
        enrollee.setSchool(school);
        enrollee.setPassword(password);

        return enrollee;
    }
}
