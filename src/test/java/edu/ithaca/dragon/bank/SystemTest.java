package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class SystemTest {

    @Test
    void SystemTest() throws InsufficientFundsException {
        CentralBank bank = new CentralBank();

        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");
        bank.users.add(user1);
        bank.users.add(user2);

        CheckingAccount acc1 = new CheckingAccount(1000, "acc1");
        SavingsAccount acc2 = new SavingsAccount(1000, "acc2", .01, 200);
        bank.accounts.add(acc1);
        bank.accounts.add(acc2);

        acc1.Deposit(100);
        acc2.Deposit(100);
        acc1.withdraw(200);
        acc2.withdraw(200);

        assertEquals(900, acc1.getBalance());
        assertEquals(900, acc2.getBalance());

        Admin admin = new Admin(bank);
        assertEquals(1800, admin.calcTotalAssets());

        Collection<String> expectedStr = new ArrayList<String>();
        assertEquals(expectedStr, admin.findAcctIdsWithSuspiciousActivity());
    }

    @Test
    void getTransactionHistoryTest() throws InsufficientFundsException {
        CentralBank bank = new CentralBank();

        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");
        bank.users.add(user1);
        bank.users.add(user2);

        CheckingAccount acc1 = new CheckingAccount(1000, "acc1");
        SavingsAccount acc2 = new SavingsAccount(1000, "acc2", .01, 200);
        bank.accounts.add(acc1);
        bank.accounts.add(acc2);

        acc1.Deposit(100);
        LocalDateTime time1 = LocalDateTime.now();
        acc2.Deposit(100);
        LocalDateTime time2 = LocalDateTime.now();
        acc1.withdraw(200);
        LocalDateTime time3 = LocalDateTime.now();
        acc2.withdraw(200);
        LocalDateTime time4 = LocalDateTime.now();

        Transaction acc1trn1 = new Transaction(1000, 100, 1100, time1, false);
        Transaction acc1trn2 = new Transaction(1100, -200, 900, time2, false);
        ArrayList<Transaction> acc1Trns = new ArrayList<Transaction>();
        acc1Trns.add(acc1trn1);
        acc1Trns.add(acc1trn2);
        assertEquals(acc1Trns.toString(), acc1.getTransactionHistory().toString());

        Transaction acc2trn1 = new Transaction(1000, 100, 1100, time3, false);
        Transaction acc2trn2 = new Transaction(1100, -200, 900, time4, false);
        ArrayList<Transaction> acc2Trns = new ArrayList<Transaction>();
        acc2Trns.add(acc2trn1);
        acc2Trns.add(acc2trn2);
        assertEquals(acc2Trns.toString(), acc2.getTransactionHistory().toString());
    }
}
