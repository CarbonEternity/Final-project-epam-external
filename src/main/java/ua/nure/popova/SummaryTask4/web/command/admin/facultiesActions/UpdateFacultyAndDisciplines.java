package ua.nure.popova.SummaryTask4.web.command.admin.facultiesActions;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UpdateFacultyAndDisciplines extends Command {

    private static final Logger LOG = Logger.getLogger(UpdateFacultyAndDisciplines.class);
    private static final long serialVersionUID = 156738258689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");

        FacultiesDAO dao = new FacultiesDAO();

        List<Discipline> disciplines = updatedDisciplines(request);
        Faculty newFaculty = updatedFaculty(request);

        Faculty updatedFaculty = dao.addFaculty(newFaculty);
        dao.addRequirements(updatedFaculty.getId(), disciplines);

        LOG.debug("Commands finished");
        return Path.COMMAND_ACTION_WITH_FACULTIES;
    }

    private Faculty updatedFaculty(HttpServletRequest request) {
        Faculty newFaculty = new Faculty();
        String facultyName = request.getParameter(Fields.FACULTY_NAME);
        String budget = request.getParameter(Fields.FACULTY_COUNT_BUDGET);
        String countTotal = request.getParameter(Fields.FACULTY_COUNT_TOTAL);
        newFaculty.setName(facultyName);
        newFaculty.setCountBudget(Integer.parseInt(budget));
        newFaculty.setCountTotal(Integer.parseInt(countTotal));
        return newFaculty;
    }


    private List<Discipline> updatedDisciplines(HttpServletRequest request) {
        Map<String, String[]> m = request.getParameterMap();
        Set s = m.entrySet();
        List<Discipline> disciplineList = new ArrayList<>();
        FacultiesDAO facultiesDAO = new FacultiesDAO();

        for (Object o : s) {

            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) o;

            String key = entry.getKey();
            String[] values = entry.getValue();

            if (key.contains("disciplineName")) {
                for (String value : values) {
                    Discipline d = facultiesDAO.findDisciplineByName(value);
                    disciplineList.add(d);
                }
            } else if (key.contains("mark")) {
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    disciplineList.get(i).setMark(Integer.parseInt(value));
                }

            }

        }
        return disciplineList;
    }
}
