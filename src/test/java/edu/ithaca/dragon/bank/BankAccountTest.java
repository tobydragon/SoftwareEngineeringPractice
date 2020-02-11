package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        //Positive, negative, and 0 account value testing:
        //Valid case (0 - infinity)
        BankAccount bankAccount;
        bankAccount = new BankAccount("a@b.com", "pass", 200);
        assertEquals(200, bankAccount.getBalance());  //Normal case, positive
        bankAccount = new BankAccount("a@b.com","pass", 1);
        assertEquals(1, bankAccount.getBalance());  //Edge case, positive
        bankAccount = new BankAccount("a@b.com", "pass",0);
        assertEquals(0, bankAccount.getBalance());  //Edge case, 0

        bankAccount = new BankAccount("a@b.com", "pass",100);
        assertEquals(100, bankAccount.getBalance());

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount;
        //Withdrawing only available funds tests:
        //Valid case (does not go over available funds)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount.withdraw(200);
        assertEquals(0, bankAccount.getBalance());  //Edge case, withdrawing all available funds
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());  //Normal case, withdrawing only half of available funds
        //Invalid case (withdraws more than available)
        BankAccount bankAccount1 = new BankAccount("a@b.com", "pass",200);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(201));  //Edge case, withdrawing just over available funds
        assertThrows(InsufficientFundsException.class, ()-> bankAccount1.withdraw(400));  //Normal case, withdrawing double available funds

        //Passing only positive values tests:
        //Valid case (positive values including 0)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount.withdraw(0);  //Edge case, withdrawing 0
        bankAccount.withdraw(100);  //Normal case, withdrawing positive number
        //Invalid case (negative values)
        BankAccount bankAccount2 = new BankAccount("a@b.com", "pass",200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-1));  //Edge case, passing barely negative value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-200));  //Normal case, passing very negative value

        //Significant decimals tests:
        //Valid case (0 - 2 significant decimals)
        bankAccount = new BankAccount("a@b.com","pass", 200);
        bankAccount.withdraw(50.3);  //Normal case, withdrawing 1 significant decimal
        bankAccount.withdraw(50.35);  //Edge case, withdrawing 2 significant decimal
        //Invalid case (3 - infinity significant decimals)
        BankAccount bankAccount3 = new BankAccount("a@b.com", "pass",200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.withdraw(50.352));  //Edge case, withdrawing 3 significant decimal
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.withdraw(50.35662));  //Normal case, withdrawing 5 significant decimal
    }

    @Test
    void depositTest() {
        BankAccount bankAccount;

        //Passing only positive values tests:
        //Valid case (positive values including 0)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount.deposit(0);  //Edge case, depositing 0
        assertEquals(200, bankAccount.getBalance(), 0.0001);
        bankAccount.deposit(100);  //Normal case, depositing positive number
        assertEquals(300, bankAccount.getBalance(), 0.0001);
        //Invalid case (negative values)
        BankAccount bankAccount2 = new BankAccount("a@b.com","pass", 200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.deposit(-1));  //Edge case, passing barely negative value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.deposit(-200));  //Normal case, passing very negative value

        //Significant decimals tests:
        //Valid case (0 - 2 significant decimals)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount.deposit(50.3);  //Normal case, depositing 1 significant decimal
        assertEquals(250.3, bankAccount.getBalance(), 0.0001);
        bankAccount.deposit(50.35);  //Edge case, depositing 2 significant decimal
        assertEquals(300.65, bankAccount.getBalance(), 0.0001);
        //Invalid case (3 - infinity significant decimals)
        BankAccount bankAccount3 = new BankAccount("a@b.com", "pass",200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.deposit(50.352));  //Edge case, depositing 3 significant decimal
        assertThrows(IllegalArgumentException.class, ()-> bankAccount3.deposit(50.35662));  //Normal case, depositing 5 significant decimal
    }

    @Test
    void transferTest() throws InsufficientFundsException{
        BankAccount bankAccount;
        BankAccount bankAccount1;

        //Passing only positive values tests:
        //Valid case (positive values including 0)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount1 = new BankAccount("a@b.com", "pass",200);
        bankAccount.transfer(0, bankAccount1);  //Edge case, transferring 0
        assertEquals(200, bankAccount.getBalance(), 0.0001);
        assertEquals(200, bankAccount1.getBalance(), 0.0001);
        bankAccount.transfer(100, bankAccount1);  //Normal case, transferring positive number
        assertEquals(100, bankAccount.getBalance(), 0.0001);
        assertEquals(300, bankAccount1.getBalance(), 0.0001);
        //Invalid case (negative values)
        BankAccount bankAccount2 = new BankAccount("a@b.com", "pass",200);
        BankAccount bankAccount3 = new BankAccount("a@b.com", "pass",200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(-1, bankAccount3));  //Edge case, passing barely negative value
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.transfer(-200, bankAccount3));  //Normal case, passing very negative value

        //Significant decimals tests:
        //Valid case (0 - 2 significant decimals)
        bankAccount = new BankAccount("a@b.com", "pass",200);
        bankAccount1 = new BankAccount("a@b.com", "pass",200);
        bankAccount.transfer(50.3, bankAccount1);  //Normal case, transferring 1 significant decimal
        assertEquals(149.7, bankAccount.getBalance(), 0.0001);
        assertEquals(250.3, bankAccount1.getBalance(), 0.0001);
        bankAccount.transfer(50.35, bankAccount1);  //Edge case, transferring 2 significant decimal
        assertEquals(99.35, bankAccount.getBalance(), 0.0001);
        assertEquals(300.65, bankAccount1.getBalance(), 0.0001);
        //Invalid case (3 - infinity significant decimals)
        BankAccount bankAccount4 = new BankAccount("a@b.com", "pass",200);
        BankAccount bankAccount5 = new BankAccount("a@b.com", "pass",200);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.transfer(50.352, bankAccount5));  //Edge case, transferring 3 significant decimal
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.transfer(50.35662, bankAccount5));  //Normal case, transferring 5 significant decimal
    }

    @Test
    void isAmountValidTest(){
        //Non-negative test
        //Invalid case (-infinity - -0.01)
        assertFalse(BankAccount.isAmountValid(-100.00));  //Normal case, very negative value
        assertFalse(BankAccount.isAmountValid(-0.01));  //Edge case, barely negative value
        //Valid case (0.00 - infinity)
        assertTrue(BankAccount.isAmountValid(0.00));  //Edge case, barely non-negative
        assertTrue(BankAccount.isAmountValid(100.00));  //Normal case, postitive

        //Decimal place test
        //Valid case (0 - 2 decimal points)
        assertTrue(BankAccount.isAmountValid(10));  //Normal case, 0 decimal points
        assertTrue(BankAccount.isAmountValid(100.00));  //Edge case, 2 decimal points
        //Valid case (0 - 2 decimal points)
        assertFalse(BankAccount.isAmountValid(100.001));  //Edge case, 3 decimal points
        assertFalse(BankAccount.isAmountValid(100.00001));  //Normal case, 5 decimal points
    }

    @Test
    void isEmailValidTest(){
        //Prefix length tests
        //Invalid case (0)
        assertFalse(BankAccount.isEmailValid("@mail.com"));  //Edge case, size 0
        //Valid case (1-infinity)
        assertTrue(BankAccount.isEmailValid( "a@mail.com"));  //Edge case, size 1
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Normal case, size 5

        //Invalid character tests:
        //Valid case (0)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, 0 invalid characters
        //Invalid case (1-infinity)
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));  //Edge case, 1 invalid character
        assertFalse( BankAccount.isEmailValid("a^bc#de%f@mail.com"));  //Normal case, 3 invalid characters

        //Number of @ signs tests:
        //Invalid case (0)
        assertFalse(BankAccount.isEmailValid( "abcdef.mail.com"));  //Edge case, 0 @ signs
        //Valid case (1)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, 1 @ sign
        //Invalid case (2-infinity)
        assertFalse(BankAccount.isEmailValid( "abcdef@@mail.com"));  //Edge case, 2 @ signs
        assertFalse(BankAccount.isEmailValid( "abcdef@@@@@mail.com"));  //Normal case, 5 @ signs

        //.com length tests:
        //Invalid case (0-1)
        assertFalse(BankAccount.isEmailValid( "abcdef@mail"));  //Edge case, 0 .com length
        assertFalse(BankAccount.isEmailValid( "abcdef@mail."));  //Edge case, 0 .com length
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.c"));  //Edge case, 1 .com length
        //Valid case (2-infinity)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.co"));  //Edge case, 2 .com length
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.info"));  //Normal case, 4 .com length

        //Alphanumeric characters in a row tests:
        //Valid case (0-1)
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Normal case, 0 non-alphanum character, 0 in a row
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));  //Normal case, 1 non-alphanum character, 1 in a row
        assertTrue(BankAccount.isEmailValid( "a.b.c.d.e.f@mail.com"));  //Edge case, 5 non-alphanum characters, 1 in a row
        //Invalid case (2-infinity)
        assertFalse(BankAccount.isEmailValid( "abc..def@mail.com"));  //Edge case, 2 non-alphanum characters, 2 in a row
        assertFalse(BankAccount.isEmailValid( "a.b.c..d.e.f@mail.com"));  //Normal case, 6 non-alphanum characters, 2 in a row
        assertFalse(BankAccount.isEmailValid( "abc.....def@mail.com"));  //Normal case, 5 non-alphanum characters, 5 in a row

        //Alphanumeric starting/ending characters test:
        //Valid case (Prefix, domain, and .com all start and end with alphanumeric characters):
        assertTrue(BankAccount.isEmailValid( "abcdef@mail.com"));  //Edge case, all valid
        //Invalid case (Prefix, domain, or .com don't start or end with alphanumeric characters):
        assertFalse(BankAccount.isEmailValid( "-abcdef@mail.com"));  //Edge case, prefix doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef-@mail.com"));  //Edge case, prefix doesn't end with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@-mail.com"));  //Edge case, suffix doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail-.com"));  //Edge case, suffix doesn't end with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.-com"));  //Edge case, domain doesn't start with alphanumeric character
        assertFalse(BankAccount.isEmailValid( "abcdef@mail.com-"));  //Edge case, domain doesn't end with alphanumeric character
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com","pass",200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", "pass",100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", "pass",-100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", "pass",100.234));
    }

}