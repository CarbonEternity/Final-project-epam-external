package ua.nure.popova.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.Role;
import ua.nure.popova.SummaryTask4.db.dao.FacultiesDAO;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CreateOrderCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CreateOrderCommand.class);
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command start");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("userRole");
        if (user == null || role == null) {
            return Path.PAGE_LOGIN;
        }

        if (role != Role.CLIENT) {
            String errorMessage = "error.invalid.permission";
            request.setAttribute("errorMessage", errorMessage);
            return Path.PAGE_ERROR_PAGE;
        }

        Map<String, String[]> m = request.getParameterMap();
        Set s = m.entrySet();


        for (Object o : s) {

            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) o;

            String key = entry.getKey();
            String[] values = entry.getValue();

            if(key.contains("zno")){
                String [] zno = key.split("_");
                String subject = zno[1];
                boolean result = new FacultiesDAO().insertZNO(user.getId(), subject,values);
                LOG.info("Result insert ZNO to table - " +result);
            }else if(key.contains("cert")){
                String [] subject = key.split("_");
                String schoolDiscipline = subject[1];
                boolean resultInsertCertificate = new FacultiesDAO().insertCertificate(user.getId(), schoolDiscipline, values);
                LOG.info("Result insert disciplines to table - " + resultInsertCertificate);
            }


        }


        return "user_home.jsp";
    }
}
