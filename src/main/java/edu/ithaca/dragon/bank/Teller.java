package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Teller extends ATM implements AdvancedAPI {

    public Teller(CentralBank bank) {
        super(bank);

    }
        public void createAccount(String acctId, double startingBalance, boolean Savings){
            if(Savings){
                BankAccount acct= new SavingsAccount(startingBalance, acctId);
                bank.accounts.add(acct);

            }
            else if(!Savings){
                BankAccount acct= new CheckingAccount(startingBalance, acctId);
                bank.accounts.add(acct);
            }

        }


        public void closeAccount (String acctId){
            BankAccount acct=bank.getBankAccount(acctId);
            bank.accounts.remove(acct);

        }
    }

