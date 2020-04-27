package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;
import ua.nure.popova.SummaryTask4.web.util.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

/**
 * Command registration.
 *
 * @author A.Popova
 */
@MultipartConfig(maxFileSize = 5000000)
public class RegistrationCommand extends Command {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(RegistrationCommand.class);

    private UserDAO userDao;
    private SendMail sendMail;

    /**
     * Instantiates a new Registration command.
     *
     * @param userDao  the user dao
     * @param sendMail the send mail
     */
    public RegistrationCommand(UserDAO userDao, SendMail sendMail) {
        this.userDao = userDao;
        this.sendMail = sendMail;
    }

    /**
     * Instantiates a new Registration command.
     */
    public RegistrationCommand() {
        this.userDao = new UserDAO();
        this.sendMail = new SendMail();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        Enrollee enrollee = extractEnrollee(request);
        Part image = getPart(request);

        if(image==null){
            throw new AppException("Please, add the scan of your cerfificate");
        }

        List<Enrollee> registeredUsers = userDao.findAllEnrollees();
        registeredUsers.forEach(user-> {
            if(user.getEmail().equals(enrollee.getEmail())){
                try {
                    throw new AppException("Such email already exists");
                } catch (AppException e) {
                    e.printStackTrace();
                }
            }
        });

        userDao.registerEmployee(enrollee, image);

        sendMail.send("University entrance", "You have successfully registered to apply for training at Karazin University!", enrollee.getEmail());

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
