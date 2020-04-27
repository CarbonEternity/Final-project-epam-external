package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.StatementDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Command show statement.
 *
 * @author A.Popova
 */
public class ShowStatementCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowStatementCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        Map<Faculty, List<Enrollee>> mapOfList = new StatementDAO().getStatement();


        request.setAttribute("mapOfList", mapOfList);
        LOG.info("Command finished");
        return Path.PAGE_SHOW_STATEMENT;
    }


}
