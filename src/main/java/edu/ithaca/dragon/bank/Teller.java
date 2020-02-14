package edu.ithaca.dragon.bank;

public class Teller extends ATM implements AdvancedAPI {


    /**
     *
     * Creates a checking account
     * @throws IllegalArgumentException
     */
    public void createAccount(String acctId, String name, String password, double startingBalance) throws IllegalArgumentException{
        if (acctId.length() != 10){
            throw new IllegalArgumentException("Account ID must be 10 digits");
        }
        if (startingBalance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (!isNameValid(name)){
            throw new IllegalArgumentException("Invalid name");
        }
        admin.createCheckingForTeller(acctId,name,password,startingBalance);
    }

    /**
     *
     * Creates a savings account
     * @throws IllegalArgumentException
     */
    public void createAccount(String acctId, String name, String password, double startingBalance, double interestRate, double maxWithdraw) throws IllegalArgumentException{
        if (acctId.length() != 10){
            throw new IllegalArgumentException("Account ID must be 10 digits");
        }
        if (startingBalance < 0){
            throw new IllegalArgumentException("Balance cannot be negative");
        }if (!isAmountValid(interestRate)){
            throw new IllegalArgumentException("Interest rate cannot be less than or equal to 0");
        }
        if (!isAmountValid(maxWithdraw)){
            throw new IllegalArgumentException("Max withdrawal cannot be less than or equal to 0");
        }
        if (!isNameValid(name)){
            throw new IllegalArgumentException("Invalid name");
        }
        admin.createSavingsForTeller(acctId, name, password, startingBalance, interestRate, maxWithdraw);
    }

    public void closeAccount(String acctId) throws IllegalArgumentException, AcctFrozenException{
        if (admin.getAccount(acctId) == null){
            throw new IllegalArgumentException("Account does not exist");
        }
        if (admin.getAccount(acctId).getFrozenStatus()){
            throw new AcctFrozenException("Cannot close a frozen account");
        }
        admin.closeAccount(acctId);
    }

    public static boolean isNameValid(String name){
        if (name.length() < 3) return false;

        int spaceCount = 0;
        //check there's at least one space
        for (int i = 0; i < name.length()-1; i++){
            if(name.charAt(i) == ' '){
                spaceCount++;
            }
        }
        if(spaceCount < 1) return false;

        for (int i = 0; i < name.length(); i++){
            if(Character.isLetter(name.charAt(i)) || name.charAt(i) == ' '){
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean isAmountValid(double amount){
        if (amount <= 0){
            return false;
        }

        // check number of decimal places
        String checkDouble = Double.toString(amount);
        int indexDecimal = checkDouble.indexOf('.');
        return checkDouble.length() - indexDecimal <= 3;
    }
}
