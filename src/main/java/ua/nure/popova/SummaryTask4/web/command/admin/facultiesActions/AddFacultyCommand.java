package ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command add faculty.
 *
 * @author A.Ppopova
 */
public class AddFacultyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AddFacultyCommand.class);
    private static final long serialVersionUID = 156778258689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");

        List<String> allDisciplines = new FacultiesDAO().findAllDisciplinesNames();
        request.setAttribute("allDisciplines", allDisciplines);

        LOG.debug("Commands finished");
        return Path.PAGE_CREATE_FACULTY;
    }
}
