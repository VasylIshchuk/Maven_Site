package pl.umcs.oop.music;

import pl.umcs.oop.auth.Account;
import pl.umcs.oop.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class ListenerAccount extends Account {
    public ListenerAccount(int id, String username, int credits, Playlist playlist) {
        super(id, username);
    }
    public static void init() {
        try {
            String createSQLTable = "CREATE TABLE IF NOT EXISTS accounts( " +
                    "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "password TEXT NOT NULL," +
                    "credit INTEGER," +
                    "playlist TEXT)";
            PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(createSQLTable);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addCredit(int id, int credit){
        String sql = "UPDATE accounts SET credit=? WHERE id=?";
        try {
           PreparedStatement statement = DatabaseConnection.getConnection()
                   .prepareStatement(sql);
           statement.setInt(1,credit);
           statement.setInt(2,id);
           statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getCredit(int id){
        String sql = "SELECT * FROM accounts WHERE id=?";
        try {
            PreparedStatement statement = DatabaseConnection.getConnection()
                    .prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getInt(4);
//            Returns:
//            the column value;
//            if the value is SQL NULL,the value returned is 0
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
