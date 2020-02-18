package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    void createAccount(String email, double startingBalance, String acctType);

    void closeAccount(String email);

    void setDailyMax(String acctId, float amount) throws InsufficientFundsException;
    void setDailyMax(String email, String type, float amount) throws InsufficientFundsException;

    void setNewPassword();
}
