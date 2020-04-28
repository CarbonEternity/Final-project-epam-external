package ua.nure.popova.SummaryTask4.web.command.admin.blockUnblockEnrolees;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.popova.SummaryTask4.Path;
import ua.nure.popova.SummaryTask4.db.dao.UserDAO;
import ua.nure.popova.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LockUnlockEnrolleeTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    UserDAO userDAO;

    @Mock
    Map<String, String[]> parameters;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("block")).thenReturn("1");
        when(request.getParameter("unblock")).thenReturn("1");
    }

    @Test
    public void testBlockEnrollee() throws ServletException, IOException, AppException {
        when(request.getParameterMap()).thenReturn(parameters);
        when(parameters.containsKey("block")).thenReturn(true);

        LockUnlockEnrollee lockUnlockEnrollee = new LockUnlockEnrollee(userDAO);
        String forward = lockUnlockEnrollee.execute(request,response);

        verify(userDAO, times(1)).blockEnrolleeByid(1);
        assertEquals(Path.PAGE_ACTIONS_WITH_ENROLLEES,forward);
    }

    @Test
    public void testUnlockEnrollee() throws ServletException, IOException, AppException {
        when(request.getParameterMap()).thenReturn(parameters);
        when(parameters.containsKey("unblock")).thenReturn(true);

        LockUnlockEnrollee lockUnlockEnrollee = new LockUnlockEnrollee(userDAO);
        String forward = lockUnlockEnrollee.execute(request,response);

        verify(userDAO, times(1)).unblockEnrolleeByid(1);
        assertEquals(Path.PAGE_ACTIONS_WITH_ENROLLEES,forward);
    }
}