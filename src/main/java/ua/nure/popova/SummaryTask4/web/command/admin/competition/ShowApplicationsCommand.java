package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowApplicationsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowApplicationsCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        int facultyId = Integer.parseInt(request.getParameter("id_fac"));

        List<Enrollee> enrollees = new UserDAO().findEnroleesFromApplicationsByFacultyId(facultyId);
        List<Enrollee> admittedEnrollees = new UserDAO().findAdmittedEnrolleesByFacultyId(facultyId);

        request.setAttribute("enrolleesList", enrollees);
        request.setAttribute("admittedEnrollees", admittedEnrollees);

        request.setAttribute("id_fac", facultyId);

        LOG.info("Command finished");
        return Path.PAGE_COMPETITION;
    }
}
