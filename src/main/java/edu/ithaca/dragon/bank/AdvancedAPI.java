package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(String acctId, double startingBalance, String emailIn);


    public void closeAccount(String acctId);
}
