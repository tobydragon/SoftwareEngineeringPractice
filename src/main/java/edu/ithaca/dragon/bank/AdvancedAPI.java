package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String acctId, String email, String password, double startingBalance);

    public void closeAccount(String acctId);
}
