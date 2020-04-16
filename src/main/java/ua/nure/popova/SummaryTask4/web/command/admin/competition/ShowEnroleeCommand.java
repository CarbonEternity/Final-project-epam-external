package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.StatementDAO;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowEnroleeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowEnroleeCommand.class);
    private static final long serialVersionUID = 1L;

    // add enrollee to the competitions table
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        if(request.getParameterMap().containsKey("back")){
            return Path.COMMAND_SHOW_FACULTY_FOR_COMPETITION;
        }

        int enrolleeId = Integer.parseInt(request.getParameter("id_enr"));
        int facultyId = Integer.parseInt(request.getParameter("id_fac"));

        StatementDAO statementDAO = new StatementDAO();
        statementDAO.addEnrolleeToCompetition(enrolleeId, facultyId);

        LOG.info("Command finished");
        return Path.COMMAND_SHOW_APPLICATIONS;  //return to faculty applications but underline admitted enrollees
    }
}
