package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private String password;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, String password, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            if (isPasswordValid(password)) {
                this.password = password;
            } else {
                throw new IllegalArgumentException("Password must be at least 8 characters");
            }
            if(isAmountValid(startingBalance)){
                this.balance = startingBalance;
            }
            else{
                throw new IllegalArgumentException("Starting Balance: " + startingBalance + " is invalid, cannot create account");
            }
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw(double amount) throws InsufficientFundsException{
        if(balance < 0){
            balance = 0;
        }
        else if(amount > balance){
            return;
        }
        else if(isAmountValid(amount) == false){
            throw new InsufficientFundsException("Amount: " + amount + "is invalid");
        }
        balance -= amount;
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() >= 8) return true;
        else return false;
    }

    private static boolean isExtensionValid(String extension){ //Private method to check if extension is valid
        if (extension.indexOf('.') != -1) { //Check if a period is in the extension
            return false;
        }
        return true;
    }

    private static boolean isDomainValid(String domain) { //Private method to check if extension is valid
        if (domain.indexOf('.') == -1) {
            return false;
        } else if (domain.indexOf('.') == 0) { //Check if a period is in the place of the @ symbol
            return false;
        } else if (domain.indexOf('@') != -1) { //Check if the @ symbol is in the domain
            return false;
        } else if (domain.indexOf('-') != -1) { //Check if a - is in the domain
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(String email) { //Public method to check if email is valid
        if (email.indexOf('@') == -1 || email.indexOf('@') == 0) { //Check if @ symbol is gone or in front of address
            return false;
        } else if (email.indexOf("..") != -1) { //Check if .. is in the address
            return false;
        } else if (email.indexOf('#') != -1) { //Check if # is in the address
            return false;
        } else if (email.indexOf("-@") != -1) { //Check if -@ is in the address
            return false;
        } else if (email.indexOf('-') == 0) { //Check if - is in the the front of the address
            return false;
        } else if (email.indexOf('.') == 0) { //Check if . is in the front of the address
            return false;
        } else if (email.charAt(email.length() - 2) == '.' || email.charAt(email.length() - 1) == '.') { //Check if a period is in multiple areas of the address
            return false;
        } else {
            String domain = email.substring(email.indexOf('@') + 1);

            if (isDomainValid(domain) == false){ //Calling private method isDomainValid()
                return false;
            } else {
                String extension = domain.substring(domain.indexOf('.') + 1);
                if(isExtensionValid(extension) == false){ //Calling private method isExtensionValid()
                    return false;
                }
                return true; //Email is valid
            }
        }
    }

    /**
     *
     * @param amount
     * should return false if the amount has more than 2 decimal points or is not positive
     * @return
     */
    public static boolean isAmountValid(double amount){
        if(amount >= 0){
            String stAmount = "" +amount;
            String decimalPoints = stAmount.substring(stAmount.indexOf(".")+1);
            if(decimalPoints.length() > 2){
                return false;
            }
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @param amount
     * Changes the balance by adding a positive amount of money as a double
     * Will change balance to 0 if balance is under 0
     * Will not change the balance amount if the amount is under 0
     */
    public void deposit(double amount){
        if(balance < 0){
            balance = 0;
        }
        if(amount < 0){
            return;
        }
        balance += amount;
    }

    /**
     *
     * @param account1
     * @param account2
     * @param amountWanted
     * Will exchange the a certain amount of money to the first account from the second account
     * Will make the wantedAmount 0 if the second account has nothing or less than money to transfer
     */
    public static void transfer(BankAccount account1, BankAccount account2, double amountWanted) throws InsufficientFundsException {
        if(account2.getBalance() <= 0 || account2.getBalance() < amountWanted){
            amountWanted = 0;
        }
        account1.deposit(amountWanted);
        account2.withdraw(amountWanted);
    }

}

