package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void withdrawTest() throws InsufficientFundsException {

        CheckingAccount account1 = new CheckingAccount("a@b.com", 200);
        account1.withdraw(100);

        assertEquals(100, account1.getBalance());


        //Equivalence Class-InsufficientFundsException
        assertThrows(IllegalArgumentException.class, ()-> account1.withdraw(0));

        assertThrows(InsufficientFundsException.class, () ->  account1.withdraw(101));


        //Equivalence Class-IllegalArgumentException more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () ->  account1.withdraw(1045.078));
        //Equivalence Class-IllegalArgumentException more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () ->  account1.withdraw(1.02839));


        //Equivalence Class-Invalid withdraw amount 0
        assertThrows(IllegalArgumentException.class, () ->  account1.withdraw(0));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () ->  account1.withdraw(-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () ->  account1.withdraw(-10));
        //Equivalence Class-IllegalArgumentException exceeds balance
        assertThrows(InsufficientFundsException.class, () ->  account1.withdraw(1000));


        //Equivalence Class- withdraw $1
        account1.withdraw(1);
        assertEquals(99,  account1.getBalance());

        //Equivalence Class- withdraw $.1
        account1.withdraw(.1);
        assertEquals(98.9,  account1.getBalance());

        //Equivalence Class- withdraw $.01
        account1.withdraw(.01);
        assertEquals(98.89,  account1.getBalance());

        //Equivalence Class No balance
        CheckingAccount account2  = new CheckingAccount("a@c.cm", 0);
        assertThrows(InsufficientFundsException.class, ()-> account2.withdraw(1));
        assertEquals(0, account2.getBalance());
    }
}
