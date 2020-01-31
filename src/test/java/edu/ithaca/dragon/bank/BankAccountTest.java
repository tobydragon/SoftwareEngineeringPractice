package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() throws InsufficientFundsException {

        //valid case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        //after successfull withdraw
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

    }

    @Test
    void isAmountValidTest(){
        //negative amount border case
        assertFalse(BankAccount.isAmountValid(-00.01));

        //negative amount
        assertFalse(BankAccount.isAmountValid(-100));

        //positive border case
        assertTrue(BankAccount.isAmountValid(00.01));

        //positive amount
        assertTrue(BankAccount.isAmountValid(100.00));

        //invalid format border case
        assertFalse(BankAccount.isAmountValid(123.987));

        //invalid format
        assertFalse(BankAccount.isAmountValid(83746.82736453));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException, IllegalArgumentException{
        //greater than balance
        assertThrows(InsufficientFundsException.class, ()-> new BankAccount("a@b.com", 100).withdraw(101));


        //less than balance
        BankAccount bankaccount = new BankAccount("a@b.com", 200);
        bankaccount.withdraw(100);
        assertEquals(100, bankaccount.getBalance());


        //equal to balance
        bankaccount = new BankAccount("a@b.com", 300);
        bankaccount.withdraw(300);
        assertEquals(0, bankaccount.getBalance());


        //negative - argument thrown
        assertThrows(IllegalArgumentException.class,() -> new BankAccount("a@b.com", 300).withdraw(-1));


        //invalid input
        assertThrows(IllegalArgumentException.class,() -> new BankAccount("a@b.com",200).withdraw(100.005));
    }


    @Test
    void isEmailValidTestUpdated(){
        // checks for a basic, valid email and for empty string
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(" "));

        // checks for forbidden characters... border case:  one forbidden characters is present
        assertFalse(BankAccount.isEmailValid("ab#c@gmail.com"));
        assertFalse(BankAccount.isEmailValid("ab-c @gmail.com"));

        // checks for 1 period after the @... border case: 0, 1, or 2 periods found
        assertTrue(BankAccount.isEmailValid("hay@mail.com"));
        assertFalse(BankAccount.isEmailValid("hayyy@mailcom"));
        assertFalse(BankAccount.isEmailValid("hay@ma.il.com"));

        // checks for the proper number of @ symbols... border case: 0, 1, or 2 @'s present
        assertTrue(BankAccount.isEmailValid("cmartano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("cmartanoaol.com"));
        assertFalse(BankAccount.isEmailValid("c@martano@yahoo.com"));

        // checks for the number of characters after the last period... border case: 1 or 2
        assertTrue(BankAccount.isEmailValid("name@place.co"));
        assertFalse(BankAccount.isEmailValid("name@place.c"));

        // checks that each dash is a "legal" dash... border case: dash to start, 2 in a row, before @ symbol
        assertTrue(BankAccount.isEmailValid("c-martano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("marta--no@gmail.com"));
        assertFalse(BankAccount.isEmailValid("-christianmar-tano@aol.com"));
        assertFalse(BankAccount.isEmailValid("c-l-martano-@gmail.com"));

        // checks that each period is a "legal" period... border case: period to start, 2 in a row, before @ symbol
        assertTrue(BankAccount.isEmailValid("c.martano@gmail.com"));
        assertFalse(BankAccount.isEmailValid("c.ma..rtano@gmail.com"));
        assertFalse(BankAccount.isEmailValid(".christian@gmail.com"));
        assertFalse(BankAccount.isEmailValid("c.l.martano.@gmail.com"));


        //presence of invalid character
        assertTrue(BankAccount.isEmailValid("abc@google.com"));
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc.@mail.com"));
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));
    }


    @Test
    void constructorTest() {
        //valid test case
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());

        //check for email exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        //check for balance exception thrown correctly, border case
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -00.01));

    }

    @Test
    void depositTest() throws IllegalArgumentException, InsufficientFundsException {
        //successful deposit
        BankAccount bankAccount = new BankAccount("a@b.com", 0);
        bankAccount.Deposit(100);
        assertEquals(100, bankAccount.getBalance());
        bankAccount.Deposit(0);
        assertEquals(100, bankAccount.getBalance());

        //checks for negative values
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 50).Deposit(-0.01));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 50).Deposit(-50));

        //successful deposit including change
        bankAccount = new BankAccount("a@b.com", 150);
        bankAccount.Deposit(0.10);
        assertEquals(150.10, bankAccount.getBalance());
        bankAccount.Deposit(31.31);
        assertEquals(181.41, bankAccount.getBalance());

        //invalid input check
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 50).Deposit(100.031));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 50).Deposit(0.00001));


    }

    @Test
    void transferTest() throws IllegalArgumentException, InsufficientFundsException{
        BankAccount sender = new BankAccount("a@b.com", 500);
        BankAccount reciever = new BankAccount("y@z.com", 0);
        //successfull transfer
        sender.Transfer(reciever,100);
        assertEquals(400, sender.getBalance());
        assertEquals(100, reciever.getBalance());

        //transfer with change
        sender.Transfer(reciever, 1.50);
        assertEquals(398.50, sender.getBalance());
        assertEquals(101.50, reciever.getBalance());

        //0 dollars transfered
        sender.Transfer(reciever, 0);
        assertEquals(398.50, sender.getBalance());
        assertEquals(101.50, reciever.getBalance());

        //negative amount trying to be transfered
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100).Transfer(new BankAccount("c@d.com", 0),-5));

        //invalid input check
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100).Transfer(new BankAccount("y@z.com", 0),0.223));

        //trying to transfer insufficent funds
        assertThrows(InsufficientFundsException.class, ()-> new BankAccount("a@b.com", 100).Transfer(new BankAccount("hay@hay.com", 0),300));

        //checks to be sure illegal argument is thrown rather than insufficient funds when both are valid
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 10).Transfer(new BankAccount("c@d.com", 0),100.3131));
    }



}