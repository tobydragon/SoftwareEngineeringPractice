package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {

    @Test
    void checkBalanceTest() throws NonExistentAccountException{
        UserArrayList userAccounts = new UserArrayList();
        CentralBank bank = new CentralBank();
        UserAccount testAccount = new UserAccount("username","password","a@b.com",123);
        userAccounts.addAccount(testAccount);
        ATM atm = new ATM(userAccounts,bank); //TODO redo these tests later
        assertEquals(1, atm.checkBalance(123));
    }

    @Test
    void confirmCredentialsTest(){



    }

}
