package ua.nure.popova.SummaryTask4.web.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class NoCommandTest{

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteForwardToErrorPage() {
        NoCommand noCommand = new NoCommand();
        String forward = noCommand.execute(request,response);
        assertEquals(Path.PAGE_ERROR_PAGE, forward);
    }

    @Test
    public void testRequestContainsErrorMessage() {
        NoCommand noCommand = new NoCommand();
        noCommand.execute(request,response);
        String errorMessage = "No such command";
        verify(request).setAttribute("errorMessage", errorMessage);
    }
}