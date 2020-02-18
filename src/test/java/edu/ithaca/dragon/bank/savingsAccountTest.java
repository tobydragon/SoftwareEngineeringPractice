package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class savingsAccountTest {
    @Test
    void withdrawSavingsTest()throws IllegalAccessException {

        //positive, negative, zero, test above withdraw max

        savingsAccount savings1 = new savingsAccount("a@b.com", "B000", "000000", 200.00, 150.00, .15);

        //withdraw more than limit
        assertThrows(IllegalArgumentException.class, ()->savings1.withdraw(200.00));

        //withdraw 0
        assertThrows(IllegalArgumentException.class, ()->savings1.withdraw(0.00));

        //withdraw negative
        assertThrows(IllegalArgumentException.class, ()->savings1.withdraw(-200.00)); //out of limit
        assertThrows(IllegalArgumentException.class, ()->savings1.withdraw(-50.00)); //within limit

        //withdraw positive
        savings1.withdraw(150.00);
        assertTrue(savings1.getBalance()-50.00 < .1);
        savings1.withdraw(1.00);
        assertTrue(savings1.getBalance() - 49.00 < .1);

    }

    @Test
    void calcSavingsInterestTest()throws IllegalAccessException {

        //postive, negative, zero interest rates

        //positive interest rate
        savingsAccount savingsAccount2 = new savingsAccount("a@b.com","B000", "000000",  200.00, 150.00, .15);
        savingsAccount2.calcInterest();
        assertTrue(savingsAccount2.getBalance() - 230 < .1);

        savingsAccount savingsAccount3 = new savingsAccount("a@b.com", "B000", "000000", 200.00, 150.00, .30);
        savingsAccount3.calcInterest();
        assertTrue(savingsAccount3.getBalance() - 260 < .1);

        //zero interest rate
        savingsAccount savingsAccount4 = new savingsAccount("a@b.com", "B000", "000000", 200.00, 150.00, 0.0);
        savingsAccount4.calcInterest();
        assertTrue(savingsAccount4.getBalance() - 200 < .1);

        //negative interest rate
        savingsAccount savingsAccount5 = new savingsAccount("a@b.com", "B000", "000000", 200.00, 150.00, -.15);
        assertThrows(IllegalArgumentException.class, ()->savingsAccount5.calcInterest());
        savingsAccount savingsAccount6 = new savingsAccount("a@b.com","B000", "000000",  200.00, 150.00, -.30);
        assertThrows(IllegalArgumentException.class, ()->savingsAccount6.calcInterest());



    }
}
