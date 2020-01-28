package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance()); //check get balance == starting balance
        bankAccount.withdraw(50);
        assertEquals(150,bankAccount.getBalance()); //check balance after withdraw
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(200)); //check if throws exception for invalid withfraw
        assertEquals(150, bankAccount.getBalance()); //check if balance remains same
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-200));
        assertEquals(150, bankAccount.getBalance()); //check for withdraw negative amount
        bankAccount.withdraw(0);
        assertEquals(150, bankAccount.getBalance()); //balance after withdraw 0
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance()); // check if withdraw decreased in account
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300)); //check if withdraw amount greater than amount in bank account/ test exception
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance()); //check if amount of total withdraw equal to balance
        bankAccount.withdraw(0);
        assertEquals(0, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100)); //check if withdraw negative number
        assertEquals(0, bankAccount.getBalance()); //shouldn't equal 100 and remain at balance it was before
        assertThrows(IllegalArgumentException.class, () ->bankAccount.withdraw(10.103234));
        assertEquals(0, bankAccount.getBalance()); //should still remain 0
    }

    @Test
    void isEmailValidTest(){
        //Email should be true if starts with character or number, includes @ and ends with .'character'
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
        assertTrue(BankAccount.isEmailValid("23s@b.com"));
        assertTrue(BankAccount.isEmailValid("a1@b.com"));
        assertFalse(BankAccount.isEmailValid("@n.com"));
        assertFalse(BankAccount.isEmailValid("n.com"));
        assertFalse(BankAccount.isEmailValid("n!!@Gcom"));
        assertTrue(BankAccount.isEmailValid("n@gmail.com"));
        assertTrue(BankAccount.isEmailValid("n@gyi.org"));
        assertFalse(BankAccount.isEmailValid(".n@Gcom"));
        assertFalse(BankAccount.isEmailValid(".#Gcom"));
        assertFalse(BankAccount.isEmailValid("n$@@G$$com"));
        assertTrue(BankAccount.isEmailValid("n12.2@gyi.g"));
    }

    @Test
    void isAmountValidTest(){

        /*Equivalence tests would : test negative number no decimals, test positive number no decimals, test negative float, test positive float
        test float with more than two decimals positive and negative, test float with one decimal positive and negative, test 0 */

        assertTrue(BankAccount.isAmountValid(10)); //positive int
        assertTrue(BankAccount.isAmountValid(0.01)); //positive float
        assertTrue(BankAccount.isAmountValid(.01)); //positive float without leading number
        assertTrue(BankAccount.isAmountValid(1.1)); //positive float with one decimal
        assertTrue(BankAccount.isAmountValid(0)); //testing 0


        assertFalse(BankAccount.isAmountValid(-10)); //negative int
        assertFalse(BankAccount.isAmountValid(-0.01)); //negative float
        assertFalse(BankAccount.isAmountValid(-.01)); //negative float without leading number
        assertFalse(BankAccount.isAmountValid(0.009)); //positive float with more than 2 decimals
        assertFalse(BankAccount.isAmountValid(-0.00242423)); //negative float with more than 2 decimals

    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100)); //illegal arguement negative withdraw

        BankAccount bankAccount2 = new BankAccount("a@be.com", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(.00012));
        assertThrows(InsufficientFundsException.class, () ->bankAccount2.withdraw(200.01)); //throw insufficient funds exception
        assertThrows(IllegalArgumentException.class, () ->bankAccount2.withdraw(200.0001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a32@gt.com", -100)); //invalid starting negative throw illegal argument
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a32@gt.com", 100.5345)); //invalid starting float throw illegal argument


    }

    @Test
    void depositTest(){
        BankAccount bankAccount = new BankAccount("a@bana.com", 100);

        //Test valid amount to deposit (throws exceptions), test valid amounts deposit to increase the account amount


        bankAccount.deposit(.01);
        assertEquals(100.01, bankAccount.getBalance());
        bankAccount.deposit(1.01);
        assertEquals(101.02, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, () ->bankAccount.deposit(0.034234));
        assertThrows(IllegalArgumentException.class, () ->bankAccount.deposit(-130));
        bankAccount.deposit(1000);
        assertEquals(1101.02, bankAccount.getBalance());






    }

}