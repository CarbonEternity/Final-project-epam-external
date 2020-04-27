package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command show admin home.
 *
 * @author A.Popova
 */
public class AdminHomeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AdminHomeCommand.class);
    private static final long serialVersionUID = 156778268689586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Path.PAGE_ADMIN_HOME;
    }
}
