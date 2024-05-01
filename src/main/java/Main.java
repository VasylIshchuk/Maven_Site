import database.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
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
