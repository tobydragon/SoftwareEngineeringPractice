package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Teller extends ATM implements AdvancedAPI {
    CentralBank bank;

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

        }
    }

