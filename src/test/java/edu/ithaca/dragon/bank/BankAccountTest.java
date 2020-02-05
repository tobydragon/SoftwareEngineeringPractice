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
        //numbers with more than 2 decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(300.001));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(1000.04940));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and not border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(50.1029384958674950));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
        //negative numbers with more than 2 decimals
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100.001));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100.10239485));
        assertEquals(1000, negative.getBalance());          //equivalence class of negative balance and not border case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100.1029384758493815));
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
        //check for exception thrown correctly for email
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        //checks if it throws an argument for negative numbers
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -150));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -10000000));
        //checks if it throws an argument for numbers with more than 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 150.01020495));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -123.1029384758495837));
        //checks if it throws an argument for numbers that are negative and have more than 2 decimal places
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -120.123453));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.102938456744854));
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
        assertTrue(BankAccount.isAmountValid(500.00000000));
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

    @Test
    void depositTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 1000);
        //deposit valid integer
        bankAccount.deposit(1);
        assertEquals(1001, bankAccount.getBalance());
        bankAccount.deposit(100);
        assertEquals(1101, bankAccount.getBalance());
        bankAccount.deposit(10000);
        assertEquals(11101, bankAccount.getBalance());
        //deposit valid double with < 1 decimal
        bankAccount.deposit(1.1);
        assertEquals(11102.1, bankAccount.getBalance());
        bankAccount.deposit(10.5);
        assertEquals(11112.6, bankAccount.getBalance());
        bankAccount.deposit(10.9);
        assertEquals(11123.5, bankAccount.getBalance());
        //deposit valid with 2 decimals
        bankAccount.deposit(1.00);
        assertEquals(11124.50, bankAccount.getBalance());
        bankAccount.deposit(1.11);
        assertEquals(11125.61, bankAccount.getBalance());
        bankAccount.deposit(1.57);
        assertEquals(11127.18, bankAccount.getBalance());
        bankAccount.deposit(1.99);
        assertEquals(11129.17, bankAccount.getBalance());
        //invalid number, negative
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-500));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(Integer.MIN_VALUE));
        assertEquals(11129.17, bankAccount.getBalance());
        //invalid number, negative, one decimal
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.1));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.5));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.9));
        assertEquals(11129.17, bankAccount.getBalance());
        //invalid number, negative, 2 decimals
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.00));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.57));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.99));
        assertEquals(11129.17, bankAccount.getBalance());
        //invalid number, more than 2 decimals
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(1.001));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(-1.585976));
        assertEquals(11129.17, bankAccount.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(50.102938475960794));
        assertEquals(11129.17, bankAccount.getBalance());


    }

    @Test
    void transferTest() throws InsufficientFundsException,IllegalArgumentException{
        BankAccount bankAccount1 = new BankAccount("a@b.com", 1000);
        BankAccount bankAccount2 = new BankAccount("b@a.com", 0);
        //checking initial balances
        assertEquals(1000, bankAccount1.getBalance());
        assertEquals(0, bankAccount2.getBalance());
        //transfer from 1 to 2, integers, valid amount
        bankAccount1.transfer(1, bankAccount2);
        assertEquals(999, bankAccount1.getBalance());
        assertEquals(1, bankAccount2.getBalance());
        bankAccount1.transfer(99, bankAccount2);
        assertEquals(900, bankAccount1.getBalance());
        assertEquals(100, bankAccount2.getBalance());
        bankAccount1.transfer(900, bankAccount2);
        assertEquals(0, bankAccount1.getBalance());
        assertEquals(1000, bankAccount2.getBalance());
        //transfer from 2 to 1, 1 decimal, valid amount
        bankAccount2.transfer(0.1, bankAccount1);
        assertEquals(0.1, bankAccount1.getBalance());
        assertEquals(999.9, bankAccount2.getBalance());
        bankAccount2.transfer(99.5, bankAccount1);
        assertEquals(99.6, bankAccount1.getBalance());
        assertEquals(900.4, bankAccount2.getBalance());
        //transfer from 2 to 1, 2 decimals, valid amount
        bankAccount2.transfer(50.41, bankAccount1);
        assertEquals(150.01, bankAccount1.getBalance());
        assertEquals(849.99, bankAccount2.getBalance());
        bankAccount2.transfer(50.11, bankAccount1);
        assertEquals(200.12, bankAccount1.getBalance());
        assertEquals(799.88, bankAccount2.getBalance());
        bankAccount2.transfer(50.99, bankAccount1);
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        //invalid transfer, same bank
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(50, bankAccount1));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(50, bankAccount2));
        //invalid transfer, negative numbers
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(-1, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(-50.8, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(-500.99, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        //invalid transfer, 3 or more decimals
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(50.001, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(50.9484930, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        assertThrows(IllegalArgumentException.class, ()-> bankAccount1.transfer(-50.102938495869503, bankAccount2));
        assertEquals(251.11, bankAccount1.getBalance());
        assertEquals(748.89, bankAccount2.getBalance());
        //invalid transfer, insufficient funds
        bankAccount1.transfer(251.11, bankAccount2);

        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.transfer(1, bankAccount2));
        assertEquals(0, bankAccount1.getBalance());
        assertEquals(1000, bankAccount2.getBalance());
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.transfer(246, bankAccount2));
        assertEquals(0, bankAccount1.getBalance());
        assertEquals(1000, bankAccount2.getBalance());
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.transfer(Integer.MAX_VALUE, bankAccount2));
        assertEquals(0, bankAccount1.getBalance());
        assertEquals(1000, bankAccount2.getBalance());

    }

    @Test
    void SavingsAccountTest() throws IllegalArgumentException, InsufficientFundsException{
        //constructor test
        SavingsAccount savingsAccount = new SavingsAccount("a@b.com", 1000, 5, 500);

        //invalid interest
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, -0.1, 500));
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, -50.6, 500));
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, -150.6, 500));

        //invalid maxWithdraw
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, 5.0, 0));
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, 5.0, -1));
        assertThrows(IllegalArgumentException.class, ()-> new SavingsAccount("a@b.com", 1000, 5.0, -500.495));

        //Compound interest
        savingsAccount.compoundInterest();
        assertEquals(1050, savingsAccount.getBalance());
        savingsAccount.compoundInterest();
        assertEquals(1102.5, savingsAccount.getBalance());

        //Overridden Withdraw
        savingsAccount.withdraw(102.5);
        assertEquals(1000, savingsAccount.getBalance());

        assertThrows(IllegalArgumentException.class, () -> savingsAccount.withdraw(501));
        assertThrows(IllegalArgumentException.class, () -> savingsAccount.withdraw(750.59));
        assertThrows(IllegalArgumentException.class, () -> savingsAccount.withdraw(1000.75));
    }



}