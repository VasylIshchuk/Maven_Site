import database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect("jdbc:sqlite:auth_account.sqlite");
        String sql = "INSERT INTO auth_account(name, password) VALUES('Benedict','1212')";
        try(Statement statement = databaseConnection.getConnection().createStatement()) {
            statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery("SELECT * FROM auth_account");
            while (rs.next()) {
                System.out.print("name = " + rs.getString("name"));
                System.out.println(" password = " + rs.getInt("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
/*
    ~/Dow/IT_2s/Programowania obiektowe/site  ./sqlite3
sqlite> CREATE TABLE auth_account(
    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    password TEXT NOT NULL);
sqlite> INSERT INTO auth_account(name, password) VALUES('Vasyl','1111');
sqlite> .save auth_account.sqlite
sqlite> .exit
 */
