package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command show requirements.
 *
 * @author A.Popova
 */
public class ShowRequirementsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowRequirementsCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command start");

        Integer id = Integer.parseInt(request.getParameter("id_faculty"));

        Faculty facultyInfo = new FacultiesDAO().findFacultyById(id);
        List<Discipline> disciplineList = new FacultiesDAO().findDisciplinesByFacultyId(id);
        List<Discipline> certificateDisciplineList = new FacultiesDAO().getAllDisciplines();


        if (facultyInfo == null && disciplineList == null) {
            String errorMessage = "error.id_faculty";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }
        request.setAttribute("facultyInfo", facultyInfo);

        request.setAttribute("disciplineList", disciplineList);
        request.setAttribute("certificateDisciplineList", certificateDisciplineList);
        assert facultyInfo != null;
        request.setAttribute("title", facultyInfo.getName());

        LOG.debug("Command finished");
        return Path.PAGE_VIEW_REQUIREMENT;

    }
}
