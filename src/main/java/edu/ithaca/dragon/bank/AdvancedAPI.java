package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String acctId, double startingBalance, UserArrayList userAccounts);

    public void closeAccount(String acctId);
}
