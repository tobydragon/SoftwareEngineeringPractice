package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionsTest {

    @Test
    void transactionHistoryTest() throws AccountDoesNotExistException, AccountAlreadyExistsException,
            InsufficientFundsException, ExceedsMaxWithdrawalException, AccountFrozenException {

        //refactored this to "bank" because newAccount makes it sound like it's just one account and not all of them @Vera
        CentralBank bank = new CentralBank();
        String testAccountID = "a@b.com";
        bank.createAccount(testAccountID, "password", 2000, false, false);

        // Account has wrong accountID
        assertThrows(AccountDoesNotExistException.class, () -> bank.transactionHistory("like@comment.subscribe"));
        assertThrows(AccountDoesNotExistException.class, () -> bank.transactionHistory("c@d.com"));
        assertThrows(AccountDoesNotExistException.class, () -> bank.transactionHistory("c@d.com"));

        //test with no transactions
        assertEquals(null, bank.transactionHistory(testAccountID));

        // Account has multiple withdraws
        bank.withdraw("a@b.com",10);
        bank.withdraw("a@b.com",120);
        bank.withdraw("a@b.com",40.65);
        bank.withdraw("a@b.com",200);
        assertEquals("withdraw 10.00,withdraw 120.00,withdraw 40.65,withdraw 200.00",
                bank.transactionHistory(testAccountID));

        // Account has multiple deposits
        bank.deposit("a@b.com",5);
        bank.deposit("a@b.com",50);
        bank.deposit("a@b.com",30.54);
        bank.deposit("a@b.com",130.0);
        assertEquals("withdraw 10.00,withdraw 120.00,withdraw 40.65,withdraw 200.00," +
                        "deposit 5.00,deposit 50.00,deposit 30.54,deposit 130.00",
                bank.transactionHistory(testAccountID));

        // Account has multiple transfers
        String transferActID = "m@v.com";
        bank.createAccount(transferActID, "password", 350, true, false);

        bank.transfer(testAccountID,transferActID, 50.34);
        bank.transfer(testAccountID,transferActID, 34.34);
        bank.transfer(testAccountID,transferActID, 190);

        assertEquals("withdraw 10.00,withdraw 120.00,withdraw 40.65,withdraw 200.00," +
                        "deposit 5.00,deposit 50.00,deposit 30.54,deposit 130.00," +
                        "transfer to m@v.com 50.34,transfer to m@v.com 34.34,transfer to m@v.com 190.00",
                bank.transactionHistory(testAccountID));
        assertEquals("transfer from a@b.com 50.34,transfer from a@b.com 34.34,transfer from a@b.com 190.00",
                bank.transactionHistory(transferActID));
    }

    @Test
    void findAcctIdsWithSuspiciousActivity() throws AccountAlreadyExistsException, InsufficientFundsException,
            ExceedsMaxWithdrawalException, AccountDoesNotExistException, AccountFrozenException {
        CentralBank bank = new CentralBank();
        String testAccountID2 = "a@b.com";
        bank.createAccount(testAccountID2, "password", 1500, false, false);

        // With multiple withdraws
        bank.withdraw("a@b.com",50);
        bank.withdraw("a@b.com",170);
        bank.withdraw("a@b.com",213.54);
        bank.withdraw("a@b.com",130.43);
        bank.withdraw("a@b.com",400);
                bank.transactionHistory(testAccountID2);


        // With multiple transfers
        String transferActID = "m@v.com";
        bank.createAccount(transferActID, "password", 2000, true, false);

        bank.transfer(testAccountID2,transferActID, 80);
        bank.transfer(testAccountID2,transferActID, 44.84);
        bank.transfer(testAccountID2,transferActID, 123.34);
        bank.transfer(testAccountID2,transferActID, 22.97);
        bank.transfer(testAccountID2,transferActID, 190);
                bank.transactionHistory(testAccountID2);
                bank.transactionHistory(transferActID);
    }

    @Test
    void freezeAccount() throws AccountAlreadyExistsException, AccountDoesNotExistException {
        CentralBank bank = new CentralBank();
        String testAccountID3 = "a@b.com";
        bank.createAccount(testAccountID3, "password", 200, false, false);

        assertEquals(false, bank.isFrozen(testAccountID3));
                bank.freezeAccount(testAccountID3);
        assertEquals(true, bank.isFrozen(testAccountID3));
        //use bank account.isFrozen in the test assertion
    }

    @Test
    void unfreezeAcct() throws AccountAlreadyExistsException, AccountDoesNotExistException {
        CentralBank bank = new CentralBank();
        String testAccountID4 = "r@g.com";
        bank.createAccount(testAccountID4, "password", 200, false, true);

        //assertEquals(true, bank.isFrozen(testAccountID));
        bank.unfreezeAcct(testAccountID4);
        assertEquals(false, bank.isFrozen(testAccountID4));

        String frozeAccountID = "y@t.com";
        bank.createAccount(frozeAccountID, "password", 200, false, false);

        assertEquals(false, bank.isFrozen(frozeAccountID));
                bank.freezeAccount(frozeAccountID);
        assertEquals(true, bank.isFrozen(frozeAccountID));
                bank.unfreezeAcct(frozeAccountID);
    }
}
