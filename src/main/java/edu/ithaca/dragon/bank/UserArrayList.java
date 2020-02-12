package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class UserArrayList {

    private ArrayList<UserAccount> userAccounts;

    //constructor
    public UserArrayList(){
        userAccounts = new ArrayList<UserAccount>();
    }

    /**
     * finds account by id in the list
     * @param userID
     * @return requested userAccount for further use
     * @throws IllegalArgumentException if account not found
     */
    public UserAccount findAccount(int userID) throws IllegalArgumentException {
        for (int i = 0; i < userAccounts.size(); i++){
            if (userAccounts.get(i).getUserID() == userID){
                return userAccounts.get(i);
            }
        }
        throw new IllegalArgumentException("Account not found"); //Throws error if account not found
    }

    /**
     * Adds a given userAccount to the arraylist after auto-assigning a valid userID to it.
     * @param newAccount
     */
    public void validAddAccount(UserAccount newAccount){
        assignValidID(newAccount);
    }

    /**
     * Adds a given userAccount to the arraylist
     * @param newAccount
     */
    public void addAccount(UserAccount newAccount){
        userAccounts.add(newAccount);
    }

    /**
     * removes given userAccount from the arraylist
     * @param account
     * @return copy of the removed Useraccount
     */
    public UserAccount removeAccount(UserAccount account) throws IllegalArgumentException{ //TODO might want to change to take userID
        if (userAccounts.indexOf(account) == -1){
            throw new IllegalArgumentException("Account not in arraylist");
        }
        UserAccount removedAccount = userAccounts.get(userAccounts.indexOf(account));
        userAccounts.remove(account);
        return removedAccount;
    }

    public int getTotalNumberAccounts(){
        return userAccounts.size();
    }

    public void assignValidID(UserAccount newAccount){
        userAccounts.add(new UserAccount(newAccount.getUsername(), newAccount.getPassword(), newAccount.getEmail(), userAccounts.size()));
    }
}
