package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.exception.Messages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command login starts");

        HttpSession session = request.getSession();

        UserDAO userDAO = new UserDAO();
        String email = request.getParameter("email");
        LOG.trace("Request parameter: email --> " + email);

        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new AppException("Email/password cannot be empty");
        }

        User user = userDAO.findSomebodyByEmail(email);

        LOG.info("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such email/password");
        }

        String forward = Path.PAGE_ERROR_PAGE;

        Role userRole = Role.getRole(user);
        LOG.info("userRole --> " + userRole);

        if (userRole == Role.ADMIN) {
            forward = Path.PAGE_ADMIN_HOME;
        }

        if (userRole == Role.CLIENT) {
            if (enrolleeEntered(user)) {
                throw new AppException(Messages.ERR_ENROLLEE_ARE_ENTERED_THE_UNIVERSITY);
            }
            forward = Path.COMMAND_LIST_FACULTIES;
        }

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command login finished");


        return forward;
    }

    private boolean enrolleeEntered(User user) {
        boolean accessEnrolleeAllowed;
        accessEnrolleeAllowed = new UserDAO().checkEnrolleeEntered(user);
        return accessEnrolleeAllowed;
    }
}
