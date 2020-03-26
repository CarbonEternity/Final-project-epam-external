package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListEnrolleesCommand extends Command {

    private static final long serialVersionUID = 7732286214029478505L;

    private static final Logger LOG = Logger.getLogger(ListEnrolleesCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");

//        // get menu items list
//        List<MenuItem> menuItems = DBManager.getInstance().findMenuItems();
//        LOG.trace("Found in DB: menuItemsList --> " + menuItems);
//
//        // sort menu by category
//        Collections.sort(menuItems, new Comparator<MenuItem>() {
//            public int compare(MenuItem o1, MenuItem o2) {
//                return (int)(o1.getCategoryId() - o2.getCategoryId());
//            }
//        });

        // put menu items list to the request
//        request.setAttribute("menuItems", menuItems);
//        LOG.trace("Set the request attribute: menuItems --> " + menuItems);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_ENROLLEES;
    }

}
