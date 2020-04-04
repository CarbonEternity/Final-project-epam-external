package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChangeFacultyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ChangeFacultyCommand.class);
    private static final long serialVersionUID = 1863978258009586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("userRole");
        String forward = Path.PAGE_ERROR_PAGE;

        if (user == null || role == null) {
            forward =Path.PAGE_LOGIN;
            return forward;
        }

        if (role != Role.ADMIN) {
            String errorMessage = "error.invalid.permission";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        String action = request.getParameter("action");
        int facultyId = Integer.parseInt(request.getParameter("id"));
        FacultiesDAO facultiesDAO = new FacultiesDAO();

        if(action.contains("delete")){
           LOG.info("command delete id = "+ facultyId);
           facultiesDAO.deleteFacultyById(facultyId); //work

            forward=Path.COMMAND_ACTION_WITH_FACULTIES;
        }else if (action.contains("edit")){
            LOG.info("command edit id = "+ facultyId);
            Faculty faculty = facultiesDAO.findFacultyById(facultyId);
            List<Discipline> disciplines = facultiesDAO.findDisciplinesByFacultyId(facultyId);


            request.setAttribute("faculty", faculty);
            request.setAttribute("listExams", disciplines);
            forward= Path.PAGE_REDACT_FACULTY;
            return forward;
        }

        return forward;
    }
}
