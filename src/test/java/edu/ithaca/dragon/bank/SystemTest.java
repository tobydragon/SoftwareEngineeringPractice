package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

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
}
