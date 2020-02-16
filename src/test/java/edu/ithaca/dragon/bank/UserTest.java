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
        Account a = new CheckingAccount(100, "a@b.com", "password");
        User u = new User();

        u.addAccount(a);
        assertEquals(u.getAccount("a@b.com"), a);
    }

    @Test
    void removeAccountTest() {
        Account a = new CheckingAccount(100, "a@b.com", "password");
        User u = new User();

        assertThrows(IllegalArgumentException.class, () -> u.getAccount(""));

        u.addAccount(a);
        u.removeAccount("a@b.com");
        assertThrows(IllegalArgumentException.class, () -> u.getAccount("a@b.com"));
    }

    @Test
    void getAccountTest() {
        Account a = new CheckingAccount(100, "a@b.com", "password");
        User u = new User();

        u.addAccount(a);
        assertEquals(u.getAccount("a@b.com"), a);

        assertThrows(IllegalArgumentException.class, () -> u.getAccount(""));
    }


    //Tests adding account, getting account, and calling account methods by id
    @Test
    void IntegrationTest() throws InsufficientFundsException, AccountFrozenException{
        String idA = "a@c.com";
        String idB = "b@c.com";
        Account a = new CheckingAccount(100, idA, "password");
        Account b = new CheckingAccount(200, idB, "password");
        User u = new User();

        u.addAccount(a);
        u.addAccount(b);

        u.getAccount(idA).withdraw(50);
        assertEquals(50, u.getAccount(idA).getBalance());
        u.getAccount(idA).deposit(20);
        assertEquals(70, u.getAccount(idA).getBalance());
        u.getAccount(idB).transfer(u.getAccount(idA), 100);
        assertEquals(170, u.getAccount(idA).getBalance());
        assertEquals(100, u.getAccount(idB).getBalance());

    }

}
