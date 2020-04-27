package ua.nure.popova.SummaryTask4.db;

public final class Fields {

    private Fields() {
        //no op
    }

    public static final String ENTITY_ID = "id";
    public static final String ENTITY_NAME = "name";

    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_SECOND_NAME = "sec_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_SCHOOL = "school";
    public static final String USER_CITY = "city";
    public static final String USER_REGION = "region";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_ENTERED_BEFORE = "entered";
    public static final String USER_ACCESS_ALLOWED = "accessAllowed";

    public static final String FACULTY_COUNT_BUDGET = "count_budget";
    public static final String FACULTY_NAME = "faculty_name";
    public static final String FACULTY_COUNT_TOTAL = "count_total";

    public static final String DISCIPLINE_ID = "id_d";
    public static final String DISCIPLINE_NAME = "discipline_name";
    public static final String DISCIPLINE_MIN_MARK = "min_mark";
    public static final String DISCIPLINE_MARK = "mark";

    public static final String APPLICATIONS_ID = "id_app";
}
