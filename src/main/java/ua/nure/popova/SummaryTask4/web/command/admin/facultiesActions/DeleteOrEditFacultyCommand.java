package ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions;

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
 * Command delete or edit faculty.
 *
 * @author A.Ppopova
 */
public class DeleteOrEditFacultyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(DeleteOrEditFacultyCommand.class);
    private static final long serialVersionUID = 1863978258009586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");
        String forward = Path.PAGE_ERROR_PAGE;

        String action = request.getParameter("action");
        int facultyId = Integer.parseInt(request.getParameter("id"));
        FacultiesDAO facultiesDAO = new FacultiesDAO();

        if(action.contains("delete")){
           LOG.info("command delete id = "+ facultyId);
           facultiesDAO.deleteFacultyById(facultyId);

            forward=Path.COMMAND_ACTION_WITH_FACULTIES;
        }else if (action.contains("edit")){
            LOG.info("command edit id = "+ facultyId);
            Faculty faculty = facultiesDAO.findFacultyById(facultyId);
            List<Discipline> disciplines = facultiesDAO.findDisciplinesByFacultyId(facultyId);
            List<String> allDisciplines = facultiesDAO.findAllDisciplinesNames();

            disciplines.forEach(x-> {
                allDisciplines.remove(x.getDisciplineName());
            });


            request.setAttribute("faculty", faculty);
            request.setAttribute("listExams", disciplines);
            request.setAttribute("allDisciplines", allDisciplines);
            forward= Path.PAGE_REDACT_FACULTY;
            return forward;
        }

        return forward;
    }
}
