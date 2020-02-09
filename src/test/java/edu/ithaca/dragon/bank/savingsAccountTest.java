package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class savingsAccountTest {
    @Test
    void withdrawSavingsTest(double amount){
        //positive, negative, zero, test above withdraw max

        savingsAccount savings1 = new savingsAccount("a@b.com", 200.00, 150.00);

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
}
