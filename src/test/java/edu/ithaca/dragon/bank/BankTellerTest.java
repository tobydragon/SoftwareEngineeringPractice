package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {

    @Test
    void withdrawTest() throws Exception {
        BankTeller bankAccount = new BankTeller();

        bankAccount.createAccount("a@b.com", "xyz", 200);
        bankAccount.createAccount("b@c.com", "xyz", 500);
        bankAccount.createAccount("c@d.com", "xyz", 1000);
        bankAccount.createAccount("e@f.com", "xyz", 10);
        bankAccount.createAccount("f@g.com", "xyz", 150);

        bankAccount.withdraw("a@b.com", 100);

        assertEquals(100, bankAccount.checkBalance("a@b.com"));

        //Equivalence Class Has balance
        bankAccount.withdraw("a@b.com", 99);//valid withdraw
        assertEquals(1, bankAccount.checkBalance("a@b.com"),10);
        bankAccount.withdraw("b@c.com", 1);//valid withdraw edgecase
        assertEquals(499, bankAccount.checkBalance("b@c.com"));
        bankAccount.withdraw("c@d.com", 0.1);
        assertEquals(999.9, bankAccount.checkBalance("c@d.com"), 10);
        bankAccount.withdraw("a@b.com", 0.01);
        assertEquals(0.99, bankAccount.checkBalance("a@b.com"), 10);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 201));
        assertEquals(0.99, bankAccount.checkBalance("a@b.com"), 10);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 20100));
        assertEquals(0.99, bankAccount.checkBalance("a@b.com"), 10);
        bankAccount.withdraw("a@b.com", 0.99);//perfect withdraw edgecase
        assertEquals(0, bankAccount.checkBalance("a@b.com"), 10);

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 1.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -14));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -0.001));

        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw("asdfa", 100));
    }

    @Test
    void depositTest() throws Exception {
        //Equivalence class: valid amount-positive int
        BankTeller bt1 = new BankTeller();
        bt1.createAccount("Mari", "love12", 100);
        bt1.deposit("Mari", 100);
        assertEquals(200, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 1);
        assertEquals(201, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 1.01);
        assertEquals(202.01, bt1.checkBalance("Mari"));

        bt1.deposit("Mari", 5000);
        assertEquals(5202.01, bt1.checkBalance("Mari"));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", -1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", -10));

        //Equivalence Class-IllegalArgumentException  more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bt1.deposit("Mari", 1.908));
    }

    @Test
    void checkBalanceTest() throws Exception {
        //Equivalence class: valid amount-positive int
        BankTeller bt1 = new BankTeller();
        bt1.createAccount("Mari", "love12", 100);

        assertEquals(100, bt1.checkBalance("Mari"));

        //Equivalence Class- 0 starting balance
        bt1.createAccount("Ami", "yes101", 0);

        assertEquals(0, bt1.checkBalance("Ami"));

        //Equivalence Class- balance >1000
        bt1.createAccount("Houry", "101NP", 5000);

        assertEquals(5000, bt1.checkBalance("Houry"));
    }

    void testCreateAccount() throws Exception {
        BankTeller b1 = new BankTeller();
        assertEquals(0, b1.getNumCustomers());
        b1.createAccount("bob", "password", 100);
        assertEquals(1, b1.getNumCustomers());
        b1.createAccount("bb", "password", 100);
        assertEquals(2, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("bb", "password", 100));
        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("b", "password", -100));
        assertThrows(IllegalArgumentException.class, () -> b1.createAccount("b", "password", 100.001));
    }

    @Test
    void testCloseAccount() throws Exception {
        BankTeller b1 = new BankTeller();
        b1.createAccount("bob", "password", 100);
        b1.createAccount("bb", "password", 100);
        assertEquals(2, b1.getNumCustomers());
        b1.closeAccount("bob");
        assertEquals(1, b1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, () -> b1.closeAccount("bob"));
    }

    @Test
    void transferTest() throws Exception{
        BankTeller bankAccount = new BankTeller();

        bankAccount.createAccount("a@b.com", "xyz", 200);
        bankAccount.createAccount("b@c.com", "xyz", 500);
        bankAccount.createAccount("c@d.com", "xyz", 1000);
        bankAccount.createAccount("e@f.com", "xyz", 10);
        bankAccount.createAccount("z@y.com", "xyz", 150);
        bankAccount.createAccount("d@j.com", "xyz", 1);
        bankAccount.createAccount("a@v.com", "xyz", 0);

        bankAccount.transfer("a@b.com", "b@c.com", 100);
        assertEquals(100, bankAccount.checkBalance("a@b.com"));
        assertEquals(600, bankAccount.checkBalance("b@c.com"));

        assertThrows(InsufficientFundsException.class, ()->bankAccount.transfer("a@v.com", "e@f.com", 300));

        assertThrows(IllegalArgumentException.class, ()->bankAccount.transfer("c@d.com", "e@f.com", -1));
        assertThrows(IllegalArgumentException.class, ()->bankAccount.transfer("a@v.com", "d@j.com", -500));
        assertThrows(IllegalArgumentException.class, ()->bankAccount.transfer("a@v.com", "d@j.com", -500.8979));
        assertThrows(IllegalArgumentException.class, ()->bankAccount.transfer("d@j.com", "e@f.com", 938.845));
        assertThrows(IllegalArgumentException.class, ()->bankAccount.transfer("d@j.com", "e@f.com", 0));
        assertThrows(InsufficientFundsException.class, ()->bankAccount.transfer("z@y.com", "c@d.com", 938.84));
    }
}