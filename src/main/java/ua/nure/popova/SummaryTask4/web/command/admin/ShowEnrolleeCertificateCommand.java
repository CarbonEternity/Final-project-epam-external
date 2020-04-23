package ua.nure.popova.SummaryTask4.web.command.admin;

import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowEnrolleeCertificateCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        int idEnr = Integer.parseInt(request.getParameter("id_enr"));
        int facultyId = Integer.parseInt(request.getParameter("id_fac"));

        UserDAO userDAO = new UserDAO();
        String img = userDAO.getImageByEnrolleeId(idEnr);

        request.setAttribute("base64Image", img);

        request.setAttribute("id_enr", idEnr);
        request.setAttribute("id_fac", facultyId);

        return Path.PAGE_SHOW_IMAGE;
    }
}
