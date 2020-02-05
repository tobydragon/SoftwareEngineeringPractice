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
        CheckingAccount bankAccount1 = new CheckingAccount("a@c.com", 300);
        bankAccount1.withdraw(99);//valid withdraw
        assertEquals(201, bankAccount1.getBalance());

        bankAccount1.withdraw(1);//valid withdraw edgecase
        assertEquals(200, bankAccount1.getBalance());
        bankAccount1.withdraw(0.1);
        assertEquals(199.9, bankAccount1.getBalance(),10);
        bankAccount1.withdraw(0.01);
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(201));
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(20100));
        assertEquals(199.89, bankAccount1.getBalance(), 10);
        bankAccount1.withdraw(199.89);//perfect withdraw edgecase
        assertEquals(0, bankAccount1.getBalance(), 10);

        //Equivalence Class No balance
        CheckingAccount ba2 = new CheckingAccount("a@c.cm", 0);
        assertThrows(InsufficientFundsException.class, ()-> ba2.withdraw(1));
        assertEquals(0, ba2.getBalance());

        CheckingAccount ba3 = new CheckingAccount("a@c.cm", 200);
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(0));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(1.001));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(-14));
        assertThrows(IllegalArgumentException.class, ()-> ba3.withdraw(-0.001));
    }
}
