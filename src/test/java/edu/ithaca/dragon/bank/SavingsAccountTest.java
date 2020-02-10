package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    void withdrawTest() throws IllegalArgumentException, InsufficientFundsException, ExceedsMaxWithdrawalException {
        BankAccount savings = new SavingsAccount("savings@bank.com", "password", 100, false);
        //withdraw more than in account
        assertThrows(InsufficientFundsException.class, ()-> savings.withdraw(100.01));
        assertThrows(InsufficientFundsException.class, ()-> savings.withdraw(200.50));
        assertThrows(InsufficientFundsException.class, ()-> savings.withdraw(499.99));

        BankAccount savings2 = new SavingsAccount("savings@bank.com", "password", 1000, false);
        //withdraw more than max withdrawal
        assertThrows(ExceedsMaxWithdrawalException.class, ()-> savings2.withdraw(500.01));
        assertThrows(ExceedsMaxWithdrawalException.class, ()-> savings2.withdraw(700.50));
        assertThrows(ExceedsMaxWithdrawalException.class, ()-> savings2.withdraw(1000));

        //invalid amount
        assertThrows(IllegalArgumentException.class, ()-> savings.withdraw(-9));
        assertThrows(IllegalArgumentException.class, ()-> savings.withdraw(9.12345));
        assertThrows(IllegalArgumentException.class, ()-> savings.withdraw(-9.12345));

        savings2.withdraw(0.01);
        assertEquals(999.99, savings2.getBalance());
        savings2.withdraw(500);
        assertEquals(499.99, savings2.getBalance());
        savings.withdraw(100);
        assertEquals(0, savings.getBalance());
    }

    @Test
    void compoundInterestTest() {
        BankAccount savings = new SavingsAccount("savings@bank.com", "password", 100, false);
        ((SavingsAccount) savings).compoundInterest();
        assertEquals(101.5, savings.getBalance());

        BankAccount savings2 = new SavingsAccount("savings@bank.com", "password", 1000, false);
        ((SavingsAccount) savings2).compoundInterest();
        assertEquals(1015.00, savings2.getBalance());
    }

}
