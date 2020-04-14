package edu.ithaca.dragon.bank;

import edu.ithaca.dragon.util.JsonUtil;

import java.io.IOException;

public class BankMain {

    public static void main (String[] args){
        System.out.println("Welcome to the Central Bank Software. Loading bank...");
        CentralBank centralBank;
        try {
           centralBank  = JsonUtil.fromJsonFile("src/main/resources/centralBankReal.json", CentralBank.class);
        }
        catch (IOException e){
            System.out.println("Warning: Unable to load central bank data, creating and working from new, empty central bank");
            centralBank = new CentralBank();
        }
        System.out.println("We currently have " + centralBank.numberOfAccounts() + " active accounts");

    }
}
