package ua.nure.popova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.Enrollee;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LoginCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    UserDAO userDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = AppException.class)
    public void testExecuteFail() throws Exception {
        new LoginCommand(userDAO).execute(request, response);
    }

    @Test
    public void testExecuteRedirectToAdminHomePage() throws Exception {
        when(request.getParameter("password")).thenReturn("someAdminPassword");
        when(request.getParameter("email")).thenReturn("someAdminEmail");
        when(request.getSession()).thenReturn(session);

        User user = new User();
        user.setId(1L);
        user.setEmail("someAdminEmail");
        user.setPassword("someAdminPassword");

        when(userDAO.findSomebodyByPassword("someAdminEmail")).thenReturn(user);

        LoginCommand loginCommand = new LoginCommand(userDAO);
        String forward = loginCommand.execute(request, response);

        assertEquals(Path.PAGE_ADMIN_HOME, forward);
    }

    @Test (expected = AppException.class)
    public void testExecuteRedirectToErrorPage() throws Exception {
        when(request.getParameter("password")).thenReturn("somePassword");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(request.getSession()).thenReturn(session);

        Enrollee user = new Enrollee();
        user.setId(1L);
        user.setEmail("someEmail");
        user.setPassword("somePassword");
        user.setRoleId(1);
        user.setEntranceStatus(1);

        when(userDAO.findSomebodyByPassword("someEmail")).thenReturn(user);
        when(userDAO.findEnroleeById(1)).thenReturn(user);

        LoginCommand loginCommand = new LoginCommand(userDAO);
        loginCommand.execute(request, response);

    }

}