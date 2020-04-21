package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class SortEnrolleesCommand extends Command {

    private static final long serialVersionUID = 1844978254689586513L;
    private static final Logger LOG = Logger.getLogger(SortEnrolleesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        // sort by lastName - namelast (Z-A), namefirst (A-Z)
        UserDAO facultiesDAO = new UserDAO();
        List<Enrollee> list;
        String sort = null;
        String page = null;

        if(request.getParameterMap().containsKey("sort")){
            sort = request.getParameter("sort");
            page = Path.PAGE_ACTIONS_WITH_ENROLLEES;
        }else if(request.getParameterMap().containsKey("sortForCompetition")){
            sort = request.getParameter("sortForCompetition");
            page =Path.PAGE_COMPETITION;
        }


        if (sort == null || (!sort.contains("name"))) {
            list = facultiesDAO.findAllEnrollees();    // at the beginning
        } else { // when button *sort* was clicked

            list = facultiesDAO.sortEnrollees(//part of SQL query
                    " ORDER BY " + Fields.USER_LAST_NAME);
            if (sort.contains("namelast")) {     // sort like namelast (Z-A) OR namefirst (A-Z) ?
                list.sort(Collections.reverseOrder());
            } else if (sort.contains("namefirst")) {
                Collections.sort(list);
            }
        }

        request.setAttribute("enrolleesList", list);
        LOG.debug("Command finished");
        return page;
    }
}
