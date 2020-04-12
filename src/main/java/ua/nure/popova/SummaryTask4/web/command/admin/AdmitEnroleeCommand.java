package ua.nure.popova.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdmitEnroleeCommand extends Command {

    private static final Logger LOG = Logger.getLogger(AdmitEnroleeCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        return null;  //return to faculty applications but underline admitted enrollees
    }
}
