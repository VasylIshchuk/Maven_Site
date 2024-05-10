package pl.umcs.oop.database;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Map<String, Connection> connections = new HashMap<>();

    public static Connection getConnection() {
        return getConnection("");
    }

    static public Connection getConnection(String name) {
        return connections.get(name);
    }

    public static void connect(String filePath) {
        connect(filePath, "");
    }

    public static void connect(String filePath, String connectionName){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
            connections.put(connectionName, connection);
            System.out.println("Connection to pl.umcs.oop.database has been established :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        disconnect("");
    }

     public static void disconnect(String connectionName){
        try {
            Connection connection = connections.get(connectionName);
            connection.close();
            connections.remove(connectionName);
            System.out.println("Disconnection to pl.umcs.oop.database has been established :(");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}