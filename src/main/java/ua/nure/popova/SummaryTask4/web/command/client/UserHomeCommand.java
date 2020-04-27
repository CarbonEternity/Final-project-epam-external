package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserHomeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UserHomeCommand.class);
    private static final long serialVersionUID = 2L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command start");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Faculty> applications = new FacultiesDAO().findOrderedFaculties(user.getId());
        request.setAttribute("listApplications", applications);

        LOG.debug("Command finished");
        return Path.PAGE_USER_HOME;
    }
}
