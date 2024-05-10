package auth;
import at.favre.lib.crypto.bcrypt.BCrypt;
import database.DatabaseConnection;

import javax.naming.AuthenticationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    public DatabaseConnection databaseConnection;

    public AccountManager(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public void init()  {
        try {
        String sql = "CREATE TABLE accounts(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "password TEXT NOT NULL);";
        PreparedStatement statement = databaseConnection.getConnection()
                .prepareStatement(sql);
        statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(String name, String password) {
        String hashedPassword =
                BCrypt.withDefaults().hashToString(12, password.toCharArray());
        String sql = "INSERT INTO accounts(name, password) VALUES(? , ?)";
        try (PreparedStatement statement =
                     databaseConnection.getConnection().prepareStatement(sql)) {
//            The parameters can be set later using the methods of the "PreparedStatement" object
            statement.setString(1, name);
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate( String name, String password) {
        String sql = "SELECT name, password FROM accounts WHERE name = ? and password = ?";
        try (PreparedStatement statement =
                     databaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, password);
            return  statement.executeQuery().next();
//            The execute() method in PreparedStatement returns true when the query returns a result.
//            In case, if SQL query doesn't return any rows,it will still be considered successful and return true.
//            ResultSet.next() check the result exists - This will check if something exists in the
//            result to your SQL query and return true if the result exists or false if the result is empty.
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccount(String name){
        String sql = "SELECT password FROM accounts WHERE name = ? ";
        try (PreparedStatement statement =
                     databaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) return new Account(name,resultSet.getString("password"));
            else throw new AuthenticationException("No such user");
        } catch (SQLException | AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
