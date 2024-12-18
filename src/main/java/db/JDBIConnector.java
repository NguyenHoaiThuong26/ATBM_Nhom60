package db;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;

import java.sql.Connection;
import java.sql.SQLException;
public class JDBIConnector {
    private static Jdbi jdbi;
    private JDBIConnector instance;

    public static void connect() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://" + DBProperties.host + ":" + DBProperties.port + "/" + DBProperties.name);
        System.out.println("jdbc:mysql://" + DBProperties.host + ":" + DBProperties.port + "/" + DBProperties.name);
        mysqlDataSource.setUser(DBProperties.username);
        mysqlDataSource.setPassword(DBProperties.password);

        try {
            mysqlDataSource.setAutoReconnect(true);
            mysqlDataSource.setUseCompression(true);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        jdbi = Jdbi.create(mysqlDataSource);
    }

    private JDBIConnector() {}

    public static Jdbi me() {
        if(jdbi == null) connect();
        return jdbi;
    }

    public JDBIConnector getInstance() {
        if (instance == null) instance = new JDBIConnector();
        return  instance;
    }
    public static void main(String[] args) {
        JDBIConnector test = new JDBIConnector();
        test.connect();
    }
}
