package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.exception.DBException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowStatementCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowStatementCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        Map<Faculty, List<Enrollee>> mapOfList = setStatement();


        request.setAttribute("mapOfList", mapOfList);
        LOG.info("Command finished");
        return Path.PAGE_SHOW_STATEMENT;
    }

    public Map<Faculty, List<Enrollee>> setStatement() throws DBException {
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        EnrolleeDAO enrolleeDAO = new EnrolleeDAO();

        Map<Faculty, List<Enrollee>> listMap = new HashMap<>();
        List<Faculty> faculties = facultiesDAO.findAllFaculties();

        faculties.forEach(x -> {
            try {
                List<Enrollee> enrolleesByFaculty = enrolleeDAO.findAdmittedEnrolleesByFacultyId(Math.toIntExact(x.getId()));
                listMap.put(x,enrolleesByFaculty);
            } catch (DBException e) {
                e.printStackTrace();
            }
        });
        return listMap;
    }


}
