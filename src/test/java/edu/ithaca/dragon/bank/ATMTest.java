package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    @Test
    void withdrawTest() throws Exception {
        CustomerCollection newCol = new CustomerCollection();
        newCol.addCustomer("a@b.com", "xyz");
        newCol.createAccount("a@b.com", 200);
        newCol.addCustomer("b@c.com", "xyz");
        newCol.createAccount("b@c.com", 1000);
        newCol.addCustomer("c@d.com", "xyz");
        newCol.createAccount("c@d.com", 2000);
        newCol.addCustomer("e@f.com", "xyz");
        newCol.createAccount("e@f.com", 300);
        newCol.addCustomer("f@g.com", "xyz");
        newCol.createAccount("f@g.com", 50);

        ATM bankAccount = new ATM(newCol);

        bankAccount.withdraw("a@b.com", 99);
        assertEquals(101, bankAccount.checkBalance("a@b.com"));
        bankAccount.withdraw("e@f.com", 1);
        assertEquals(299, bankAccount.checkBalance("e@f.com"));
        bankAccount.withdraw("a@b.com", 100.89);
        assertEquals(0.11, bankAccount.checkBalance("a@b.com"), 10);

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 1.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -14));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -0.001));

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 2000));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 201));
    }
    @Test
    void depositTest() throws Exception {
        //Equivalence class: valid amount-positive int
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("Mari", "love12");
        c1.createAccount("Mari", 100);

        ATM atm =new ATM(c1);

        atm.deposit("Mari",100);
        assertEquals(200, atm.checkBalance("Mari"));

        atm.deposit("Mari",1);
        assertEquals(201, atm.checkBalance("Mari"));

        atm.deposit("Mari",5000);
        assertEquals(5201, atm.checkBalance("Mari"));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () ->atm.deposit("Mari",-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> atm.deposit("Mari",-10));

        //Equivalence Class-IllegalArgumentException  more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> atm.deposit("Mari",1.908));

    }

    @Test
    void checkBalanceTest() throws Exception {
        //Equivalence Class-valid balance
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("Mari", "love12");
        c1.createAccount("Mari", 100);

        ATM atm =new ATM(c1);

        assertEquals(100, atm.checkBalance("Mari"));

        //Equivalence Class- 0 starting balance
        c1.addCustomer("Ami", "yes101");
        c1.createAccount("Ami", 0);

        assertEquals(0, atm.checkBalance("Ami"));

        //Equivalence Class- balance >1000
        c1.addCustomer("Houry", "101NP");
        c1.createAccount("Houry", 5000);

        assertEquals(5000, atm.checkBalance("Houry"));
    }
    @Test
    void transferTest() throws Exception{
        CustomerCollection newCol = new CustomerCollection();
        newCol.addCustomer("a@b.com", "xyz");
        newCol.createAccount("a@b.com", 100);
        newCol.addCustomer("b@c.com", "xyz");
        newCol.createAccount("b@c.com", 600);

        newCol.transfer("a@b.com", "b@c.com", 50);
        assertEquals(50, newCol.getBalance("a@b.com"));
        assertEquals(650, newCol.getBalance("b@c.com"));

        newCol.addCustomer("c@b.com", "xyz");
        newCol.createAccount("c@b.com", 0);
        newCol.addCustomer("z@r.com", "xyz");
        newCol.createAccount("z@r.com", 200);

        assertThrows(InsufficientFundsException.class, ()->newCol.transfer("c@b.com", "z@r.com", 300));

        assertThrows(IllegalArgumentException.class, ()->newCol.transfer("a@b.com", "b@c.com", -1));
        assertThrows(IllegalArgumentException.class, ()->newCol.transfer("a@b.com", "b@c.com", -500));
        assertThrows(IllegalArgumentException.class, ()->newCol.transfer("a@b.com", "b@c.com", -500.654));
        assertThrows(IllegalArgumentException.class, ()->newCol.transfer("a@b.com", "b@c.com", 0));
        assertThrows(IllegalArgumentException.class, ()->newCol.transfer("a@b.com", "b@c.com", 300.5654));
    }
}
