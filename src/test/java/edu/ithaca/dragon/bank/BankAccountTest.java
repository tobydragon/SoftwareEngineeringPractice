package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException {
        //classes - fresh account, after withdrawal, after unsuccessful withdrawal
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);

        //fresh account
        assertEquals(1000, bankAccount.getBalance());               //equivalence class starting balance and not border cas
        //after withdrawal
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance());                //equivalence class of less than and not border case
        bankAccount.withdraw(500);
        assertEquals(400, bankAccount.getBalance());                //equivalence class of more than and not border case
        bankAccount.withdraw(400);
        assertEquals(0, bankAccount.getBalance());                  //equivalence class of zero and border case

        //after unsuccessful withdrawal
        BankAccount unsuccessful = new BankAccount("a@b.com",1000);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1100));       //equivalence class of greater than and border case
        assertEquals(1000, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(2000));       //equivalence class of middle value and not border case
        assertEquals(1000, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(Integer.MAX_VALUE));  //equivalence class of Max Value and border case
        assertEquals(1000, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        //classes - sufficient funds, insufficient funds, negative funds
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);
        //sufficient funds
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance());            //equivalence class of less than and not border case
        bankAccount.withdraw(500);
        assertEquals(400, bankAccount.getBalance());            //equivalence class of more than and not border case
        bankAccount.withdraw(400);
        assertEquals(0, bankAccount.getBalance());              //equivalence class of zero and border case
        //insufficient funds
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(max));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1));
        //negative numbers, does nothing for now
        BankAccount negative = new BankAccount("a@b.com", 1000);
        negative.withdraw(-1);
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
        negative.withdraw(-500);
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and not border case
        negative.withdraw(min);
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
    }

    @Test
    void isEmailValidTest(){
        //one @ symbol class
        assertTrue(BankAccount.isEmailValid( "a@b.com"));                   //equivalence class of one @ and not border case
        assertFalse(BankAccount.isEmailValid("abc@def@mail.com"));          //equivalence class of multiple @ and border case
        assertFalse(BankAccount.isEmailValid("abc@d@ef@mail.com"));         //equivalence class of multiple @ and border case
        assertFalse(BankAccount.isEmailValid("abc@d@ef@ma@il.com"));        //equivalence class of multiple @ and border case
        assertFalse( BankAccount.isEmailValid(""));                         //equivalence class of one no @ and border case
        //valid special characters in prefix
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));             //equivalence class  of one valid special characters and not border case
        assertFalse(BankAccount.isEmailValid("abc..@mail.com"));            //equivalence class  of two valid special characters and not border case
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));             //equivalence class  of valid special characters and not border case
        //invalid characters in prefix
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));          //equivalence class  of one invalid characters and border case
        assertFalse(BankAccount.isEmailValid("abc#de!f@mail.com"));         //equivalence class  of two invalid characters and border case
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));           //equivalence class  of one invalid characters and not border case
        //invalid suffix characters
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));  //equivalence class  of invalid suffix characters and border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail!ar%chive.com")); //equivalence class  of invalid suffix characters and border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail!ar%chi.ve.com"));    //equivalence class  of invalid suffix characters and border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail--archive.com")); //equivalence class  of invalid suffix characters and border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail-arc!h.ive.com"));    //equivalence class  of invalid suffix characters and border case
        //valid domain
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));            //equivalence class  of invalid domain and not border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));              //equivalence class  of no domain and border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));         //equivalence class  of two . in domain and border case
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));            //equivalence class  of invalid domain and not border case

        /* equivalence class of Domain present twice and border case */
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