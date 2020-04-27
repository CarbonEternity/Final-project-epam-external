package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command delete application.
 *
 * @author A.Popova
 */
public class DeleteApplicationCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteApplicationCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command start");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int idForDelete = Integer.parseInt(request.getParameter("id_faculty"));
        LOG.info("for delete application " + idForDelete);
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        facultiesDAO.deleteApplicationByFacultyAndEnroleeId(idForDelete, user.getId());

        LOG.debug("Command finished");
        return Path.COMMAND_LIST_FACULTIES;
    }
}
