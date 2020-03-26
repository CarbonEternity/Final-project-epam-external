package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;


public class ListOrdersCommand extends Command {

    private static final long serialVersionUID = 1863978254689586513L;

    private static final Logger LOG = Logger.getLogger(ListOrdersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Commands starts");

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

        // sort by count_budget, count_total, namelast (Z-A), namefirst (A-Z)

        List<Faculty> list;
        String sort = request.getParameter("sort");
        if (sort == null || (!sort.contains("count") && !sort.contains("name"))) {
            list = new FacultiesDAO().findAllFaculties();    // at the beginning
        } else { // when button *sort* was clicked
            StringBuilder sr = new StringBuilder("ORDER BY "); //part of SQL query
            if(!sort.contains("name")){
                sr.append(sort);
            }else {
                sr.append(Fields.ENTITY_NAME);
            }

            list = new FacultiesDAO().sortFaculties(sr.toString());
            if(sort.contains("namelast")){     // sort like namelast (Z-A) OR namefirst (A-Z) ?
                list.sort(Collections.reverseOrder());
            }else if(sort.contains("namefirst")){
                Collections.sort(list);
            }
        }

        request.setAttribute("listFaculties", list);
//        request.setAttribute("title", "Список факультетов"); TODO

        return Path.PAGE_LIST_ORDERS;
    }

}
