package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {

    @Test
    void withdrawTest() throws InsufficientFundsException{
        CheckingAccount checkBankAccount = new CheckingAccount("a@b.com", 200);
        checkBankAccount.withdraw(100);
        assertEquals(100, checkBankAccount.getBalance());

        //Equivalence Class Has balance
        CheckingAccount checkAccount1 = new CheckingAccount("a@c.com", 300);
        checkAccount1.withdraw(99);//valid withdraw
        assertEquals(201, checkAccount1.getBalance());

        checkAccount1.withdraw(1);//valid withdraw edgecase
        assertEquals(200, checkAccount1.getBalance());
        checkAccount1.withdraw(0.1);
        assertEquals(199.9, checkAccount1.getBalance(),10);
        checkAccount1.withdraw(0.01);
        assertEquals(199.89, checkAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> checkAccount1.withdraw(201));
        assertEquals(199.89, checkAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> checkAccount1.withdraw(20100));
        assertEquals(199.89, checkAccount1.getBalance(), 10);
        checkAccount1.withdraw(199.89);//perfect withdraw edgecase
        assertEquals(0, checkAccount1.getBalance(), 10);

        //Equivalence Class No balance
        CheckingAccount checkingAccount2 = new CheckingAccount("a@c.cm", 0);
        assertThrows(InsufficientFundsException.class, ()-> checkingAccount2.withdraw(1));
        assertEquals(0, checkingAccount2.getBalance());

        CheckingAccount checkingAccount3 = new CheckingAccount("a@c.cm", 200);
        assertThrows(IllegalArgumentException.class, ()-> checkingAccount3.withdraw(0));
        assertThrows(IllegalArgumentException.class, ()-> checkingAccount3.withdraw(1.001));
        assertThrows(IllegalArgumentException.class, ()-> checkingAccount3.withdraw(-14));
        assertThrows(IllegalArgumentException.class, ()-> checkingAccount3.withdraw(-0.001));
    }
}