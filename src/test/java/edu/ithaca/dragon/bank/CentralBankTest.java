
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void createAccountTest() {
        CentralBank centralBank1 = new CentralBank("Keybank", null);
        centralBank1.createAccount("001", 500);

        //Check for correct creation of account
        assertEquals(centralBank1.checkBalance("001"), 500);

        //check for exception thrown correctly. All test cases for negatives and decimal places not required since isAmountValid()
        //already does so. Also, it wasn't specified that ID's only had to be numbers (can change if need be).
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("", 200));

        //check for ID already exists
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("001", 200));

        //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("123", 75.899));

        //negative number
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("678", -450));


        //negative number three decimals
        assertThrows(IllegalArgumentException.class, ()-> centralBank1.createAccount("491", -500.671));
    }

}