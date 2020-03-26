package ua.nure.popova.SummaryTask4.db.dao;

import ua.nure.popova.SummaryTask4.db.DBManager;
import ua.nure.popova.SummaryTask4.db.Fields;
import ua.nure.popova.SummaryTask4.db.entity.Discipline;
import ua.nure.popova.SummaryTask4.db.entity.Faculty;
import ua.nure.popova.SummaryTask4.exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultiesDAO {

    private static final String SQL_FIND_FACULTY_AND_ZNO_NEED_BY_ID = "SELECT * FROM faculties INNER JOIN requirements r ON faculties.id = r.id_faculty inner join disciplines d on r.id_subject = d.id where faculties.id = ";
    private static final String SQL_ALL_FACULTIES = "SELECT * FROM faculties ";
    private static final String SQL_FIND_FACULTY_BY_ID = "SELECT * FROM faculties where id = ";

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

    //TODO
    private Faculty extractFaculty(ResultSet rs) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(rs.getLong(Fields.ENTITY_ID));
        faculty.setName(rs.getString(Fields.ENTITY_NAME));
        faculty.setCountBudget(rs.getInt(Fields.ENTITY_FACULTY_COUNT_BUDGET));
        faculty.setCountTotal(rs.getInt(Fields.ENTITY_FACULTY_COUNT_TOTAL));

        return faculty;
    }

    private Discipline extractDiscipline(ResultSet rs)throws SQLException{
        Discipline discipline = new Discipline();
        discipline.setId(rs.getLong(Fields.ENTITY_ID));
        discipline.setDisciplineName(rs.getString("discipline_name"));
        discipline.setMinMark(rs.getInt(Fields.ENTITY_MIN_MARK));
        return discipline;
    }

    public List<Discipline> findDisciplinesByFacultyId(Integer id) throws DBException{
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

    public Faculty findFacultyById(Integer id) throws DBException{
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
}
