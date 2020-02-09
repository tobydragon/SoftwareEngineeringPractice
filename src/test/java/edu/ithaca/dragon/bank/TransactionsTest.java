package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionsTest {

    @Test
    void transactionHistoryTest() throws AccountDoesNotExistException, AccountAlreadyExistsException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        CentralBank newAccount = new CentralBank();
        String testAccountID = "a@b.com";
        newAccount.createAccount(testAccountID, "password", 200, false);

        // Account has wrong accountID
        assertThrows(AccountDoesNotExistException.class, () -> newAccount.transactionHistory("like@comment.subscribe"));
        assertThrows(AccountDoesNotExistException.class, () -> newAccount.transactionHistory("c@d.com"));
        assertThrows(AccountDoesNotExistException.class, () -> newAccount.transactionHistory("c@d.com"));

        // Account has multiple withdraws
        newAccount.withdraw("a@b.com",10);
        newAccount.withdraw("a@b.com",120);
        newAccount.withdraw("a@b.com",40.65);
        newAccount.withdraw("a@b.com",200);
        newAccount.transactionHistory(testAccountID);

        // Account has multiple deposits
        newAccount.deposit("a@b.com",5);
        newAccount.deposit("a@b.com",50);
        newAccount.deposit("a@b.com",30.54);
        newAccount.deposit("a@b.com",130.0);
        newAccount.transactionHistory(testAccountID);

        // Account has multiple transfers
        String transferActID = "m@v.com";
        newAccount.createAccount(testAccountID, "password", 350, true);

        newAccount.transfer(testAccountID,transferActID, 50.34);
        newAccount.transfer(testAccountID,transferActID, 34.34);
        newAccount.transfer(testAccountID,transferActID, -2.34);
        newAccount.transfer(testAccountID,transferActID, -10);
        newAccount.transfer(testAccountID,transferActID, 190);

        newAccount.transactionHistory(testAccountID);
        newAccount.transactionHistory(transferActID);
    }
}
