package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);//middle balance equivalence class
        assertEquals(200, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@h.com", 5);//low balance equivalence class
        assertEquals(5, bankAccount2.getBalance());

        BankAccount bankAccount3 = new BankAccount("e@h.com", 1000);//high balance equivalence class
        assertEquals(1000, bankAccount3.getBalance());

        BankAccount bankAccount4 = new BankAccount("g@u.com", 0);// 0 balance equivalence class
        assertEquals(0, bankAccount4.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@f.com", 200);

        bankAccount.withdraw(400);
        assertEquals(200, bankAccount.getBalance());//equivalence for amount greater than balance

        bankAccount.withdraw(-100);
        assertEquals(200, bankAccount.getBalance()); //equivalence for negative amount

        bankAccount.withdraw(50);
        assertEquals(150, bankAccount.getBalance());//equivalence for double digit amount

        BankAccount bankAccount2 = new BankAccount("a@h.com", 300);
        bankAccount2.withdraw(301);
        assertEquals(300, bankAccount2.getBalance());//border for amount greater than balance

        bankAccount2.withdraw(-1);
        assertEquals(300, bankAccount2.getBalance()); //border for negative amount

        bankAccount2.withdraw(150);
        assertEquals(150, bankAccount2.getBalance());//equivalence for triple digit (middle) amount

        bankAccount2.withdraw(0);
        assertEquals(150, bankAccount2.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isEmailValidCorrectTest(){
        assertTrue(BankAccount.isEmailValid("correct@test.com")); //Equivalence for simple correct email address
        assertFalse(BankAccount.isEmailValid("incorrect@test"));

        //Email with/without "#" character
        assertFalse(BankAccount.isEmailValid("incor#rect@test.com")); //border
        assertFalse(BankAccount.isEmailValid("incorrect@te#st.com"));//border

        //Email with "-, ., _" prefixes
        assertTrue(BankAccount.isEmailValid("cor-rect@test.com")); //Equivalence for correctly used "-" in email
        assertTrue(BankAccount.isEmailValid("cor_rect@test.com")); //Equivalence for correctly used "_" in email
        assertTrue(BankAccount.isEmailValid("cor.rect@test.com")); //Equivalence for correctly used "." in email
        assertFalse(BankAccount.isEmailValid("incorrect-@test.com")); //Equivalence for "-" with no letter/number following
        assertFalse(BankAccount.isEmailValid("incorrect_@test.com"));//Equivalence for "_" with no letter/number following
        assertFalse(BankAccount.isEmailValid("incorrect.@test.com"));//Equivalence for "." with no letter/number following
        assertFalse(BankAccount.isEmailValid(".incorrect@test.com"));//Border for "." at beginning
        assertTrue(BankAccount.isEmailValid("-incorrect@test.com"));//Border for "-" at beginning
        assertTrue(BankAccount.isEmailValid("_incorrect@test.com"));//Border for "_" at beginning
        assertFalse(BankAccount.isEmailValid("incorrect@test.com."));//Border for "." at end
        assertFalse(BankAccount.isEmailValid("incorrect@test.com-"));//Border for "-" at end
        assertFalse(BankAccount.isEmailValid("incorrect@test.com_"));//Border for "_" at end
        assertFalse(BankAccount.isEmailValid("incor..rect@test.com"));//Equivalence for repeated "."
        assertFalse(BankAccount.isEmailValid("incor--rect@test.com"));//Equivalence for repeated "-"
        assertFalse(BankAccount.isEmailValid("incor__rect@test.com"));//Equivalence for repeated "_"

        //Email with "-" domain
        assertTrue(BankAccount.isEmailValid("correct@te-st.com")); //Equivalence for correctly used "-" in domain
        assertFalse(BankAccount.isEmailValid("incorrect@test.c")); //Equivalence for last portion being less than 2 letters
        assertFalse(BankAccount.isEmailValid("incorrect@test..com")); //Equivalence for two "." in ".com"


    }

}