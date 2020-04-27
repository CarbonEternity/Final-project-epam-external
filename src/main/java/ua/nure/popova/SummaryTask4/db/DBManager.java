package ua.nure.popova.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.popova.SummaryTask4.exception.DBException;
import ua.nure.popova.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Db manager.
 *
 * @author A.Popova
 */
public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;
    private DataSource ds;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/selection_сommittee");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            ex.printStackTrace();
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            cannotObtainDataSource(ex);
        }
    }

    private void cannotObtainDataSource(NamingException ex) {
        try {
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     * @throws DBException the db exception
     */
    public Connection getConnection() throws DBException {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }

    /**
     * Rollback and close.
     *
     * @param con the con
     */
    public void rollbackAndClose(Connection con) {
        rollback(con);
        close(con);
    }

    /**
     * Commit and close.
     *
     * @param con the con
     */
    public void commitAndClose(Connection con) {
        commit(con);
        close(con);
    }

    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     *
     * @param stmt the stmt
     */
    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     *
     * @param rs the rs
     */
    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     *
     * @param con  the con
     * @param stmt the stmt
     * @param rs   the rs
     */
    public void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Commit.
     *
     * @param con the con
     */
    public void commit(Connection con) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                LOG.error("Cannot commit transaction", ex);
            }
        }
    }

}
