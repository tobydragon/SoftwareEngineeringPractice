package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


 class CentralBankTest {

     @Test
     void centralBankConstuctor(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com","centralBankConstuctorTest1");
         assertEquals(1,c1.accountCount());

         //create multiple accounts
         //CE: Multiple account test can also go in the add/delete account tests
         c1.createAccount("b00", 200, "pam@gmail.com","centralBankConstuctorTest2");
         c1.createAccount("b13", 200, "kelly@gmail.com","centralBankConstuctorTest3");
         assertEquals(3,c1.accountCount());



     }

     //Admin Tests

     @Test

     void overAllBalanceTest(){
         //formerly calcTotalAssetsTest()
         CentralBank c1 = new CentralBank();
         assertEquals(0, c1.calcTotalAssets());
         c1.createAccount("b123", 200, "jim@gmail.com","overAllBalanceTest1");
         c1.createAccount("b00", 200, "pam@gmail.com","overAllBalanceTest2");
         c1.createAccount("b13", 200, "kelly@gmail.com","overAllBalanceTest3");

         assertEquals(600, c1.calcTotalAssets());

     }


     @Test
     void freezeAccountTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("BA1234", 10000, "candace@gmail.com","freezeAccountTest1");
         BankAccount b1 = c1.findAccountWithId("BA1234");
         assertFalse(b1.getAcctFrozen());

         c1.freezeAccount("BA1234");
         assertTrue(b1.getAcctFrozen());
         assertThrows(IllegalAccessException.class,()-> b1.deposit(200));
         assertThrows(IllegalAccessException.class,()-> b1.withdraw(200));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.freezeAccount("34"));

     }

     @Test
     void unfreezeAccountTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("BA1234", 10000, "candace@gmail.com","UNfreezeAccountTest1");
         BankAccount b1 = c1.findAccountWithId("BA1234");
         assertFalse(b1.getAcctFrozen());

         c1.freezeAccount("BA1234");
         assertTrue(b1.getAcctFrozen());

         c1.unfreezeAcct("BA1234");
         assertFalse(b1.getAcctFrozen());

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.unfreezeAcct("34"));

     }

     @Test
     void findAccountStringTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com","findAccountStringTest1");
         assertEquals("b123", c1.findAccountWithId("b123").getAcctId());


     }

     @Test
     void suspActTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com","suspActTest");

         //sus activity should be true for singular account
         BankAccount b1 = c1.findAccountWithId("b123");
         assertFalse(b1.getSusAct());

         b1.setSusAct(true);
         assertTrue(b1.getSusAct());

         //appear on sus activity collection; list.
         assertNotNull(c1.findAcctIdsWithSuspiciousActivity());
         assertEquals(1, c1.findAcctIdsWithSuspiciousActivity().size());
     }





     //Basic Tests

     @Test
     void confirmCredentialsTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","confirmCredentialsTest");
         assertFalse(c1.confirmCredentials("b123","123"));
         assertTrue(c1.confirmCredentials("b123","confirmCredentialsTest"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.confirmCredentials("123","confirmCredentialsTest"));

     }

     @Test
     void checkBalanceTest(){
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","checkBalanceTest");
         assertEquals(200,c1.checkBalance("b123"));

         CentralBank c2 = new CentralBank();
         c1.createAccount("b100",1,"123@ithaca.com","checkBalanceTest2");
         assertEquals(1,c1.checkBalance("b100"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.checkBalance("b1"));
     }

     @Test
     void withdrawTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","withdrawTest");
         c1.withdraw("b123",100);
         assertEquals(100,c1.checkBalance("b123"));
         c1.withdraw("b123",50.55);
         assertEquals(49.45,c1.checkBalance("b123"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.withdraw("123",100));

     }


     @Test
     void depositTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","depositTest");
         c1.deposit("b123",100);
         assertEquals(300,c1.checkBalance("b123"));
         c1.deposit("b123",50.55);
         assertEquals(350.55,c1.checkBalance("b123"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.deposit("b12",300));


     }

     @Test
     void transferTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","depositTest");
         c1.createAccount("c321",150,"12@gmail.com","depositTest1");

         c1.transfer("b123","c321",100);
         assertEquals(100,c1.checkBalance("b123"));
         assertEquals(250,c1.checkBalance("c321"));

         c1.transfer("c321", "b123",10.10);
         assertEquals(110.1,c1.checkBalance("b123"));
         assertEquals(239.9,c1.checkBalance("c321"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.deposit("b12",300));
         assertThrows(IllegalArgumentException.class,()-> c1.deposit("123",300));




     }
     @Test
     void transactionHistoryTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123",200,"1234@gmail.com","depositTest");
         c1.createAccount("c321",150,"12@gmail.com","depositTest1");

         c1.withdraw("b123",10);
         c1.deposit("b123",100);
         c1.checkBalance("b123");
         c1.transfer("b123","c321",11);
         assertEquals("Withdraw: 10.0\nDeposit: 100.0\nCheck balance: 290.0\nTransfer: 11.0 to c321\nWithdraw: 11.0",c1.transactionHistory("b123"));

         //ID not found
         assertThrows(IllegalArgumentException.class,()-> c1.deposit("b12",300));
         assertThrows(IllegalArgumentException.class,()-> c1.deposit("123",300));
     }

     @Test
     void closeAccountTest() throws IllegalAccessException {
         CentralBank c1 = new CentralBank();
         c1.createAccount("b123", 200, "jim@gmail.com","centralBankConstuctorTest1");
         c1.createAccount("b00", 200, "pam@gmail.com","centralBankConstuctorTest2");
         c1.createAccount("b13", 200, "kelly@gmail.com","centralBankConstuctorTest3");
         assertEquals(3,c1.accountCount());


         //Account Balance !0
         assertThrows(IllegalArgumentException.class, ()-> c1.closeAccount("b123"));
         assertEquals(3,c1.accountCount());

         //No Account ID
         assertThrows(IllegalArgumentException.class, ()-> c1.closeAccount("falseID"));
         assertEquals(3,c1.accountCount());

         //Valid close -- Set balance to zero and close
         BankAccount b1 = c1.findAccountWithId("b123");
         b1.withdraw(200);

         BankAccount b2 = c1.findAccountWithId("b00");
         b2.withdraw(200);

         BankAccount b3 = c1.findAccountWithId("b13");
         b3.withdraw(200);


         c1.closeAccount("b00");
         c1.closeAccount("b13");
         c1.closeAccount("b123");
         assertEquals(0,c1.accountCount());


     }


}
