package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    void constructorTest() {
        User u = new User();
    }

    @Test
    void addAccountTest() {
        Account a = new CheckingAccount(100, "a@b.com");
        User u = new User();

        u.addAccount(a);
        assertEquals(u.getAccount("a@b.com"), a);
    }

    @Test
    void removeAccountTest() {
        Account a = new CheckingAccount(100, "a@b.com");
        User u = new User();

        assertThrows(IllegalArgumentException.class, () -> u.getAccount(""));

        u.addAccount(a);
        u.removeAccount("a@b.com");
        assertThrows(IllegalArgumentException.class, () -> u.getAccount("a@b.com"));
    }

    @Test
    void getAccountTest() {
        Account a = new CheckingAccount(100, "a@b.com");
        User u = new User();

        u.addAccount(a);
        assertEquals(u.getAccount("a@b.com"), a);

        assertThrows(IllegalArgumentException.class, () -> u.getAccount(""));
    }

}
