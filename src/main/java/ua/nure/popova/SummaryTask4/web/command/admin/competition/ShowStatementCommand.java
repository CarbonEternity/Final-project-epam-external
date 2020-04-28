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
import java.util.concurrent.atomic.AtomicReference;

/**
 * Command show statement.
 *
 * @author A.Popova
 */
public class ShowStatementCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowStatementCommand.class);
    private static final long serialVersionUID = 98980778L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.info("Command start");

        Map<Faculty, List<Enrollee>> mapOfList = new StatementDAO().getStatement();

        if(!mapOfList.isEmpty()){
            request.setAttribute("mapOfList", mapOfList);
        }

        boolean enrolleesExists= false;
        enrolleesExists = isEnrolleesExistsInStatement(mapOfList, enrolleesExists);

        request.setAttribute("enrolleesExists", enrolleesExists);

        LOG.info("Command finished");
        return Path.PAGE_SHOW_STATEMENT;
    }

    private boolean isEnrolleesExistsInStatement(Map<Faculty, List<Enrollee>> mapOfList, boolean enrolleesExists) {
        for (Map.Entry<Faculty, List<Enrollee>> entry : mapOfList.entrySet()) {
            List<Enrollee> v = entry.getValue();
            if (!v.isEmpty()) {
                enrolleesExists = true;
                break;
            }
        }
        return enrolleesExists;
    }


}
