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

    @Test
    void transferTest() throws IllegalArgumentException, InsufficientFundsException{
        BankAccount sender = new CheckingAccount(500, "christian");
        BankAccount reciever = new SavingsAccount(0, "martano");
        CentralBank myBank = new CentralBank();
        myBank.accounts.add(sender);
        myBank.accounts.add(reciever);
        ATM atm = new ATM(myBank);
        //successfull transfer
        atm.transfer("christian","martano",100);
        assertEquals(400, sender.getBalance());
        assertEquals(100, reciever.getBalance());

        //transfer with change
        atm.transfer("christian","martano", 1.50);
        assertEquals(398.50, sender.getBalance());
        assertEquals(101.50, reciever.getBalance());

        //0 dollars transfered
        atm.transfer("christian","martano", 0);
        assertEquals(398.50, sender.getBalance());
        assertEquals(101.50, reciever.getBalance());

        //negative amount trying to be transfered
        assertThrows(IllegalArgumentException.class, ()->atm.transfer("christian","martano", -1));

        //invalid input check
        assertThrows(IllegalArgumentException.class, ()-> atm.transfer("christian","martano", 100.0005));

        //trying to transfer insufficent funds
        assertThrows(InsufficientFundsException.class, ()->atm.transfer("christian","martano", 500));

        //checks to be sure illegal argument is thrown rather than insufficient funds when both are valid
        assertThrows(IllegalArgumentException.class, ()->atm.transfer("christian","martano", 500.0005));

        //invalid email
        assertThrows(IllegalArgumentException.class, ()->atm.transfer("christiann","martano", 500));
    }



}
