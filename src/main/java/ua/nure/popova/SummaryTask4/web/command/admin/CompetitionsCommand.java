package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CompetitionsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CompetitionsCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        String forward=null;

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


        }


        //добавить одну колонку к студентам добавлен или нет, если добавлен то селект только показать результаты


        LOG.info("Command finished");
        return forward;
    }
}
