package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());

        //Josue's getBalanceTest
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0); //Equivalence case of 0 dollars (nothing) in bankaccount case
        BankAccount bankAccount3= new BankAccount("a@b.com", 50); //Equivalence case of 50 dollars (small amount) in bankaccount case
        BankAccount bankAccount4 = new BankAccount("a@b.com", 10000); //Equivalence case of 10000 dollars (large amount) in bankaccount case
        BankAccount bankAccount5 = new BankAccount("a@b.com", -10); //Equivalence case of -10 dollars (negative) in bankaccount case
        BankAccount bankAccount6 = new BankAccount("a@b.com", -150); //Equivalence case of -150 dollars (negative) in bankaccount case
        BankAccount bankAccount7 = new BankAccount("a@b.com", -5000); //Equivalence case of -5000 dollars (negative) in bankaccount case
        BankAccount bankAccount8 = new BankAccount("a@b.com", 67.86); //Equivalence case of 67.86 dollars (float) in bankaccount case

        assertEquals(0, bankAccount2.getBalance());
        assertEquals(50, bankAccount3.getBalance());
        assertEquals(10000, bankAccount4.getBalance());
        assertEquals(-10, bankAccount5.getBalance());
        assertEquals(-150, bankAccount6.getBalance());
        assertEquals(-5000, bankAccount7.getBalance());
        assertEquals(67.86, bankAccount8.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("j@h.com", 367.54); //Valid withraw
        bankAccount2.withdraw(100);
        assertEquals(267.54, bankAccount2.getBalance());
        BankAccount bankAccount3 = new BankAccount("j@h.com", 367.54); //Valid withdraw with edgecase
        bankAccount3.withdraw(367.54);
        assertEquals(0, bankAccount3.getBalance());
        BankAccount bankAccount4 = new BankAccount("j@h.com", 367.54); //Withdrawing larger than balance
        bankAccount4.withdraw(400);
        assertEquals(367.54, bankAccount4.getBalance());
        BankAccount bankAccount5 = new BankAccount("j@h.com", 367.54); //Withdrawing very large amount, over balance
        bankAccount5.withdraw(6000);
        assertEquals(367.54, bankAccount5.getBalance());

        BankAccount bankAccount6 = new BankAccount("j@h.com", -367.54); //Withdrawing non-existing number, negatives automatically change to 0
        bankAccount6.withdraw(400);
        assertEquals(0, bankAccount6.getBalance());
        BankAccount bankAccount7 = new BankAccount("j@h.com", -367.54); //Withdrawing non-existing number, negatives automatically change to 0
        bankAccount7.withdraw(40000);
        assertEquals(0, bankAccount7.getBalance());
    }

    @Test
    void isEmailValidTest(){
        //assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //assertFalse( BankAccount.isEmailValid(""));

        //Josue's invalid email prefix tests
        assertFalse((BankAccount.isEmailValid("a..@b.com")));
        assertFalse((BankAccount.isEmailValid("..a@b.com")));
        assertFalse((BankAccount.isEmailValid("#a@b.com")));
        assertFalse((BankAccount.isEmailValid("-a@b.cc")));
        assertFalse((BankAccount.isEmailValid("TomHanksIsCool-@b.com"))); //equivalence case is - next to the @ edge case
        assertFalse((BankAccount.isEmailValid("Maud..Vile@b.com"))); //equivalence case is .. in the prefix edge case
        assertFalse((BankAccount.isEmailValid("Yung#Muney@b.com"))); //equivalence case is # in the prefix edge case
        assertFalse((BankAccount.isEmailValid(".TableScraps@b.com")));  //equivalence case is prefix starting with . edge case

        //Josue's invalid email domain tests
        assertFalse((BankAccount.isEmailValid("TomHanksIsCold@b#c.com"))); //equivalence case # in domain edge case
        assertFalse((BankAccount.isEmailValid("KevinLevin@b"))); //equivalence case no extension to the domain edge case
        assertFalse((BankAccount.isEmailValid("JetSetRadio@b..com"))); //equivalence case .. in the domain edge case
        assertFalse((BankAccount.isEmailValid("Jajaja@b.SpanishLaugh.com"))); //equivalence case two . in the domain edge case

        //there is no equivalence case for there only being one letter in the extension. edge case
        assertFalse((BankAccount.isEmailValid("Jajaja@b.c")));

        //there is no equivalence case for any valid emails.
        //Josue's True email tests
        assertTrue(BankAccount.isEmailValid( "bagles@b.com"));
        assertTrue(BankAccount.isEmailValid( "animale.farm@b.org"));
        assertTrue(BankAccount.isEmailValid( "bagles@fircracker.cc"));

        //New tests 1-3
        assertFalse((BankAccount.isEmailValid("a@b-g.com"))); //equivalence case - in domain edge case
        assertFalse((BankAccount.isEmailValid("a#@g.cc"))); //equivalence case # in domain edge case
        assertFalse((BankAccount.isEmailValid("..yt@g.cc"))); //equivalence case .. in domain edge case
        assertFalse((BankAccount.isEmailValid("h@g.c"))); //equivalence case single letter extension domain edge case
        assertFalse((BankAccount.isEmailValid("@g.cc"))); //equivalence case no address domain edge case
        assertFalse((BankAccount.isEmailValid("a@.cc"))); //equivalence case no domain in domain edge case
        assertFalse((BankAccount.isEmailValid("a@g."))); //equivalence case no extension to the domain edge case
        assertFalse((BankAccount.isEmailValid("a@a@b@g.com")));
        assertFalse((BankAccount.isEmailValid("a@b.com..")));
    }

    @Test
    void isAmountValidTest(){
        //True statements
        assertTrue(BankAccount.isAmountValid(50)); //equivalence case valid with 50 and nothing above decimal points
        assertTrue(BankAccount.isAmountValid(1.5)); //equivalence case valid with 1.5 and one decimal point
        assertTrue(BankAccount.isAmountValid(0.01)); //equivalence case valid edgecase with 0.01 being the least number before being 0 or 0.001
        assertTrue(BankAccount.isAmountValid(1000.20)); //equivalence case valid with 1000.20 with a large number and 2/1 decimal points
        assertTrue(BankAccount.isAmountValid(50.00)); //equivalence case valid with 50 and 2/none above decimal points
        assertTrue(BankAccount.isAmountValid(100000.00)); //equivalence case valid with a large number and 2/none above decimal points

        //False Statements
        assertFalse(BankAccount.isAmountValid(50.001)); //equivalence case is an extra decimal point
        assertFalse(BankAccount.isAmountValid(-0.1)); //equivalence case is under 0/is negative
        assertFalse(BankAccount.isAmountValid(0.0000000000034)); //equivalence case has more than 2 decimal points
        assertFalse(BankAccount.isAmountValid(-.022)); //equivalence case is negative and more than 2 decimal points

    }

    @Test
    void depositTest(){
        //Basic test
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(100.);
        assertEquals(300, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@b.com", 400);
        bankAccount2.deposit(200);
        assertEquals(600, bankAccount2.getBalance());

        //Negative balance
        BankAccount bankAccount3 = new BankAccount("a@b.com", -1000);
        bankAccount3.deposit(200);
        assertEquals(200, bankAccount3.getBalance());

        BankAccount bankAccount4 = new BankAccount("a@b.com", -50);
        bankAccount4.deposit(50);
        assertEquals(50, bankAccount4.getBalance());

        //Negative deposit amount
        BankAccount bankAccount5 = new BankAccount("a@b.com", 200);
        bankAccount5.deposit(-600);
        assertEquals(200, bankAccount5.getBalance());

        BankAccount bankAccount6 = new BankAccount("a@b.com", 100000);
        bankAccount6.deposit(-10000);
        assertEquals(100000, bankAccount6.getBalance());

        //Multiple deposits with doubles
        BankAccount bankAccount7 = new BankAccount("a@b.com", 400);
        bankAccount7.deposit(65.93);
        assertEquals(600, bankAccount7.getBalance());
        bankAccount7.deposit(23.93);
        assertEquals(600, bankAccount7.getBalance());

        BankAccount bankAccount8 = new BankAccount("a@b.com", 400);
        bankAccount8.deposit(16.75);
        assertEquals(600, bankAccount8.getBalance());
        bankAccount8.deposit(3.40);
        assertEquals(600, bankAccount8.getBalance());
    }

    @Test
    void transferTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankAccount2 = new BankAccount("a@b.com", 500);
        BankAccount.transfer(bankAccount, bankAccount2, 50.00);
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }
}