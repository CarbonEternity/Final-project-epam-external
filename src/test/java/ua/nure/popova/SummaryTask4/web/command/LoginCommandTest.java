package ua.nure.popova.SummaryTask4.web.command;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.db.entity.User;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginCommandTest{

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Spy
    UserDAO userDAO;

    @Spy
    User user;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test (expected = AppException.class)
    public void executeFailTest() throws Exception{
        new LoginCommand().execute(request, response);
    }

 /*   @Test
    public void executeRedirectToErrorPageTest() throws Exception{
        when(request.getParameter("password")).thenReturn("somePassword");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(request.getSession()).thenReturn(session);
        when(userDAO.findSomebodyByEmail(request.getParameter("email"))).thenReturn(user);

        new LoginCommand().execute(request, response);


        assertEquals(Path.PAGE_ERROR_PAGE, new LoginCommand().execute(request, response));
    }*/

}