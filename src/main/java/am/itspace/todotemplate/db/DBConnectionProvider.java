package am.itspace.todotemplate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final DBConnectionProvider dbConnectionProvider;
    private Connection connection;

    static {
        dbConnectionProvider = new DBConnectionProvider();
    }

    private static final String DB_URL = "jdbc:mysql://localhost:3306/todo_template";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "root";

    private DBConnectionProvider() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static DBConnectionProvider getInstance() {return dbConnectionProvider;}

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
