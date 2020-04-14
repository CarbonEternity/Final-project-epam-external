package ua.nure.popova.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.exception.DBException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplyForStudyCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ApplyForStudyCommand.class);
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

        parseDisciplinesAndZNO(request, user);
        FacultiesDAO facultiesDAO = new FacultiesDAO();

        String title = request.getParameter("title");
        Faculty faculty = facultiesDAO.findFacultyByName(title);
        facultiesDAO.insertIntoApplications(faculty.getId(), user.getId());


        List<Faculty> applications = facultiesDAO.findOrderedFaculties(user.getId());

        request.setAttribute("listApplications", applications);

        return Path.PAGE_USER_HOME;
    }

    private void parseDisciplinesAndZNO(HttpServletRequest request, User user) throws DBException {
        Map<String, String[]> m = request.getParameterMap();
        Set s = m.entrySet();

        for (Object o : s) {

            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) o;

            String key = entry.getKey();
            String[] values = entry.getValue();

            if (key.contains("zno")) {  //TODO заменить название предмета на айди
                String[] zno = key.split("_");
                String subject = zno[1];

                for (String value : values) {
                    if (!value.equals("")) {

                        boolean result = new FacultiesDAO().insertZNO(user.getId(), subject, values);
                    }
                    LOG.info("Result insert ZNO to table +");
                }
            } else if (key.contains("cert") && values.length != 0) {
                String[] subject = key.split("_");
                String schoolDiscipline = subject[1];

                for (String value : values) {
                    if (!value.equals("")) {
                        boolean resultInsertCertificate = new FacultiesDAO().insertCertificate(user.getId(), schoolDiscipline, values);
                    }
                    LOG.info("Result insert disciplines to table + ");
                }
            }

        }
    }
}
