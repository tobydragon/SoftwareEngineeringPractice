package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException {
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
        bankAccount.withdraw("a@b.com", 199.89);
        assertEquals(0.11, bankAccount.checkBalance("a@b.com"));

        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 0));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", 1.001));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -14));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw("a@b.com", -0.001));

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 2000));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw("a@b.com", 201));

    }
}
