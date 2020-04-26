package ua.nure.popova.SummaryTask4.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.exception.AppException;
import ua.nure.popova.SummaryTask4.web.command.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ControllerTest extends Mockito {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher dispatcher;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGetForward() throws ServletException, IOException {
        String errorPage = Path.PAGE_ERROR_PAGE;

        when(request.getParameter("command")).thenReturn("login");
        when(request.getParameter("email")).thenReturn("someEmail");
        when(request.getRequestDispatcher(errorPage)).thenReturn(dispatcher);

        Controller controller = new Controller();
        controller.doGet(request,response);

        verify(request).getParameter("email");
        verify(request).getParameter("command");
        verify(request, times(1)).getRequestDispatcher(errorPage);
        verify(dispatcher).forward(request, response);
    }
}