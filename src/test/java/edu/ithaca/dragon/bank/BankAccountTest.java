
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void transferTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("abg@g.com",100); //tests for a valid transfer
        BankAccount bankAccount1 = new BankAccount("a@b.com",100);
        bankAccount.transfer(bankAccount1,80);
        assertEquals(180,bankAccount1.getBalance());
        assertEquals(20, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("abg@g.com",100); // tests for a valid withdraw at the boundary of your balance
        BankAccount bankAccount3 = new BankAccount("a@b.com",100);
        bankAccount2.transfer(bankAccount3,100);
        assertEquals(200,bankAccount3.getBalance());
        assertEquals(0, bankAccount2.getBalance());

        BankAccount bankAccount4 = new BankAccount("abg@g.com",30); //tests for a valid transfer at the boundary of the smallest amount
        BankAccount bankAccount5 = new BankAccount("a@b.com",50);
        bankAccount4.transfer(bankAccount5,0.01);
        assertEquals(29.99,bankAccount4.getBalance());
        assertEquals(50.01, bankAccount5.getBalance());

        BankAccount bankAccount6 = new BankAccount("abg@g.com",900); //tests for an invalid transfer of 0
        BankAccount bankAccount7 = new BankAccount("a@b.com",1000);
        assertThrows(IllegalArgumentException.class, () -> bankAccount6.transfer(bankAccount7,0));
        assertEquals(900,bankAccount6.getBalance());
        assertEquals(1000, bankAccount7.getBalance());

        BankAccount bankAccount8 = new BankAccount("abg@g.com",700); //tests for an invalid transfer of an amount larger than your balance
        BankAccount bankAccount9 = new BankAccount("a@b.com",654.60);
        assertThrows(InsufficientFundsException.class, () -> bankAccount8.transfer(bankAccount9,701));
        assertEquals(700,bankAccount8.getBalance());
        assertEquals(654.60, bankAccount9.getBalance());

        BankAccount bankAccount10 = new BankAccount("abg@g.com",600); //tests for an invalid transfer of a negative number
        BankAccount bankAccount11 = new BankAccount("a@b.com",600);
        assertThrows(IllegalArgumentException.class, () -> bankAccount10.transfer(bankAccount11,-600));
        assertEquals(600,bankAccount10.getBalance());
        assertEquals(600, bankAccount11.getBalance());

        BankAccount bankAccount12 = new BankAccount("abg@g.com",600); //tests for an invalid transfer of a negative number and an amount too large
        BankAccount bankAccount13 = new BankAccount("a@b.com",600);
        assertThrows(IllegalArgumentException.class, () -> bankAccount12.transfer(bankAccount13,-700));
        assertEquals(600,bankAccount12.getBalance());
        assertEquals(600, bankAccount13.getBalance());







    }

    //need to tests for deposit of 0

    @Test
    void depositTest() throws IllegalArgumentException{

        BankAccount bankAccount = new BankAccount("abg@g.com",0); //tests for a valid deposit
        bankAccount.deposit(100);
        assertEquals(100,bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("abg@g.com",5); //tests for a valid deposit with two decimal places
        bankAccount2.deposit(100.50);
        assertEquals(105.50,bankAccount2.getBalance());

        BankAccount bankAccount3 = new BankAccount("abg@g.com",20); //tests for an invalid deposit with more than two decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount3.deposit(100.5080));
        assertEquals(20,bankAccount3.getBalance());

        BankAccount bankAccount4 = new BankAccount("abg@g.com",50); //tests for an invalid deposit with a negative number
        assertThrows(IllegalArgumentException.class, () -> bankAccount4.deposit(-10000000));
        assertEquals(50,bankAccount4.getBalance());

        BankAccount bankAccount5 = new BankAccount("abg@g.com",75); //tests for an invalid deposit with a negative number and more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> bankAccount5.deposit(-10000000.9874));
        assertEquals(75,bankAccount5.getBalance());

        BankAccount bankAccount6 = new BankAccount("abg@g.com",75); //tests for an invalid deposit of 0.
        assertThrows(IllegalArgumentException.class, () -> bankAccount5.deposit(0));
        assertEquals(75,bankAccount5.getBalance());




    }

    @Test
    void isAmountValidTest() throws InsufficientFundsException {

        BankAccount bankAccount = new BankAccount("ak3@g.com",9000);//gets a postive number with 2 decimal points. Should pass
        bankAccount.withdraw(123.45);
        assertEquals(8876.55, bankAccount.getBalance());

        BankAccount bankAccount3 = new BankAccount("ak@g.com",45);//gets a postive number with 1 decimal point. Should pass
        bankAccount3.withdraw(20.5);
        assertEquals(24.5, bankAccount3.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@b.com", 200); //checks to make sure a positive number with 3 decimal places is invalid
        assertThrows(IllegalArgumentException.class, () -> bankAccount2.withdraw(123.546));
        assertEquals(200,bankAccount2.getBalance());

        BankAccount bankAccount1 = new BankAccount("a@b.com", 300); //checks to make sure a negative number with 4 decimal places is invalid
        assertThrows(IllegalArgumentException.class, () -> bankAccount1.withdraw(25.5566));
        assertEquals(300,bankAccount1.getBalance());

    }

    @Test
    void getBalanceTest() {

        //correctly gives you the balance when you have a valid email
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        //gives you your balance when your balance is 0. Valid email.
        BankAccount bankAccount1 = new BankAccount("abc-d@mail.com", 0);
        assertEquals(0, bankAccount1.getBalance());

        //returns your balance when it is negative. Valid email.
        BankAccount bankAccount2 = new BankAccount("abc-d@mail.com", -10);
        assertEquals(-10, bankAccount2.getBalance());

        //correctly gives you the balance when you have a valid email
        BankAccount bankAccount3 = new BankAccount("a@b.com", 800.67);
        assertEquals(800.67, bankAccount3.getBalance());



    }

    @Test
    void withdrawTest() throws InsufficientFundsException {

        //tests for a valid withdrawal that will leave you with a half balance. Valid email is provided.
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //tests for a valid withdrawal that will leave you with no money left in the account. Valid Email.
        BankAccount bankAccount1 = new BankAccount("abc-d@mail.com", 100);
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        //tests for an invalid withdrawal of $0 that will leave you with the same balance no matter what it is. Valid email.
        BankAccount bankAccount2 = new BankAccount("abc-d@mail.com", 100);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(0));
        assertEquals(100,bankAccount2.getBalance());

        //tests for an invalid withdrawal that is more than your current balance. valid email.
        BankAccount bankAccount3 = new BankAccount("abc-d@mail.com", 348.08);
        assertThrows(InsufficientFundsException.class, ()-> bankAccount3.withdraw(349));
        assertEquals(348.08,bankAccount3.getBalance());

        //tests for an invalid withdrawal that is a negative number. Valid email.
        BankAccount bankAccount4 = new BankAccount("abc-d@mail.com", 590.02);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(-20));
        assertEquals(590.02,bankAccount4.getBalance());

        //tests for an invalid withdrawal that is a fraction of a penny. Should return an illegal argument exception
        BankAccount bankAccount5 = new BankAccount("abc-d@mail.com", 300.50);
        assertThrows(IllegalArgumentException.class, ()-> bankAccount4.withdraw(20.567));
        assertEquals(300.50,bankAccount5.getBalance());

    }

    @Test
    void isEmailValidTest(){


        assertTrue(BankAccount.isEmailValid("a@b.com"));
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.archive.com"));

        //tests the equivalence class for no period.
        assertFalse(BankAccount.isEmailValid("a@b"));
        //tests for no domain after @ sign
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        //Tests for an invalid domain because it is too short
        assertFalse(BankAccount.isEmailValid("ab@j.c"));
        //tests for a domain that is invalid because it is too long.
        assertFalse(BankAccount.isEmailValid("ab@domain.c"));
        //Tests for a domain that comes before the @ sign and not after
        assertFalse(BankAccount.isEmailValid("ab.com@j"));
        //Tests for an email without an @ sign
        assertFalse(BankAccount.isEmailValid("ab#c#domain.com"));
        //Tests for an email that has a special character before the @ sign.
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //tests for a an email that has double special characters
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        //tests for an email that starts with a special character
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        //tests for an email that has an invalid character.
        assertFalse(BankAccount.isEmailValid("abc$def@mail.com"));
        //tests for an email that has invalid domain after the period because it is too short
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //tests for an email that has an invalid character after the @ sign.
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //tests for an email that has no period after the @ sign
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        //tets for an email that has double special characters after the @ sign
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));
        //tests for a special character at the end of the email.
        assertFalse(BankAccount.isEmailValid("abc@def.co-"));


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100.908)); //no email inputed and invalid starting balance

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 10.804)); //throws exception for illegal starting balance

    }

}