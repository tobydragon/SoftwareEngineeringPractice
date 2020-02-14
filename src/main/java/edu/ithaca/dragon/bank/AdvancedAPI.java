package edu.ithaca.dragon.bank;

//API to be used by Teller systems
public interface AdvancedAPI extends BasicAPI {

    public void createCustomerWithAccount(String actID, String password, double startingBalance) throws IllegalArgumentException;

    public void createCustomer(String acctId, String password) throws IllegalArgumentException;

    public void addAccount(String actID, double startingBalance) throws IllegalArgumentException;

    public void closeCustomer(String acctId) ;
}
