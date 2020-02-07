package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void withdrawTest()throws InsufficientFundsException, IllegalArgumentException, AccountAlreadyExistsException, AccountDoesNotExistException {
        CentralBank newAccount = new CentralBank();
        String newAccountID = "email@test.com";
        newAccount.createAccount(newAccountID, 200);

        assertThrows(AccountDoesNotExistException.class, () -> newAccount.withdraw("notemail@test.com", 100));

        assertThrows(InsufficientFundsException.class, () -> newAccount.withdraw("email@test.com", 201)); //border case
        assertThrows(InsufficientFundsException.class, () -> newAccount.withdraw("email@test.com", 350));
        assertThrows(InsufficientFundsException.class, () -> newAccount.withdraw("email@test.com", 1000)); //border case

        //Withdraw equals or less than is in account
        newAccount.withdraw("email@test.com", 1);
        assertEquals(199, newAccount.checkBalance("email@test.com")); //border case
        newAccount.withdraw("email@test.com",100);
        assertEquals(99, newAccount.checkBalance("email@test.com"));
        newAccount.withdraw("email@test.com",99);
        assertEquals(0, newAccount.checkBalance("email@test.com")); //border case

        // Negative, One to Two Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-1.01)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-53.83));
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-9999999.9)); // border case

        // Negative, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-1.0000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-7.48));
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",-9999999.9999999)); // border case

        // Positive, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",0.000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",92.498865));
        assertThrows(IllegalArgumentException.class, () -> newAccount.withdraw("email@test.com",9999999.999999)); //border case
    }

    @Test
    void depositTest() throws AccountAlreadyExistsException, AccountDoesNotExistException {
        CentralBank newAccount = new CentralBank();
        String newAccountID = "email@test.com";
        newAccount.createAccount(newAccountID, 200);

        // Negative, One to Two Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-1.01)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-53.83));
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-9999999.9)); // border case

        // Positive, One to Two Decimals
        newAccount.deposit("email@test.com",0);
        assertEquals(200, newAccount.checkBalance("email@test.com")); //border case
        newAccount.deposit("email@test.com",100);
        assertEquals(300, newAccount.checkBalance("email@test.com"));
        newAccount.deposit("email@test.com",999);
        assertEquals(1299, newAccount.checkBalance("email@test.com")); //border case

        // Negative, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-1.0000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-7.48));
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",-9999999.9999999)); // border case

        // Positive, Multiple Decimals
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",0.000001)); // border case
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",92.498865));
        assertThrows(IllegalArgumentException.class, () -> newAccount.deposit("email@test.com",9999999.999999)); //border case
    }

    @Test
    void accountExistsTest() throws AccountAlreadyExistsException, IllegalArgumentException{
        CentralBank bank = new CentralBank();
        bank.createAccount("yes@yes.com", 0);
        assertTrue(bank.accountExists("yes@yes.com"));
        assertFalse(bank.accountExists("nope@nope.com"));
    }

    @Test
    void createAccountTest() throws AccountAlreadyExistsException, IllegalArgumentException {

        CentralBank bank = new CentralBank();

        //equivalence classes

        //account created
        //good id and start balance
        String id1 = "a@b.com";
        bank.createAccount(id1, 0);
        assertTrue(bank.accountExists(id1));
        assertEquals(0, bank.checkBalance(id1));

        String id2 = "b@c.com";
        bank.createAccount(id2, 100.5);
        assertTrue(bank.accountExists(id2));
        assertEquals(100.5, bank.checkBalance(id2));

        //this project does not allow dashes in the domain
        //String id3 = "c.long.email@d-long-email.com";
        String id3 = "test.test.test@email.com";
        bank.createAccount(id3, 100000.86);
        assertTrue(bank.accountExists(id3));
        assertEquals(100000.86, bank.checkBalance(id3));


        //account not created
        //invalid id/email
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("#bad", 100));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("bad..email@bad-.com", 100));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("bad@bad.c", 100));

        //id already exists
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id1, 100));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id2, 100));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id3, 100));

        //invalid start balance
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("c@d.com", -0.01));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("d@e.com", 100.999));
        assertThrows(IllegalArgumentException.class, ()-> bank.createAccount("e@f.com", -5.055));

        //invalid id and start balance
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id1, -0.01));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id2, 100.999));
        assertThrows(AccountAlreadyExistsException.class, ()-> bank.createAccount(id3, -5.055));


    }

    @Test
    void calcTotalAssetsTest() throws AccountAlreadyExistsException, IllegalArgumentException, AccountDoesNotExistException {
        CentralBank bank = new CentralBank();

        //equivalence class - bank has accounts
        //border
        bank.createAccount("a@b.com", 0);
        bank.createAccount("b@c.com", 0);
        bank.createAccount("c@d.com", 0);
        bank.createAccount("d@e.com", 0);

        assertEquals(0, bank.calcTotalAssets());

        bank = new CentralBank();
        bank.createAccount("a@b.com", 100.50);
        bank.createAccount("b@c.com", 150.05);
        bank.createAccount("c@d.com", 200.50);
        bank.createAccount("d@e.com", 250.05);

        assertEquals(701.10, bank.calcTotalAssets());

        //border
        bank = new CentralBank();
        bank.createAccount("a@b.com", 100000);
        bank.createAccount("b@c.com", 500000);
        bank.createAccount("c@d.com", 1000000);
        bank.createAccount("d@e.com", 5000000);

        assertEquals(6600000, bank.calcTotalAssets());


        //equivalence class - bank has no accounts
        CentralBank bank2 = new CentralBank();
        assertThrows(AccountDoesNotExistException.class, ()-> bank2.calcTotalAssets());

    }


}
