package edu.ithaca.dragon.bank;

import java.util.Collection;

public class Teller extends ATM implements AdvancedAPI {
    CentralBank bank;

    public Teller(CentralBank bank) {
        super(bank);

    }
        public void createAccount(String acctId, double startingBalance, boolean Savings){

        }


        public void closeAccount (String acctId){

        }
    }

