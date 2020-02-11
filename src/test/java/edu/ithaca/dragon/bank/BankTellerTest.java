package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTellerTest {

    @Test
    void withdrawTest() throws InsufficientFundsException{
        CustomerCollection newCol= new CustomerCollection();
        BankTeller bankAccount = new BankTeller();

        bankAccount.createAccount("a@b.com", "xyz", 200);
        bankAccount.createAccount("b@c.com", "xyz", 500);
        bankAccount.createAccount("c@d.com", "xyz",1000);
        bankAccount.createAccount("e@f.com", "xyz", 10);
        bankAccount.createAccount("f@g.com", "xyz", 150);

        bankAccount.withdraw("a@b.com", 200);

        assertEquals(100, bankAccount.checkBalance("a@b.com"));

        //Equivalence Class Has balance
        BankTeller bankAccount1 = new BankTeller();
        bankAccount1.withdraw("a@b.com",99);//valid withdraw
        assertEquals( 201, bankAccount1.checkBalance("a@b.com"));
        bankAccount1.withdraw("a@b.com", 1);//valid withdraw edgecase
        assertEquals(200, bankAccount1.checkBalance("a@b.com"));
        bankAccount1.withdraw("a@b.com", 0.1);
        assertEquals(199.9, bankAccount1.checkBalance("a@b.com"),10);
        bankAccount1.withdraw("a@b.com", 0.01);
        assertEquals(199.89, bankAccount1.checkBalance("a@b.com"), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw("a@b.com", 201));
        assertEquals(199.89, bankAccount1.checkBalance("a@b.com"), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw("a@b.com", 20100));
        assertEquals(199.89, bankAccount1.checkBalance("a@b.com"), 10);
        bankAccount1.withdraw("a@b.com", 199.89);//perfect withdraw edgecase
        assertEquals(0, bankAccount1.checkBalance("a@b.com"), 10);

        //Equivalence Class No balance
        BankTeller ba2 = new BankTeller();
        assertThrows(InsufficientFundsException.class, ()-> ba2.withdraw("a@b.com",1));
        assertEquals(0, ba2.checkBalance("a@b.com"));

        BankTeller ba3 = new BankTeller();
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw("a@b.com", 0));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw("a@b.com", 1.001));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw("a@b.com", -14));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw("a@b.com", -0.001));
    }
}
