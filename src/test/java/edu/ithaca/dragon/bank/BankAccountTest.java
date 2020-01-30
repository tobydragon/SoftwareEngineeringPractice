package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException, IllegalArgumentException {
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
        assertEquals(1000, unsuccessful.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(2000));       //equivalence class of middle value and not border case
        assertEquals(1000, unsuccessful.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(Integer.MAX_VALUE));  //equivalence class of Max Value and border case
        assertEquals(1000, unsuccessful.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException{
        //classes - sufficient funds, insufficient funds, negative funds
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);
        //sufficient funds
        bankAccount.withdraw(0);
        assertEquals(1000, bankAccount.getBalance());
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
        //negative numbers
        BankAccount negative = new BankAccount("a@b.com", 1000);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-500));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and not border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(min));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
    }

    @Test
    void isEmailValidTest(){
        //one @ symbol class
        assertTrue(BankAccount.isEmailValid( "a.b.c@b.com"));                   //equivalence class of one @ and not border case
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
    void isAmountValidTest(){
        //valid number, no decimals
        assertTrue(BankAccount.isAmountValid(0));
        assertTrue(BankAccount.isAmountValid(1));
        assertTrue(BankAccount.isAmountValid(500));
        assertTrue(BankAccount.isAmountValid(678));
        assertTrue(BankAccount.isAmountValid(Integer.MAX_VALUE));
        //valid number, 1 decimal
        assertTrue(BankAccount.isAmountValid(500.0));
        assertTrue(BankAccount.isAmountValid(500.1));
        assertTrue(BankAccount.isAmountValid(500.5));
        assertTrue(BankAccount.isAmountValid(500.9));
        //valid number, 2 decimals
        assertTrue(BankAccount.isAmountValid(500.00));
        assertTrue(BankAccount.isAmountValid(500.01));
        assertTrue(BankAccount.isAmountValid(500.10));
        assertTrue(BankAccount.isAmountValid(500.62));
        assertTrue(BankAccount.isAmountValid(500.99));
        //invalid number, more than 2 decimals
        assertFalse(BankAccount.isAmountValid(500.001));
        assertFalse(BankAccount.isAmountValid(500.597));
        assertFalse(BankAccount.isAmountValid(500.56690930452));
        assertFalse(BankAccount.isAmountValid(500.999));
        assertFalse(BankAccount.isAmountValid(500.2048675849586746));
        //invalid number, negative with 0 decimals
        assertFalse(BankAccount.isAmountValid(-1));
        assertFalse(BankAccount.isAmountValid(-100));
        assertFalse(BankAccount.isAmountValid(Integer.MIN_VALUE));
        //invalid number, negative with 1 decimal
        assertFalse(BankAccount.isAmountValid(-1.0));
        assertFalse(BankAccount.isAmountValid(-100.7));
        assertFalse(BankAccount.isAmountValid(-999999.9));
        //invalid number, negative with 2 decimals
        assertFalse(BankAccount.isAmountValid(-1.00));
        assertFalse(BankAccount.isAmountValid(-100.59));
        assertFalse(BankAccount.isAmountValid(-999999999999.99));
        //invalid number, negative with more than 2 decimals
        assertFalse(BankAccount.isAmountValid(-100.001));
        assertFalse(BankAccount.isAmountValid(-100.5689));
        assertFalse(BankAccount.isAmountValid(-100.5784939576859));
        assertFalse(BankAccount.isAmountValid(-999.9999999999999999));





    }

}