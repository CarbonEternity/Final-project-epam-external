package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command show faculties for add enrollees to competition.
 *
 * @author A.Popova
 */
public class ShowFacultiesForCompetition extends Command {

    private static final Logger LOG = Logger.getLogger(ShowFacultiesForCompetition.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        List<Faculty> faculties = new FacultiesDAO().findAllFaculties();
        request.setAttribute("listFaculties", faculties);

        LOG.info("Command finished");
        return Path.PAGE_FACULTIES_FOR_APPLICATIONS;
    }
}
