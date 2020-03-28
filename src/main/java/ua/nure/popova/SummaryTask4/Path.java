package ua.nure.popova.SummaryTask4;

public final class Path {

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_ENROLLEES = "/WEB-INF/jsp/admin/list_enrollees.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/client/list_orders.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands
    public static final String COMMAND_LIST_ENROLLEES_AND_ORDERS = "/controller?command=adminkahome";
    public static final String COMMAND_LIST_FACULTIES = "/controller?command=sortFacultyList";

    public static final String COMMAND_VIEW_REQUIREMENT = "/WEB-INF/jsp/client/viewRequirements.jsp";
    public static final String PAGE_USER_HOME = "/WEB-INF/jsp/client/home.jsp";
}
