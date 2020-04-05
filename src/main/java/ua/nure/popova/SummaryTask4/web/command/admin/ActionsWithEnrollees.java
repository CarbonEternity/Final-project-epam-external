package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.EnrolleeDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActionsWithEnrollees extends Command {

    private static final Logger LOG = Logger.getLogger(ActionsWithEnrollees.class);
    private static final long serialVersionUID = 156778258689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        List<Enrollee> enrollees = new EnrolleeDAO().findAllEnrollees();
        request.setAttribute("enrolleesList", enrollees);

        return Path.PAGE_ACTIONS_WITH_ENROLLEES;
    }
}
