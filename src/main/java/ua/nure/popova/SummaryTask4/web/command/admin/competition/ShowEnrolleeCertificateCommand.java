package ua.nure.popova.SummaryTask4.web.command.admin.competition;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command show enrollee certificate.
 *
 * @author A.Popova
 */
public class ShowEnrolleeCertificateCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ShowEnrolleeCertificateCommand.class);
    private static final long serialVersionUID = 156778261189586513L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.info("Command start");

        int idEnr = Integer.parseInt(request.getParameter("id_enr"));
        int facultyId = Integer.parseInt(request.getParameter("id_fac"));

        UserDAO userDAO = new UserDAO();
        String img = userDAO.getImageByEnrolleeId(idEnr);

        request.setAttribute("base64Image", img);

        request.setAttribute("id_enr", idEnr);
        request.setAttribute("id_fac", facultyId);

        LOG.info("Command finished");
        return Path.PAGE_SHOW_IMAGE;
    }
}
