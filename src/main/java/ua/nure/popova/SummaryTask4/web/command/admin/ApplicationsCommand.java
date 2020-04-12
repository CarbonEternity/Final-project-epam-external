package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ApplicationsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CompetitionsCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        int facultyId = Integer.parseInt(request.getParameter("id_fac"));

        List<Enrollee> enrollees = new EnrolleeDAO().findEnroleesFromApplicationsByFacultyId(facultyId);
        request.setAttribute("enrolleesList", enrollees);
        request.setAttribute("id_fac", facultyId);

        LOG.info("Command finished");
        return Path.PAGE_COMPETITION;
    }
}
