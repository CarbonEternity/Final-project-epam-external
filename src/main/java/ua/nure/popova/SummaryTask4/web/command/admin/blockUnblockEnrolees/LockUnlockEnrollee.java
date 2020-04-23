package ua.nure.popova.SummaryTask4.web.command.admin.blockUnblockEnrolees;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LockUnlockEnrollee extends Command {

    private static final Logger LOG = Logger.getLogger(LockUnlockEnrollee.class);
    private static final long serialVersionUID = 156778258689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command start");

        UserDAO dao = new UserDAO();
        int enrolleeId;

        if (request.getParameterMap().containsKey("block")) {
            LOG.info("user blocked");
            enrolleeId = Integer.parseInt(request.getParameter("block"));
            dao.blockEnrolleeByid(enrolleeId);
        } else if (request.getParameterMap().containsKey("unblock")) {
            LOG.info("user unblocked");
            enrolleeId = Integer.parseInt(request.getParameter("unblock"));
            dao.unblockEnrolleeByid(enrolleeId);
        }

        List<Enrollee> enrollees = new UserDAO().findAllEnrollees();
        request.setAttribute("enrolleesList", enrollees);

        LOG.info("Command finished");
        return Path.PAGE_ACTIONS_WITH_ENROLLEES;
    }
}
