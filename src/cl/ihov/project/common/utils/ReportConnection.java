package cl.ihov.project.common.utils;

import cl.ihov.project.common.utils.BaseResources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportConnection {

    private static final String username = BaseResources.getValue("db_config", "username");
    private static final String password = BaseResources.getValue("db_config", "password");
    private static final String url = BaseResources.getValue("db_config", "url");
    private static final String driver = BaseResources.getValue("db_config", "driver");

    private Connection connection = null;

    public void doConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReportConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void doDisconnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection = null;
        }
    }

    public Connection getConection() {
        return connection;
    }
}
