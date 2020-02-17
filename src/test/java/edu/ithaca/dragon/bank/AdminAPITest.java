package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminAPITest {

    @Test
    public void findAcctsWithSuspiciousActivityTest() throws AccountAlreadyExistsException, InsufficientFundsException, AccountNotFoundException {
        CentralBank bank = new CentralBank();
        AdvancedAPI teller = bank;
        AdminAPI admin = bank;

        teller.createAccount("a@b.com", "pass",  200);
        teller.createAccount("a@c.com", "pass",  200);
        teller.createAccount("a@d.com", "pass",  200);

        teller.withdraw("a@b.com", 50);
        teller.deposit("a@b.com", 300);
        teller.withdraw("a@c.com", 150);
        teller.withdraw("a@d.com", 101);

        assertEquals(2, admin.findAcctIdsWithSuspiciousActivity().size());
    }
}
