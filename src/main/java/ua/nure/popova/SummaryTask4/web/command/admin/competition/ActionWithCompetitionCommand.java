package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.CompetitionDAO;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ActionWithCompetitionCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ActionWithCompetitionCommand.class);
    private static final long serialVersionUID = 1L;

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
            Enrollee enrollee = new EnrolleeDAO().findEnroleeById(enrolleeId);
            List<Discipline> enroleeZno = new EnrolleeDAO().findZnoByEnroleeId(enrolleeId, facultyId);
            List<Discipline> enroleeCertificate = new EnrolleeDAO().findCertificateByEnroleeId(enrolleeId);


            request.setAttribute("enroleeInfo", enrollee);
            request.setAttribute("id_fac", facultyId);
            request.setAttribute("enroleeZno", enroleeZno);
            request.setAttribute("enroleeCertificate", enroleeCertificate);
            forward=Path.PAGE_SHOW_ENROLLEE_INFO;
        } else if (action.contains("admit")) {
            LOG.info("command admit id enrolee = " + enrolleeId);

            CompetitionDAO competitionDAO = new CompetitionDAO();
            competitionDAO.addEnrolleeToCompetition(enrolleeId, facultyId);


            return Path.COMMAND_SHOW_APPLICATIONS;
        }

        LOG.info("Command finished");
        return forward;
    }
}
