package ua.nure.popova.SummaryTask4.web.command;

import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * The type Command.
 *
 * @author A.Popova
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8879403039606311780L;

    /**
     * Execution method for command.
     *
     * @param request  the request
     * @param response the response
     * @return Address to go once the command is executed.
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     * @throws AppException     the app exception
     */
    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException,
            AppException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}