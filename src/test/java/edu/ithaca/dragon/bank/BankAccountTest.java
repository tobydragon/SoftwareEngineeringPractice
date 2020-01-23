package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());

        //New tests
        //Partitions of -0.01=invalid partition, 0.00->100.0 first, 101.00->200.00 second, 201.00+ third

        //Equivalence class above the maximum balance as a third, with a invalid boundary value above 200.00 maximum
        BankAccount newAccount1 = new BankAccount("c@d.com", 200);
        newAccount1.withdraw( 300);
        assertEquals(200, newAccount1.getBalance());

        //Equivalence class less being an invalid partition, with the boundary value of -0.01
        BankAccount newAccount2 = new BankAccount("c@d.com", 200);
        newAccount2.withdraw(-200);
        assertEquals(200, newAccount2.getBalance());

        //Equivalence class being valid with a staring partition of 0 with first, with boundary value 0.00
        BankAccount newAccount3 = new BankAccount("c@d.com", 200);
        newAccount3.withdraw(0);
        assertEquals(200, newAccount3.getBalance());

        //Equivalence class being valid as the maximum partition with second, with a boundary value of 200.00 maximum
        BankAccount newAccount4 = new BankAccount("c@d.com", 200);
        newAccount4.withdraw(200);
        assertEquals(0, newAccount4.getBalance());

        //Equivalence class being valid as the second partition, with a boundary value of 0.00 and 100.00 maximum
        BankAccount newAccount5 = new BankAccount("c@d.com", 200);
        newAccount5.withdraw(67);
        assertEquals(133, newAccount5.getBalance());

        //Equivalence class being valid as the third partition, with a boundary value between 101.00 and 200.00 maximum
        BankAccount newAccount6 = new BankAccount("c@d.com", 200);
        newAccount6.withdraw(146);
        assertEquals(54, newAccount6.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //Partitions of ""=invalid partition, address first, domain second, extension third

        //Equivalence class being in first partitions, with boundary value to index[0]->indexOf(@)
        assertFalse(BankAccount.isEmailValid("a..b@male.com"));
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));

        //Equivalence class being in first partitions, with boundary value to index[0]->indexOf(@)
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));

        //Equivalence class being in second partitions, with boundary value to indexOf(".")->end
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        //Equivalence class being in second partitions, with boundary value to indexOf("@")->indexOf(".")
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //Equivalence class being in third partitions, with boundary value to indexOf("@")->end
        assertFalse(BankAccount.isEmailValid("bc.def@mail"));
        //Equivalence class being in second partitions, with boundary value to indexOf("@")->indexOf(".")
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));

        //Equivalence class being in second partitions, with boundary value to indexOf("@)->indexOf(extensions)
        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
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