package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuspiciousTest {

    @Test
    void suspiciousActivityTest() throws AccountAlreadyExistsException, AccountDoesNotExistException, AccountFrozenException,
            IllegalArgumentException, InsufficientFundsException, ExceedsMaxWithdrawalException{
        CentralBank bank = new CentralBank();

        bank.createAccount("goodguy@bank.com", "password", 500, false);
        bank.createAccount("okayokay@bank.com", "password", 500, false);
        bank.createAccount("suspicious@bank.com", "password", 500, false);
        bank.createAccount("suspicious2@bank.com", "password", 500, true);
        bank.createAccount("badguy@bank.com", "password", 500, false);
        bank.createAccount("accomplice@bank.com", "password", 500, true);

        //just a variety of deposits and withdrawals
        bank.deposit("goodguy@bank.com", 1500);
        bank.deposit("goodguy@bank.com", 200);
        bank.withdraw("goodguy@bank.com", 702.50);
        bank.deposit("goodguy@bank.com", 750);
        bank.withdraw("goodguy@bank.com", 100);
        bank.withdraw("goodguy@bank.com", 1000);
        bank.deposit("goodguy@bank.com", 1536.42);
        bank.withdraw("goodguy@bank.com", 1200.51);
        bank.withdraw("goodguy@bank.com", 25.00);
        bank.deposit("goodguy@bank.com", 207.40);

        //a withdraws and deposits with one big out there one
        bank.deposit("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 500);
        bank.deposit("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 500);
        bank.deposit("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 500);
        bank.deposit("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 500);
        bank.deposit("suspicious@bank.com", 500);
        bank.deposit("suspicious@bank.com", 500);
        bank.withdraw("suspicious@bank.com", 1000);

        //another one but this time it is a small deposit instead of a big withdraw (to test both ends)
        bank.deposit("suspicious2@bank.com", 500);
        bank.withdraw("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 500);
        bank.withdraw("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 500);
        bank.withdraw("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 500);
        bank.withdraw("suspicious2@bank.com", 500);
        bank.withdraw("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 500);
        bank.deposit("suspicious2@bank.com", 100);

        //selection of withdraws and transfers - should all be considered okay
        bank.deposit("okayokay@bank.com", 50000); //big deposit but only one to get it started
        bank.withdraw("okayokay@bank.com", 1000);
        bank.transfer("okayokay@bank.com", "goodguy@bank.com", 500);
        bank.withdraw("okayokay@bank.com", 1000);
        bank.withdraw("okayokay@bank.com", 1000);
        bank.transfer("okayokay@bank.com", "goodguy@bank.com", 500);
        bank.transfer("okayokay@bank.com", "goodguy@bank.com", 500);
        bank.withdraw("okayokay@bank.com", 1000);
        bank.transfer("okayokay@bank.com", "suspicious@bank.com", 500);
        bank.transfer("okayokay@bank.com", "badguy@bank.com", 500);
        bank.transfer("okayokay@bank.com", "goodguy@bank.com", 500);
        bank.withdraw("okayokay@bank.com", 1000);

        //suspicious transfers - too much money going out
        bank.deposit("badguy@bank.com", 50000);
        bank.transfer("badguy@bank.com", "accomplice@bank.com", 10000);
        bank.transfer("badguy@bank.com", "suspicious@bank.com", 10000);
        bank.transfer("badguy@bank.com", "accomplice@bank.com", 10000);
        bank.transfer("badguy@bank.com", "suspicious@bank.com", 10000);
        bank.transfer("badguy@bank.com", "accomplice@bank.com", 10000);

        Collection<String> suspicious = new HashSet<>();
        suspicious.add("suspicious@bank.com");
        suspicious.add("suspicious2@bank.com");
        suspicious.add("badguy@bank.com");
        suspicious.add("accomplice@bank.com");
        assertEquals(suspicious, bank.findAcctIdsWithSuspiciousActivity());


    }

}
