package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.StatementDAO;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command for show enrollee's profile or add him to competition.
 *
 * @author A.Popova
 */
public class ActionWithCompetitionCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ActionWithCompetitionCommand.class);
    private static final long serialVersionUID = 34567898765L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        String forward=null;

        if(request.getParameterMap().containsKey("back")){
            return Path.COMMAND_SHOW_FACULTY_FOR_COMPETITION;
        }

        String action = request.getParameter("action");
        int enrolleeId = Integer.parseInt(request.getParameter("id_enr"));
        int facultyId = Integer.parseInt(request.getParameter("id_fac"));


        if (action.contains("show")) {
            LOG.info("command show id enrolee = " + enrolleeId);
            Enrollee enrollee = new UserDAO().findEnroleeById(enrolleeId);
            List<Discipline> enroleeZno = new UserDAO().findZnoByEnroleeId(enrolleeId, facultyId);
            List<Discipline> enroleeCertificate = new UserDAO().findCertificateByEnroleeId(enrolleeId);


            request.setAttribute("enroleeInfo", enrollee);
            request.setAttribute("id_fac", facultyId);
            request.setAttribute("enroleeZno", enroleeZno);
            request.setAttribute("enroleeCertificate", enroleeCertificate);
            forward=Path.PAGE_SHOW_ENROLLEE_INFO;
        } else if (action.contains("admit")) {
            LOG.info("command admit id enrolee = " + enrolleeId);

            StatementDAO statementDAO = new StatementDAO();
            statementDAO.addEnrolleeToCompetition(enrolleeId, facultyId);


            return Path.COMMAND_SHOW_APPLICATIONS;
        }

        LOG.info("Command finished");
        return forward;
    }
}
