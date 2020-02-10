package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


 class CentralBankTest {

     @Test
     void centralBankConstuctor(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com");
         assertEquals(1,c1.accountCount());

         //create multiple accounts
         //CE: Multiple account test can also go in the add/delete account tests
         c1.createAccount("b00", 200, "pam@gmail.com");
         c1.createAccount("b13", 200, "kelly@gmail.com");
         assertEquals(3,c1.accountCount());


     }

     //Admin Tests


     @Test
     void freezeAccountTest(){


     }

     @Test
     void unfreezeAccountTest(){

         //unfreeze account
     }

     @Test
     void findAccountStringTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com");
         assertEquals("b123", c1.findAccountWithId("b123").getAcctId());


     }

     @Test
     void suspActTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com");

         //sus activity should be true for singular account
         BankAccount b1 = c1.findAccountWithId("b123");
         assertFalse(b1.getSusAct());

         b1.setSusAct(true);
         assertTrue(b1.getSusAct());

         //appear on sus activity collection; list.
         assertNotNull(c1.findAcctIdsWithSuspiciousActivity());
         assertEquals(1, c1.findAcctIdsWithSuspiciousActivity().size());
     }


     @Test
      void calcTotalAssetsTest(){
         //total assets test


     }





}
