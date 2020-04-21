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
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FacultiesDAO {

    private static final Logger LOG = Logger.getLogger(FacultiesDAO.class);

    private static final String SQL_FIND_FACULTY_AND_ZNO_NEED_BY_ID = "SELECT * FROM faculties INNER JOIN requirements r ON faculties.id = r.id_faculty inner join disciplines d on r.id_subject = d.id_d where faculties.id = ";
    private static final String SQL_ALL_FACULTIES = "SELECT * FROM faculties ";
    private static final String SQL_FIND_FACULTY_BY_ID = "SELECT * FROM faculties where id = ";
    private static final String SQL_ALL_DISCIPLINES = "SELECT * FROM disciplines ";
    private static final String SQL_FIND_DISCIPLINE_BY_NAME = "SELECT * FROM disciplines where discipline_name = ?";
    private static final String SQL_INSERT_ZNO = "INSERT INTO zno (id_enrollee, id_subject, mark) VALUES (?,?,?)";

    private static final String SQL_CHECK_ZNO = "SELECT 1 from zno where id_enrollee=? and id_subject=? and mark=?";
    private static final String SQL_CHECK_CERTIFICATE = "SELECT 1 from certificates where id_enrollee=? and id_subject=? and mark=?";

    private static final String SQL_INSERT_CERTIFICATES = "INSERT INTO certificates (id_enrollee, id_subject, mark) VALUES (?,?,?)";
    private static final String SQL_FIND_ALL_FACULTIES_BY_ENROLLEE_ID = "SELECT * FROM faculties INNER JOIN applications a ON faculties.id = a.id_faculty where id_enrollee = ";
    private static final String SQL_INSERT_INTO_APPLICATIONS = "INSERT INTO applications (id_faculty ,id_enrollee) VALUES (?,?)";
    private static final String SQL_FIND_FACULTY_BY_NAME = "SELECT * FROM faculties WHERE faculties.name = ?";
    private static final String SQL_DELETE_APPLICATION = "DELETE FROM applications WHERE id_app = ";
    private static final String SQL_FIND_APPLICATION_ID = "SELECT * FROM applications WHERE id_faculty = ? AND id_enrollee = ?";
    private static final String SQL_DELETE_RESULT = "DELETE FROM statement WHERE id_application = ?";

    //admin's
    private static final String SQL_DELETE_FACULTY_BY_ID = "DELETE FROM faculties WHERE id = ";
    private static final String SQL_UPDATE_REQUIREMENTS_BY_FACULTY_ID = "UPDATE requirements SET id_subject=?, min_mark=? where id=?";
    private static final String SQL_INSERT_FACULTY = "INSERT into faculties (name, count_budget, count_total) VALUES (?,?,?)";
    private static final String SQL_UPDATE_FACULTY = "UPDATE faculties SET count_budget=?, count_total=? WHERE id=?";
    private static final String SQL_SELECT_ALL_DISCIPLINES = "SELECT discipline_name from disciplines order by id_d";
    private static final String SELECT_ID_FROM_REQUIREMENTS_BY_ID_FACULTY = "select id from requirements where id_faculty = ";
    private static final String SQL_INSERT_REQUIREMENTS = "INSERT INTO requirements (id_faculty, id_subject, min_mark) VALUES (?,?,?)";


    public List<Faculty> findAllFaculties() {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public Discipline findDisciplineByName(String name) {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return discipline;
    }

    public List<Faculty> sortFaculties(String query)  {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public Faculty findFacultyByName(String name)  {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return faculty;
    }

    public Faculty findFacultyById(Integer id) {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return faculty;
    }

    public List<Discipline> findDisciplineList()  {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Faculty> findOrderedFaculties(Long userId)  {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public List<Discipline> findDisciplinesByFacultyId(Integer id)  {
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
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public boolean insertIntoApplications(Long facultyId, Long enrolleeId)  {
        LOG.info("start insert application");
        PreparedStatement pstmt;
        Connection con = null;
        boolean flag = false;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_INTO_APPLICATIONS);
            pstmt.setLong(1, facultyId);
            pstmt.setLong(2, enrolleeId);
            pstmt.executeUpdate();
            pstmt.close();

        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("application inserted successful");

        return flag;
    }

    public int getApplicationId(Long facultyId, Long enrolleeId)  {
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
        } catch (SQLException | DBException ex) {
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
        faculty.setCountBudget(rs.getInt(Fields.FACULTY_COUNT_BUDGET));
        faculty.setCountTotal(rs.getInt(Fields.FACULTY_COUNT_TOTAL));

        return faculty;
    }

    private Discipline extractDiscipline(ResultSet rs) throws SQLException {
        Discipline discipline = new Discipline();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (Fields.DISCIPLINE_MIN_MARK.equals(rsmd.getColumnName(x))) {
                discipline.setMark(rs.getInt(Fields.DISCIPLINE_MIN_MARK));
            }
        }
        discipline.setId(rs.getLong(Fields.DISCIPLINE_ID));
        discipline.setDisciplineName(rs.getString(Fields.DISCIPLINE_NAME));
        return discipline;
    }

    // на сколько сдал зно
    public boolean insertZNO(Long enrolleeId, String key, String[] values)  {
        PreparedStatement pstmt;
        Connection con = null;
        Discipline discipline = findDisciplineByName(key);
        boolean flag = false;

            try {
                con = DBManager.getInstance().getConnection();
                pstmt = con.prepareStatement(SQL_INSERT_ZNO);

                for (String value : values) {
                    if (!value.isEmpty() && checkIfZnoNotExists(enrolleeId, discipline.getId(), value)) {
                        pstmt.setLong(1, enrolleeId);
                        pstmt.setLong(2, discipline.getId());
                        pstmt.setString(3, value);
                        pstmt.executeUpdate();
                    }
                }
                LOG.info("insert ZNO success");

                pstmt.close();
                flag = true;
            } catch (SQLException | DBException ex) {
                DBManager.getInstance().rollbackAndClose(con);
                ex.printStackTrace();

            } finally {
                assert con != null;
                DBManager.getInstance().commitAndClose(con);
            }

        return flag;
    }

    private boolean checkIfZnoNotExists(Long enrolleeId, Long id, String value)  {
        PreparedStatement pstmt;
        Connection con = null;
        boolean flag = true; //true если нет совпадений
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CHECK_ZNO);

            pstmt.setLong(1, enrolleeId);
            pstmt.setLong(2,id);
            pstmt.setString(3, value);

            rs=pstmt.executeQuery();

          if(rs.isBeforeFirst()){
              flag=false;
          }
            pstmt.close();
            LOG.warn("checkIfZnoNotExists success, column was added - " + flag);
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return flag;
    }

    private boolean checkIfCertificateNotExists(Long enrolleeId, Long id, String value)  {
        PreparedStatement pstmt;
        Connection con = null;
        boolean flag = true; //true если нет совпадений
        ResultSet rs;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_CHECK_CERTIFICATE);

            pstmt.setLong(1, enrolleeId);
            pstmt.setLong(2,id);
            pstmt.setString(3, value);

            rs=pstmt.executeQuery();

            if(rs.isBeforeFirst()){
                flag=false;
            }
            pstmt.close();
            LOG.warn("checkIfCertificateNotExists success, columns added - " + flag);
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return flag;
    }

    //занесении результата атестата
    public boolean insertCertificate(Long enrolleeId, String key, String[] values)  {
        PreparedStatement pstmt;
        Connection con = null;
        Discipline discipline = findDisciplineByName(key);

        boolean flag = false;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_CERTIFICATES);

            for (String value : values) {
                if (!value.isEmpty() && checkIfCertificateNotExists(enrolleeId, discipline.getId(), value)) {
                    pstmt.setLong(1, enrolleeId);
                    pstmt.setLong(2, discipline.getId());
                    pstmt.setString(3, value);
                    pstmt.executeUpdate();
                }
            }

            LOG.info("certificate inserted success");
            pstmt.close();
            flag = true;
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return flag;
    }

    public int deleteApplicationByFacultyAndEnroleeId(long idFaculty, Long userId)  {
        PreparedStatement pstmt;
        Connection con = null;
        int applicationId = getApplicationId(idFaculty, userId);
        int result=0;
        LOG.info("delete application");
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_APPLICATION + applicationId);
            result=pstmt.executeUpdate();
            pstmt.close();
            result=deleteResultByApplicationId(applicationId);
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("application deleted");
        return result;
    }

    public int deleteResultByApplicationId(int applicationId)  {
        PreparedStatement pstmt;
        Connection con = null;
        LOG.info("delete result");
        int result=0;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_RESULT);
            pstmt.setInt(1, applicationId);
            result=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("result deleted");
        return result;
    }

    public int deleteFacultyById(int facultyId)  {
        PreparedStatement pstmt;
        Connection con = null;
        int result=0;
        LOG.info("delete faculty");
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_DELETE_FACULTY_BY_ID + facultyId);
            result=pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("faculty deleted");
        return result;
    }

    public boolean updateDisciplinesByFacultyId(List<Discipline> disciplines, Long facultyId)  {
        PreparedStatement pstmt;
        Connection con = null;
        boolean result=false;
        LOG.info("update disciplines");
        List<Integer> ids = getRequirementsIdByFacultyId(facultyId);

        Map<Integer, Discipline> replace = IntStream.range(0, ids.size()).boxed()
                .collect(Collectors.toMap(ids::get, disciplines::get));

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_REQUIREMENTS_BY_FACULTY_ID);

            Set<Map.Entry<Integer, Discipline>> entries = replace.entrySet();

            for (Map.Entry<Integer, Discipline> entry : entries) {

                Discipline d = entry.getValue();
                Integer reqId = entry.getKey();
                d.setId(findDisciplineIdByName(d.getDisciplineName()));

                pstmt.setInt(1, Math.toIntExact(d.getId()));
                pstmt.setInt(2, d.getMark());
                pstmt.setInt(3, reqId);

                pstmt.executeUpdate();
            }

            assert pstmt != null;
            pstmt.close();
            result=true;
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("disciplines updated");
        return result;
    }

    private List<Integer> getRequirementsIdByFacultyId(Long facultyId)  {
        List<Integer> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SELECT_ID_FROM_REQUIREMENTS_BY_ID_FACULTY  + facultyId);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(rs.getInt(Fields.ENTITY_ID));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    private Long findDisciplineIdByName(String disciplineName)  {
        Discipline d = findDisciplineByName(disciplineName);
        return d.getId();
    }


    public void updateFaculty(Faculty newFaculty)  {
        PreparedStatement pstmt;
        Connection con = null;
        LOG.info("update faculty");
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_FACULTY);
            pstmt.setInt(1, newFaculty.getCountBudget());
            pstmt.setInt(2, newFaculty.getCountTotal());
            pstmt.setLong(3, newFaculty.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        LOG.info("faculty updated");
    }

    public List<String> findAllDisciplinesNames()  {
        List<String> list = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_ALL_DISCIPLINES);
            rs = pstmt.executeQuery();
            while (rs.next())
                list.add(rs.getString(Fields.DISCIPLINE_NAME));
            rs.close();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    public Faculty addFaculty(Faculty newFaculty)  {
        PreparedStatement pstmt;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_FACULTY);
            pstmt.setString(1, newFaculty.getName());
            pstmt.setInt(2, newFaculty.getCountBudget());
            pstmt.setInt(3, newFaculty.getCountTotal());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return findFacultyByName(newFaculty.getName());
    }

    public int addRequirements(Long idFaculty, List<Discipline> disciplines)  {
        PreparedStatement pstmt;
        Connection con = null;
        int result=0;

        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_INSERT_REQUIREMENTS);

            for (Discipline discipline : disciplines) {
                pstmt.setInt(1, Math.toIntExact(idFaculty));
                pstmt.setInt(2, Math.toIntExact(discipline.getId()));
                pstmt.setInt(3, discipline.getMark());

                result = pstmt.executeUpdate();
            }

            pstmt.close();
        } catch (SQLException | DBException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();

        } finally {
            assert con != null;
            DBManager.getInstance().commitAndClose(con);
        }
        return result;
    }
}
