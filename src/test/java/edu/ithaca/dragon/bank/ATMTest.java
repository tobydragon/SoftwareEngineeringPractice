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

        //correct credentialss
        assertEquals(true,atm.confirmCredentials("christian","martano"));

        //incorrect credetials
        assertEquals(false,atm.confirmCredentials("abc","123"));

        //correct username incorrect password
        assertEquals(false,atm.confirmCredentials("christian","123"));

        //incorrect username correct password
        assertEquals(false,atm.confirmCredentials("CCC","martano"));



    }

    @Test
    void checkbalanceTest(){
        BankAccount christian = new CheckingAccount(1000, "christian");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christian);
        ATM atm = new ATM(myBank);
        //basic check balance
        assertEquals(1000,atm.checkBalance("christian"));
        //invalid account id
        assertThrows(IllegalArgumentException.class, () -> atm.checkBalance("Chistian"));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException {
        BankAccount christian = new CheckingAccount(1000, "christian");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christian);
        ATM atm = new ATM(myBank);
        //greater than balance
        assertThrows(InsufficientFundsException.class, ()->atm.withdraw("christian",1001));

        //less than balance
        atm.withdraw("christian",50);
        assertEquals(950, christian.getBalance());


        //equal to balance
        atm.withdraw("christian",950);
        assertEquals(0, christian.getBalance());


        //negative - argument thrown
        assertThrows(IllegalArgumentException.class,() -> atm.withdraw("christian",-1));


        //invalid input
        atm.deposit("christian",1000);
        assertThrows(IllegalArgumentException.class,() -> atm.withdraw("christian",100.0005));
    }

    @Test
    void depositTest() throws InsufficientFundsException, IllegalArgumentException {
        BankAccount christian = new CheckingAccount(1000, "christian");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(christian);
        ATM atm = new ATM(myBank);

        //regular deposit
        atm.deposit("christian",50);
        assertEquals(1050, christian.getBalance());


        //negative - argument thrown
        assertThrows(IllegalArgumentException.class,() -> atm.deposit("christian",-1));


        //invalid input
        atm.deposit("christian",1000);
        assertThrows(IllegalArgumentException.class,() -> atm.deposit("christian",100.0005));
    }


}
