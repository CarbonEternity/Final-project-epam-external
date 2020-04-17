package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.StatementDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ShowResultCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowResultCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.info("Command started");

        Map<Faculty, List<Enrollee>> enrolled = new StatementDAO().getResult(true);

        Map<Faculty, List<Enrollee>> notEnrolled = new StatementDAO().getResult(false);


        request.setAttribute("enrolled", enrolled);
        request.setAttribute("notEnrolled", notEnrolled);


        LOG.info("Command finished");
        //enrolled and not enrolled
        return Path.PAGE_SHOW_RESULT;
    }
}
