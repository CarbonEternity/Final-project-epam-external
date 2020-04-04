package ua.nure.popova.SummaryTask4;

public final class Path {

    private Path() {
    }

    // pages
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_ORDERS = "/WEB-INF/jsp/client/list_orders.jsp";
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands
    public static final String COMMAND_ADMIN_HOME = "/controller?command=adminHome";
    public static final String COMMAND_LIST_FACULTIES = "/controller?command=sortFacultyList";





    //client's part
    public static final String PAGE_VIEW_REQUIREMENT = "/WEB-INF/jsp/client/viewRequirements.jsp";
    public static final String PAGE_USER_HOME = "/WEB-INF/jsp/client/home.jsp";

    //admin's part
    public static final String PAGE_ADMIN_HOME = "/WEB-INF/jsp/admin/adminHome.jsp";
    public static final String COMMAND_ACTION_WITH_FACULTIES = "/controller?command=actionWithFaculties"; // FACULTIES WITH BUTTONS
    public static final String PAGE_ADMIN_SHOW_ALL_FACULTIES = "/WEB-INF/jsp/admin/allFaculties.jsp";
    public static final String PAGE_REDACT_FACULTY = "/WEB-INF/jsp/admin/redactFaculty.jsp";
}
