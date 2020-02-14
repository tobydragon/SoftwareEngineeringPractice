package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String email, double startingBalance, String acctType);

    public void closeAccount(String email);
}
