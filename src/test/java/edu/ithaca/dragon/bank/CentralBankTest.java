package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {


    @Test
     void checkBalance() {
        CentralBank cb = new CentralBank();
        cb.createAccount("a@b.com", 305, "abcd1234");
         //checks the balance of an account in the collection
         assertEquals(305, cb.checkBalance("a@b.com"));
         //asks for the balance of an account not in the collection
         assertThrows(IllegalArgumentException.class, ()-> cb.checkBalance("ab@c.com"));
     }
     @Test
     void depositTest() {
         CentralBank cb = new CentralBank();
         cb.createAccount("a@b.com",305,"abcd1234");
         //deposits a valid amount into a valid bank account
         cb.deposit("a@b.com",50);
         assertEquals(355, cb.checkBalance("a@b.com"));
         //attempts to deposit an invalid amount
         assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",5000.608));
         //attempts to deposit 0
         assertThrows(IllegalArgumentException.class, ()-> cb.deposit("a@b.com",0));
     }
    @Test
    void confirmCredentialsTest(){
        CentralBank cB = new CentralBank();
        cB.createAccount("ppatel@ithaca.edu",500,"ITH19");
        cB.createAccount("mdad@ithaca.edu",500,"ITH20");
        cB.createAccount("kweal@ithaca.edu",500,"ITH21");

        assertTrue(cB.confirmCredentials("ppatel@ithaca.edu","ITH19"));
        assertTrue(cB.confirmCredentials("mdad@ithaca.edu","ITH20"));
        assertTrue(cB.confirmCredentials("kweal@ithaca.edu","ITH21"));

        assertFalse(cB.confirmCredentials("Beefstew@rolling.org","WER1"));
        assertFalse(cB.confirmCredentials("Cornbeef@rocks.com","DANCER23"));
        assertFalse(cB.confirmCredentials("sloppyJO@beverages.net","HollY23!"));


    }
    @Test
    void withdrawTest() throws InsufficientFundsException {
        CentralBank cb = new CentralBank();

        cb.createAccount("a@b.com", 400, "ABCD1234");

        //withdraws a valid amount with sufficient funds
        cb.withdraw("a@b.com",200);
        assertEquals(200,cb.checkBalance("a@b.com"));

        //withdraws an invalid amount with sufficient funds
        assertThrows(IllegalArgumentException.class, ()-> cb.withdraw("a@b.com",-20.9088));

        //withdraws a valid amount with insufficient funds
        assertThrows(InsufficientFundsException.class, ()-> cb.withdraw("a@b.com",560));

    }

    @Test
    void transferTest() throws InsufficientFundsException {
        CentralBank cb = new CentralBank();
        cb.createAccount("ppatel@ithaca.edu", 500, "PurpleNurple");
        cb.createAccount("prav15@cornell.edu",500, "REdBed12");

        cb.transfer("ppatel@ithaca.edu", "prav15@cornell.edu", 100);
        assertEquals(400, cb.checkBalance("ppatel@ithaca.edu"));
        assertEquals(600, cb.checkBalance("prav15@cornell.edu"));

        assertThrows(IllegalArgumentException.class, ()-> cb.transfer("ppatel@ithaca.edu", "prav15@cornell.edu", 0));

        assertThrows(InsufficientFundsException.class, ()-> cb.transfer("prav15@cornell.edu", "ppatel@ithaca.edu",700));
        assertThrows(InsufficientFundsException.class, ()-> cb.transfer("ppatel@ithaca.edu", "prav15@cornell.edu",500));
    }

    @Test
    void removeAccountTest(){
        CentralBank cb = new CentralBank();
        cb.createAccount("ppatel@ithaca.edu", 300, "PopTartK1NG");
        cb.createAccount("mark.davis12@federal.gov", 1400, "PopTartsAreAmazing");
        cb.createAccount("lolAntonioBrown@dramaqueen.cc", 1600, "Steelersarebetterwithoutyou");
        cb.createAccount("ABshouldFIGHTLoganPaul@DaZn.com", 500, "ABizweak84");
        cb.createAccount("ABisaWORSETO@nevergonnahappen.net", 30, "81isgreaterthan84");

        cb.closeAccount("ABshouldFIGHTLoganPaul@DaZn.com");
        assertTrue(cb.checkCustomerCollection("ABshouldFIGHTLoganPaul@DaZn.com"));

    }
}
