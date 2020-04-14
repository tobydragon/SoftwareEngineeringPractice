package edu.ithaca.dragon.bank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentralBank {

    private Map<String, BankAccount> accountMap;

    public CentralBank(){
        this.accountMap = new HashMap<>();
    }

    public void addAccounts(List<BankAccount> accountList) {
        for (BankAccount account: accountList){
            this.accountMap.put(account.getEmail(), account);
        }
    }

    public int numberOfAccounts(){
        return accountMap.size();
    }


    //-------- for json processing  -----------//
    public Map<String, BankAccount> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, BankAccount> accountMap) {
        this.accountMap = accountMap;
    }
}
