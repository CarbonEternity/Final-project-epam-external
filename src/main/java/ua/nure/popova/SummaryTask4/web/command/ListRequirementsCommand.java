package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListRequirementsCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ListRequirementsCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command start");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("userRole");
        if (user == null || role == null) {
            return Path.PAGE_LOGIN;
        }

        if (role != Role.CLIENT) {
            String errorMessage = "error.invalid.permission";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        Integer id = Integer.parseInt(request.getParameter("id_faculty"));

        Faculty facultyInfo = new FacultiesDAO().findFacultyById(id);
        List<Discipline> disciplineList = new FacultiesDAO().findDisciplinesByFacultyId(id);
        List<Discipline> certificateDisciplineList = new FacultiesDAO().findDisciplineList();


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
        return Path.COMMAND_VIEW_REQUIREMENT;

    }
}
