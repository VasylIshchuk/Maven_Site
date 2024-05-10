package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    public void connect(String path){
        try {
            connection = DriverManager.getConnection(path);
            System.out.println("Connection to database has been established :)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            connection.close();
            System.out.println("Disconnection to database has been established :(");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
