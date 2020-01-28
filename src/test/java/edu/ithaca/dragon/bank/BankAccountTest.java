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
    void withdrawTest(){
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
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}