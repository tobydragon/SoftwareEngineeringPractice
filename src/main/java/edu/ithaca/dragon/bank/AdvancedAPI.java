package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public BankAccount createAccount(String acctId, double startingBalance);

    public void closeAccount(String acctId);
}
