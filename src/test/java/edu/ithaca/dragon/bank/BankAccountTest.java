package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new CheckingAccount("a@b.com", "password", 200, false);

        assertEquals(200, bankAccount.getBalance());

        //Josue's getBalanceTest
        BankAccount bankAccount2 = new CheckingAccount("a@b.com", "password", 0, false); //Equivalence case of 0 dollars (nothing) in bankaccount case
        BankAccount bankAccount3= new CheckingAccount("a@b.com", "password", 50, false); //Equivalence case of 50 dollars (small amount) in bankaccount case
        BankAccount bankAccount4 = new CheckingAccount("a@b.com", "password", 10000, false); //Equivalence case of 10000 dollars (large amount) in bankaccount case
        BankAccount bankAccount8 = new CheckingAccount("a@b.com", "password", 67.86, false); //Equivalence case of 67.86 dollars (float) in bankaccount case

        assertEquals(0, bankAccount2.getBalance());
        assertEquals(50, bankAccount3.getBalance());
        assertEquals(10000, bankAccount4.getBalance());
        assertEquals(67.86, bankAccount8.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, ExceedsMaxWithdrawalException{
        BankAccount bankAccount = new CheckingAccount("a@b.com", "password", 200, false);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());

        BankAccount bankAccount2 = new CheckingAccount("j@h.com", "password", 367.54, false); //Valid withraw
        bankAccount2.withdraw(100);
        assertEquals(267.54, bankAccount2.getBalance());
        BankAccount bankAccount3 = new CheckingAccount("j@h.com", "password", 367.54, false); //Valid withdraw with edgecase
        bankAccount3.withdraw(367.54);
        assertEquals(0, bankAccount3.getBalance());
        BankAccount bankAccount4 = new CheckingAccount("j@h.com", "password", 367.54, false); //Withdrawing larger than balance
        assertThrows(InsufficientFundsException.class, ()-> bankAccount4.withdraw(400));

        BankAccount bankAccount5 = new CheckingAccount("j@h.com", "password", 367.54, false); //Withdrawing very large amount, over balance
        assertThrows(InsufficientFundsException.class, ()-> bankAccount5.withdraw(6000));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

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
        BankAccount bankAccount = new CheckingAccount("a@b.com", "password", 200, false); //equivalence case is a regular amount of 100 dollars deposited
        bankAccount.deposit(100.);
        assertEquals(300, bankAccount.getBalance());

        BankAccount bankAccount2 = new CheckingAccount("a@b.com", "password", 7000.0, false); //equivalence case using decimals and values above 5000 in account
        bankAccount2.deposit(234.98);
        assertEquals(7234.98, bankAccount2.getBalance());

        //Negative deposit amount
        BankAccount bankAccount5 = new CheckingAccount("a@b.com", "password", 200, false); //equivalence case with withdraw being a negative and automatically adding nothing
        assertThrows(IllegalArgumentException.class, ()-> bankAccount5.deposit(-600));

        BankAccount bankAccount6 = new CheckingAccount("a@b.com", "password", 100000, false); //equivalence edge case without anything being deposited
        assertThrows(IllegalArgumentException.class, ()-> bankAccount6.deposit(-10000));

        //Multiple deposits with doubles
        BankAccount bankAccount7 = new CheckingAccount("a@b.com", "password", 400, false); //equivalence case with multiple decimal value deposits
        bankAccount7.deposit(65.93);
        assertEquals(465.93, bankAccount7.getBalance());
        bankAccount7.deposit(23.93);
        assertEquals(489.86, bankAccount7.getBalance());

        BankAccount bankAccount8 = new CheckingAccount("a@b.com", "password", 400); //equivalence case with multiple low decimal value deposits
        bankAccount8.deposit(16.75);
        assertEquals(416.75, bankAccount8.getBalance());
        bankAccount8.deposit(3.40);
        assertEquals(420.15, bankAccount8.getBalance());
    }

    @Test
    void transferTest() throws InsufficientFundsException, ExceedsMaxWithdrawalException {
        //equivalence case with basic balances of 200 and 500 with 50 dollar transfer
        BankAccount bankAccount = new CheckingAccount("a@b.com", "password", 200);
        BankAccount bankAccount2 = new CheckingAccount("a@b.com", "password", 500);
        BankAccount.transfer(bankAccount, bankAccount2, 50.00);
        assertEquals(250, bankAccount.getBalance());
        assertEquals(450, bankAccount2.getBalance());

        //equivalence case with 2nd account is less than amount wanted to transfer, so nothing gets transferred
        BankAccount bankAccount7 = new CheckingAccount("a@b.com", "password", 549.34);
        BankAccount bankAccount8 = new CheckingAccount("a@b.com", "password", 47);
        assertThrows(InsufficientFundsException.class, ()-> BankAccount.transfer(bankAccount7, bankAccount8, 48.54));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new CheckingAccount("a@b.com", "password", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("", "password", 100, false)); //equivalence case with no email input
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("a.cc", "password", -100, false)); //equivalence case with only a letter
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("@b.cc", "password", 100, false)); //equivalence case with only domain and extension
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("bank@email.com", "", 100, false));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("bank@email.com", "1234", 100, false));
        assertThrows(IllegalArgumentException.class, ()-> new CheckingAccount("bank@email.com", "passwrd", 100,false));

        //ummmm these tests don't exactly go with the constructor test but ok????
        BankAccount bankAccount1 = new CheckingAccount("hello@barry.cc", "password", 50, false);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.withdraw(-6000)); //equivalence case with a large (or low) negative amount value to withdraw
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.withdraw(-60)); //equivalence case with a negative amount value to withdraw

        BankAccount bankAccount2 = new CheckingAccount("b@a.cc", "password", 90, false);
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(bankAccount1, bankAccount2, -900)); //equivalence case where the transfer amount is negative amount of money
        assertThrows(IllegalArgumentException.class, ()-> BankAccount.transfer(bankAccount1, bankAccount2, -91)); //equivalence case of negative amount of money to transfer
    }

    @Test
    void isPasswordValidTest() {
        //too short
        assertFalse(BankAccount.isPasswordValid(""));
        assertFalse(BankAccount.isPasswordValid("pw123"));
        assertFalse(BankAccount.isPasswordValid("passwrd"));

        //okay
        assertTrue(BankAccount.isPasswordValid("password"));
        assertTrue(BankAccount.isPasswordValid("supersecretpassword123"));
        assertTrue(BankAccount.isPasswordValid("#thebestpasswordever!!12345"));
    }

}