package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command change locale.
 *
 * @author A.Popova
 */
public class ChangeLocaleCommand extends Command {

    private static final long serialVersionUID = -77776616686657267L;

    private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("command starts");

        HttpSession session = request.getSession();
        String forward = Path.PAGE_ERROR_PAGE;

        User user = (User) session.getAttribute("user");
        Role userRole = Role.getRole(user);
        LOG.info("userRole --> " + userRole);

        if (userRole == Role.ADMIN) {
            forward = Path.PAGE_ADMIN_HOME;
        }

        if (userRole == Role.CLIENT) {
            forward = Path.COMMAND_LIST_FACULTIES;
        }

        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);

        LOG.info("command finished");
        return forward;
    }
}
