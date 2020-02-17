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

        teller.deposit(acctId, 0); //though centsless, 0 is still a valid number
        teller.deposit(acctId, .01); // minimum amount possible
        teller.deposit(acctId, 100); // equivalence test

        assertEquals(teller.checkBalance(acctId),300.01);

        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100)); // invalid middle case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-1)); // invalid border case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,100.001)); // invalid border case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,100.00001)); // invalid middle case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100.001)); // invalid case (middle value, border decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.deposit(acctId,-100.00001)); // invalid middle case (value, decimal place limit)

        assertThrows(IllegalArgumentException.class, ()-> teller.deposit("YOLO",100)); //Checks incorrect acctId
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        CentralBank myBank = new CentralBank();
        BasicAPI atm = myBank;
        AdvancedAPI teller = myBank;
        String email = "a@b.com";
        String checking = "Checking";
        String savings = "Savings";
        teller.createAccount(email, 200,checking);

        teller.withdraw(email, checking,0); //though centsless, 0 is still a valid number
        teller.withdraw(email, checking,.01); // minimum amount possible
        teller.withdraw(email, checking,100); // equivalence test

        assertEquals(99.99,teller.checkBalance(email, checking));

        teller.createAccount(email,1000, savings);
        teller.setDailyMax(email, savings, 100);

        teller.withdraw(email,savings,.01);
        assertEquals(999.99, teller.checkBalance(email,savings));

        SavingsAccount savingsAccount = teller.findSavingsAcct(teller.getAccountId(email,savings));

        assertThrows(InsufficientFundsException.class, ()-> teller.withdraw(email, savings,100)); // valid amount but past daily max

        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,-100)); // invalid middle case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,-1)); // invalid border case (value)
        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,100.001)); // invalid border case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,100.00001)); // invalid middle case (decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,-100.001)); // invalid case (middle value, border decimal place limit)
        assertThrows(IllegalArgumentException.class, ()-> teller.withdraw(email, savings,-100.00001)); // invalid middle case (value, decimal place limit)

        assertThrows(IllegalArgumentException.class, ()-> teller.deposit("YOLO",100)); //Checks incorrect acctId
    }

    @Test
    /**
     * This tests the integration of all 3 classes implemented by CentralBank
     * It covers multiple functions which relate to each other and rely on their shared success
     */
    void integrationTest1(){
        CentralBank theBank = new CentralBank();
        AdvancedAPI teller = theBank;
        BasicAPI atm = theBank;
        AdminAPI bossMan = theBank;

        teller.createAccount("a@b.com", 2, "Checking");
        teller.createAccount("a@b.com", 2, "Savings");

        atm.deposit(teller.getAccountId("a@b.com", "Checking"), 48);
        assertEquals(50, teller.checkBalance(atm.getAccountId("a@b.com", "Checking")));

        atm.deposit(atm.getAccountId("a@b.com", "Savings"), 98);
        assertEquals(100, atm.checkBalance(atm.getAccountId("a@b.com", "Savings")));

        assertEquals(150, bossMan.calcTotalAssets());
    }

    @Test
    void calcTotalAssetsTest(){
        CentralBank theBank = new CentralBank();
        AdvancedAPI teller = theBank;
        AdminAPI bossMan = theBank;

        assertEquals(0.0, bossMan.calcTotalAssets()); //No accounts, no money

        String[] emails = new String[]{"a@b.com", "a@b.com", "e@f.com", "g@h.com"};
        int[] balances = new int[]{200,200,200,200};
        String[] acctTypes = new String[]{"Checking","Savings"};

        for(int i=0; i< emails.length; i++) {
            teller.createAccount(emails[i], balances[i], acctTypes[i%2]);
            assertEquals((i+1)*200, bossMan.calcTotalAssets()); //200 per account
        }
    }
}
