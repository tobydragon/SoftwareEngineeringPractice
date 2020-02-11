package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void checkBalanceTest(){
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", 200,"Checking");
        String acctId = teller.getAccountId("a@b.com", "Checking");

        assertEquals(200, teller.checkBalance(acctId));
    }

    @Test
    void getAccountIdTest(){
        AdvancedAPI teller = new CentralBank();

        String[] emails = new String[]{"a@b.com", "a@b.com", "e@f.com", "g@h.com"};
        int[] balances = new int[]{100,200,300,400};
        String[] expectedIds = new String[]{"1C", "2S", "3C", "4S"};
        String[] acctTypes = new String[]{"Checking","Savings"};

        for(int i=0; i< emails.length; i++) {
            teller.createAccount(emails[i], balances[i], acctTypes[i%2]);
            assertEquals(expectedIds[i], teller.getAccountId(emails[i],acctTypes[i%2]));
        }
    }


    @Test
    void createAccountTest(){
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", 200,"Checking");
        assertEquals(200, teller.checkBalance(teller.getAccountId("a@b.com", "Checking")));
    }

    @Test
    void depositTest(){
        AdvancedAPI teller = new CentralBank();
        teller.createAccount("a@b.com", 200,"Checking");
        String acctId = teller.getAccountId("a@b.com","Checking");

        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100)); // invalid middle case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-1)); // invalid border case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,100.001)); // invalid border case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,100.00001)); // invalid middle case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100.001)); // invalid case (middle value, border decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100.00001)); // invalid middle case (value, decimal place limit)

        assertThrows(IllegalArgumentException.class, ()-> teller.deposit("YOLO",100)); //Checks incorrect acctId


    }
}
