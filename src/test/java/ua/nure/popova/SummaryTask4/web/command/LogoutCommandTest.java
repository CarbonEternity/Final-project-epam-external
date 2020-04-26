package ua.nure.popova.SummaryTask4.web.command;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.assertEquals;

public class LogoutCommandTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute() {
        LogoutCommand logoutCommand = new LogoutCommand();
        String forward = logoutCommand.execute(request, response);

        assertEquals(Path.PAGE_LOGIN, forward);
    }
}