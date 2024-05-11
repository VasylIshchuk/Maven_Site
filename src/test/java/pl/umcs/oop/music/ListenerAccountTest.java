package pl.umcs.oop.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.umcs.oop.auth.Account;
import pl.umcs.oop.database.DatabaseConnection;

import javax.naming.AuthenticationException;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ListenerAccountTest {
    @BeforeAll
    public static void connectWithDatabase(){
        DatabaseConnection.connect("songs.db");
    }

    @AfterAll
    public static void disconnectWithDatabase(){
        DatabaseConnection.disconnect();
    }
    @Test
    public void testRegisterAccount() {
        ListenerAccount.init();
        Playlist playlist = new Playlist();
        Account account = new ListenerAccount(1,"Wild beast",7, playlist);
        int id = Account.Persistence.register("Wild beast","nj430sp4");
        assertEquals(id,account.getId());
    }
    @Test
    public void testAuthenticateAccount() throws AuthenticationException {
        Playlist playlist = new Playlist();
        Account listenerAccount = new ListenerAccount(1,"Wild beast",7, playlist);
        Account account = Account.Persistence.authenticate("Wild beast","nj430sp4");
        assertEquals(account.getId(),listenerAccount.getId());
    }
    @Test
    public void testMethodGetCredit(){
        int credit = ListenerAccount.getCredit(1);
        assertEquals(credit,0);
    }
    @Test
    public void testMethodAddCredit(){
        ListenerAccount.addCredit(1,3);
        assertEquals(ListenerAccount.getCredit(1),3);
    }


}