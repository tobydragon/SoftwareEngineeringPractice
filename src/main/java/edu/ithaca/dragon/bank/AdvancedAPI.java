package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createAccount(User user, String acctId, double startingBalance, boolean Savings);

    public void closeAccount(User user, String acctId);
}
