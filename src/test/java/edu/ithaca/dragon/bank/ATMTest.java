package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ATMTest {

    @Test
    void confirmCredentialsTest(){
        User christian = new User("christian","martano");
        CentralBank mybank = new CentralBank();
        mybank.users.add(christian);
        ATM atm = new ATM(mybank);

        //correct credentials
        assertEquals(true,atm.confirmCredentials("christian","martano"));

        //incorrect credetials
        assertEquals(false,atm.confirmCredentials("abc","123"));

        //correct username incorrect password
        assertEquals(false,atm.confirmCredentials("christian","123"));

        //incorrect username correct password
        assertEquals(false,atm.confirmCredentials("CCC","martano"));



    }
}
