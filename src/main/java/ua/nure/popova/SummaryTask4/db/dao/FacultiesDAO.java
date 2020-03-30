package ua.nure.popova.SummaryTask4.db.dao;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultiesDAO {

    private static final Logger LOG = Logger.getLogger(FacultiesDAO.class);

    private static final String SQL_FIND_FACULTY_AND_ZNO_NEED_BY_ID = "SELECT * FROM faculties INNER JOIN requirements r ON faculties.id = r.id_faculty inner join disciplines d on r.id_subject = d.id where faculties.id = ";
    private static final String SQL_ALL_FACULTIES = "SELECT * FROM faculties ";
    private static final String SQL_FIND_FACULTY_BY_ID = "SELECT * FROM faculties where id = ";
    private static final String SQL_ALL_DISCIPLINES = "SELECT * FROM disciplines ";
    private static final String SQL_FIND_DISCIPLINE_BY_NAME = "SELECT * FROM disciplines where discipline_name = ?";
    private static final String SQL_INSERT_ZNO = "INSERT INTO zno (id_enrollee, id_subject, mark) VALUES (?,?,?)";
    private static final String SQL_INSERT_CERTIFICATES = "INSERT INTO certificates (id_enrollee, id_subject, mark) VALUES (?,?,?)";
    private static final String SQL_INSERT_DISCIPLINE = "INSERT INTO disciplines (discipline_name) VALUE (?)";
    private static final String SQL_FIND_ALL_FACULTIES_BY_ENROLLEE_ID = "SELECT * FROM faculties INNER JOIN applications a ON faculties.id = a.id_faculty where id_enrollee = ";
    private static final String SQL_INSERT_INTO_APPLICATIONS = "INSERT INTO applications (id_faculty ,id_enrollee) VALUES (?,?)";
    private static final String SQL_INSERT_INTO_RESULTS = "INSERT INTO results (id_application, result) VALUES (?, ?)";
    private static final String SQL_FIND_FACULTY_BY_NAME = "SELECT * FROM faculties WHERE faculties.name = ?";
    private static final String SQL_DELETE_APPLICATION = "DELETE FROM applications WHERE id = ";
    private static final String SQL_FIND_APPLICATION_ID = "SELECT * FROM applications WHERE id_faculty = ? AND id_enrollee = ?";
    private static final String SQL_DELETE_RESULT = "DELETE FROM results WHERE id_application = ?";

    public List<Faculty> findAllFaculties() throws DBException {
        List<Faculty> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ALL_FACULTIES);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractFaculty(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public Discipline findDisciplineByName(String name) throws DBException {
        Discipline discipline = new Discipline();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_DISCIPLINE_BY_NAME);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            while (rs.next())
                discipline = extractDiscipline(rs);


            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return discipline;
    }

    public List<Faculty> sortFaculties(String query) throws DBException {
        List<Faculty> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ALL_FACULTIES + query);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractFaculty(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public Faculty findFacultyByName(String name) throws DBException {
        Faculty faculty = new Faculty();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_FACULTY_BY_NAME);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();
            while (rs.next())
                faculty = extractFaculty(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return faculty;
    }

    public Faculty findFacultyById(Integer id) throws DBException {
        Faculty faculty = new Faculty();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_FACULTY_BY_ID + id);
            rs = pstmt.executeQuery();
            while (rs.next())
                faculty = extractFaculty(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return faculty;
    }

    public List<Discipline> findDisciplineList() throws DBException {
        List<Discipline> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ALL_DISCIPLINES);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractDiscipline(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Faculty> findOrderedFaculties(Long userId) throws DBException {
        List<Faculty> list = new ArrayList<>();

        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_ALL_FACULTIES_BY_ENROLLEE_ID + userId);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractFaculty(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Discipline> findDisciplinesByFacultyId(Integer id) throws DBException {
        List<Discipline> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_FACULTY_AND_ZNO_NEED_BY_ID + id);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(extractDiscipline(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public boolean insertIntoApplications(Long facultyId, Long enrolleeId) throws DBException {
        LOG.info("start insert application");
        PreparedStatement pstmt;
        Connection con = null;
        boolean flag = false;
        long idApplication;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_APPLICATIONS);
            pstmt.setLong(1, facultyId);
            pstmt.setLong(2, enrolleeId);
            pstmt.executeUpdate();
            pstmt.close();

            idApplication = getApplicationId(facultyId, enrolleeId);
            flag = insertIntoResults(idApplication, false);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("application inserted successful");

        return flag;
    }

    private boolean insertIntoResults(long idApplication, boolean result) throws DBException {
        LOG.info("start insert result");
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean flag;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_RESULTS);
            pstmt.setLong(1, idApplication);
            pstmt.setBoolean(2, result);
            pstmt.executeUpdate();
            pstmt.close();

            flag = true;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
            flag = false;
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("result inserted successful");
        return flag;
    }

    public int getApplicationId(Long facultyId, Long enrolleeId) throws DBException {
        LOG.info("get application's id");
        PreparedStatement pstmt;
        Connection con = null;
        int applicationId = 0;
        ResultSet rs;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_APPLICATION_ID);
            pstmt.setLong(1, facultyId);
            pstmt.setLong(2, enrolleeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                applicationId = rs.getInt(Fields.ENTITY_ID);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return applicationId;
    }

    private Faculty extractFaculty(ResultSet rs) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(rs.getLong(Fields.ENTITY_ID));
        faculty.setName(rs.getString(Fields.ENTITY_NAME));
        faculty.setCountBudget(rs.getInt(Fields.ENTITY_FACULTY_COUNT_BUDGET));
        faculty.setCountTotal(rs.getInt(Fields.ENTITY_FACULTY_COUNT_TOTAL));

        return faculty;
    }

    private Discipline extractDiscipline(ResultSet rs) throws SQLException {
        Discipline discipline = new Discipline();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (Fields.ENTITY_MIN_MARK.equals(rsmd.getColumnName(x))) {
                discipline.setMinMark(rs.getInt(Fields.ENTITY_MIN_MARK));
            }
        }
        discipline.setId(rs.getLong(Fields.ENTITY_ID));
        discipline.setDisciplineName(rs.getString("discipline_name"));
        return discipline;
    }

    // на сколько сдал зно
    public boolean insertZNO(Long enrolleeId, String key, String[] values) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        Discipline discipline = findDisciplineByName(key);
        boolean flag = false;
        if (values.length != 0) {
            try {
                con = DBManager.getInstance().getConnection();
                pstmt = con.prepareStatement(SQL_INSERT_ZNO);

                for (String value : values) {
                    if (!value.isEmpty()) {
                        pstmt.setLong(1, enrolleeId);
                        pstmt.setLong(2, discipline.getId());
                        pstmt.setString(3, value);
                    }
                }
                LOG.info("insertZNO success");
                pstmt.executeUpdate();
                pstmt.close();
                flag = true;
            } catch (SQLException ex) {
                DBManager.getInstance().rollbackAndClose(con);
                ex.printStackTrace();

            } finally {
                assert con != null;
                DBManager.getInstance().commitAndClose(con);
            }
        }
        return flag;
    }

    /*private Discipline checkDiscipline(String d) throws DBException {  //TODO
        PreparedStatement pstmt;
        Connection con = null;
        Discipline discipline = new Discipline();
        ResultSet rs;
        long id = 0;
        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ALL_DISCIPLINES);
            rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columns = rsmd.getColumnCount();
            for (int x = 1; x <= columns; x++) {
                if (d.equals(rsmd.getColumnName(x))) { //if such exists

                    discipline = findDisciplineByName(d);
                    flag = true;
                }
            }

            if (!flag && !d.equals(" ")) {
                insertNewDiscipline(d);
                discipline = findDisciplineByName(d);
                flag = true;
            }

            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return discipline;
    }*/

  /*  private boolean insertNewDiscipline(String name) throws DBException { //TODO
        PreparedStatement pstmt = null;
        Connection con = null;
        boolean flag = false;
        if (!name.equals(" ")) {
            try {
                con = DBManager.getInstance().getConnection();
                pstmt = con.prepareStatement(SQL_INSERT_DISCIPLINE);
                pstmt.setString(1, name);
                pstmt.executeUpdate();
                pstmt.close();
                flag = true;
            } catch (SQLException ex) {
                DBManager.getInstance().rollbackAndClose(con);
                ex.printStackTrace();

            } finally {
                assert con != null;
                DBManager.getInstance().commitAndClose(con);
            }
        }
        return flag;
    }*/

    //занесении результата атестата
    public boolean insertCertificate(Long enrolleeId, String key, String[] values) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        Discipline discipline = findDisciplineByName(key);

        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_CERTIFICATES);

            for (String value : values) {
                if (!value.isEmpty()) {
                    pstmt.setLong(1, enrolleeId);
                    pstmt.setLong(2, discipline.getId());
                    pstmt.setString(3, value);
                }
            }

            pstmt.executeUpdate();
            pstmt.close();
            flag = true;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return flag;
    }

    public void deleteApplicationByFacultyAndEnroleeId(long idFaculty, Long userId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        int applicationId = getApplicationId(idFaculty, userId);
        LOG.info("delete application");
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_APPLICATION+applicationId);
            pstmt.executeUpdate();
            pstmt.close();
            deleteResultByApplicationId(applicationId);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("application deleted");
    }

    public void deleteResultByApplicationId(int applicationId) throws DBException {
        PreparedStatement pstmt;
        Connection con = null;
        LOG.info("delete result");
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_RESULT);
            pstmt.setInt(1, applicationId);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("result deleted");
    }
}
