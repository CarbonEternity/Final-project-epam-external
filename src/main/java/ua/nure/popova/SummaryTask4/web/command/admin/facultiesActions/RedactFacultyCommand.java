package ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedactFacultyCommand extends Command {


    private static final Logger LOG = Logger.getLogger(RedactFacultyCommand.class);
    private static final long serialVersionUID = 156778258689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Commands starts");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("userRole");

        if (user == null || role == null) {
            return Path.PAGE_LOGIN;
        }

        if (role != Role.ADMIN) {
            String errorMessage = "error.invalid.permission";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        FacultiesDAO dao = new FacultiesDAO();


        List<Discipline> disciplines = updatedDisciplines(request);
        //update in BD

        Faculty updatedfaculty = updatedFaculty(request);
        //update in BD

        dao.updateDisciplinesByFacultyId(disciplines, updatedfaculty.getId());
        dao.updateFaculty(updatedfaculty);

        LOG.debug("Commands finished");
        return Path.COMMAND_ACTION_WITH_FACULTIES;
    }

    private Faculty updatedFaculty(HttpServletRequest request) {
        Faculty newFaculty = new Faculty();
        String facultyId = request.getParameter("faculty_id");
        String  facultyName = request.getParameter("faculty_name");
        String budget = request.getParameter("count_budget");
        String countTotal = request.getParameter("count_total");
        newFaculty.setName(facultyName);
        newFaculty.setCountBudget(Integer.parseInt(budget));
        newFaculty.setCountTotal(Integer.parseInt(countTotal));
        newFaculty.setId(Long.valueOf(facultyId));
        return newFaculty;
    }


    private  List<Discipline> updatedDisciplines(HttpServletRequest request) {
        Map<String, String[]> m = request.getParameterMap();
        Set s = m.entrySet();
        List<Discipline> disciplineList = new ArrayList<>();

        for (Object o : s) {

            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) o;

            String key = entry.getKey();
            String[] values = entry.getValue();

            if(key.contains("disciplineName")){
                for (String value : values) {
                    Discipline d = new Discipline();
                    d.setDisciplineName(value);
                    disciplineList.add(d);
                }
            }else if(key.contains("mark")){
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    disciplineList.get(i).setMark(Integer.parseInt(value));
                }

            }

        }
        return disciplineList;
    }
}
