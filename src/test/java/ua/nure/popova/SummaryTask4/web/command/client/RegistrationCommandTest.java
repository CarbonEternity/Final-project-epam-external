package ua.nure.popova.SummaryTask4.web.command.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.util.SendMail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class RegistrationCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UserDAO userDAO;

    @Mock
    Part part;

    @Mock
    SendMail sendMail;

    @Before
    public void setUp() throws IOException, ServletException {
        MockitoAnnotations.initMocks(this);

        when(request.getParameter("firstName")).thenReturn("someFirstName");
        when(request.getParameter("secName")).thenReturn("someSecName");
        when(request.getParameter("lastName")).thenReturn("someLastName");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(request.getParameter("city")).thenReturn("someCity");
        when(request.getParameter("region")).thenReturn("someRegion");
        when(request.getParameter("school")).thenReturn("someSchool");
        when(request.getParameter("password")).thenReturn("somePassword");
        when(request.getPart("img")).thenReturn(part);
        when(sendMail.send("University entrance", "You have successfully registered for university!", "someEmail")).thenReturn(true);
    }

    @Test
    public void testExecute() throws AppException {
        Enrollee enrollee = extractEnrollee(request);
        when(userDAO.registerEmployee(enrollee, part)).thenReturn(1);

        RegistrationCommand registrationCommand = new RegistrationCommand(userDAO, sendMail);
        String forward = registrationCommand.execute(request, response);

        assertEquals(Path.PAGE_LOGIN, forward);
    }

    @Test
    public void testExecuteSendMail() throws AppException {
        Enrollee enrollee = extractEnrollee(request);
        when(userDAO.registerEmployee(enrollee, part)).thenReturn(1);

        RegistrationCommand registrationCommand = new RegistrationCommand(userDAO, sendMail);
        registrationCommand.execute(request, response);

        verify(sendMail, atLeast(1))
                .send("University entrance", "You have successfully registered for university!", "someEmail");
    }

    private Enrollee extractEnrollee(HttpServletRequest request) {
        Enrollee enrollee = new Enrollee();

        String firstName = request.getParameter("firstName");
        String secName = request.getParameter("secName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String school = request.getParameter("school");
        String password = request.getParameter("password");

        enrollee.setFirstName(firstName);
        enrollee.setSecName(secName);
        enrollee.setLastName(lastName);
        enrollee.setEmail(email);
        enrollee.setCity(city);
        enrollee.setRegion(region);
        enrollee.setSchool(school);
        enrollee.setPassword(password);

        return enrollee;
    }
}