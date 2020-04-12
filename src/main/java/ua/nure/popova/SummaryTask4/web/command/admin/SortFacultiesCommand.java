package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

public class SortFacultiesCommand extends Command {

    private static final long serialVersionUID = 1893978254689586513L;
    private static final Logger LOG = Logger.getLogger(SortFacultiesCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");

        // sort by count_budget, count_total, namelast (Z-A), namefirst (A-Z)
        FacultiesDAO facultiesDAO = new FacultiesDAO();
        List<Faculty> list;
        String sort = null;
        String page = null;

        if(request.getParameterMap().containsKey("sort")){
            sort = request.getParameter("sort");
            page = Path.PAGE_ACTIONS_WITH_ENROLLEES;
        }else if(request.getParameterMap().containsKey("sortFacultiesForCompetition")){
            sort = request.getParameter("sortFacultiesForCompetition");
            page =Path.PAGE_FACULTIES_FOR_APPLICATIONS;
        }


        if (sort == null || (!sort.contains("count") && !sort.contains("name"))) {
            list = facultiesDAO.findAllFaculties();    // at the beginning
        } else { // when button *sort* was clicked
            StringBuilder sr = new StringBuilder("ORDER BY "); //part of SQL query
            if (!sort.contains("name")) {
                sr.append(sort);
            } else {
                sr.append(Fields.ENTITY_NAME);
            }

            list = facultiesDAO.sortFaculties(sr.toString());
            if (sort.contains("namelast")) {     // sort like namelast (Z-A) OR namefirst (A-Z) ?
                list.sort(Collections.reverseOrder());
            } else if (sort.contains("namefirst")) {
                Collections.sort(list);
            }
        }

        request.setAttribute("listFaculties", list);
        request.setAttribute("title", "Faculties");

        return page;
    }
}
