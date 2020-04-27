package ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Command show list faculties for admin.
 *
 * @author A.Ppopova
 */
public class ListFacultiesForAdminCommand extends Command {

    private static final long serialVersionUID = 1863978258689586513L;

    private static final Logger LOG = Logger.getLogger(ListFacultiesForAdminCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("userRole");

        if (user == null || role == null) {
            return Path.PAGE_LOGIN;
        }

        if (role != Role.ADMIN) {
            String errorMessage = "error.invalid.permission";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        FacultiesDAO facultiesDAO = new FacultiesDAO();
        List<Faculty> list = facultiesDAO.findAllFaculties();

        request.setAttribute("listFaculties", list);
        request.setAttribute("title", "Faculties");

        return Path.PAGE_ADMIN_SHOW_ALL_FACULTIES;
    }
}
