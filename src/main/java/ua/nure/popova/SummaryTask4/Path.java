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
    public static final String COMMAND_ENROLLEES_LIST = "/controller?command=actionWithEnrollees";
    public static final String COMMAND_SHOW_APPLICATIONS = "/controller?command=showApplications";
    public static final String COMMAND_SHOW_FACULTY_FOR_COMPETITION = "/controller?command=showFacultiesForCompetition";
    public static final String COMMAND_SHOW_RESULT= "/controller?command=showResult";





    //client's part
    public static final String PAGE_VIEW_REQUIREMENT = "/WEB-INF/jsp/client/viewRequirements.jsp";
    public static final String PAGE_USER_HOME = "/WEB-INF/jsp/client/home.jsp";

    //admin's part
    public static final String PAGE_ADMIN_HOME = "/WEB-INF/jsp/admin/adminHome.jsp";
    public static final String COMMAND_ACTION_WITH_FACULTIES = "/controller?command=actionWithFaculties"; // FACULTIES WITH BUTTONS
    public static final String PAGE_ADMIN_SHOW_ALL_FACULTIES = "/WEB-INF/jsp/admin/allFaculties.jsp";
    public static final String PAGE_REDACT_FACULTY = "/WEB-INF/jsp/admin/redactFaculty.jsp";
    public static final String PAGE_CREATE_FACULTY = "/WEB-INF/jsp/admin/createFaculty.jsp";
    public static final String PAGE_ACTIONS_WITH_ENROLLEES = "/WEB-INF/jsp/admin/actionWithEnrollees.jsp";
    public static final String PAGE_COMPETITION = "/WEB-INF/jsp/admin/competetion.jsp";
    public static final String PAGE_FACULTIES_FOR_APPLICATIONS = "/WEB-INF/jsp/admin/facultiesForCompetition.jsp";
    public static final String PAGE_SHOW_ENROLLEE_INFO = "/WEB-INF/jsp/admin/showEnrollee.jsp";
    public static final String PAGE_SHOW_STATEMENT = "/WEB-INF/jsp/admin/statement.jsp";
    public static final String PAGE_SHOW_RESULT = "/WEB-INF/jsp/admin/result.jsp";
}
