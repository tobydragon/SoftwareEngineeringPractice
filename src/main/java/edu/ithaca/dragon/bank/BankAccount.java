package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
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
    public void withdraw (double amount){
        if(balance < 0){
            balance = 0;
        }
        if(amount > balance){
            return;
        }
        balance -= amount;
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1||email.indexOf('@')==0){
            return false;
        }

        else if(email.indexOf("..") != -1){
            return false;
        }

        else if(email.indexOf('#')!= -1){
            return false;
        }

        else if(email.indexOf("-@") != -1){
            return false;
        }

        else if(email.indexOf('-')==0){
            return false;
        }

        else if(email.indexOf('.') == 0){
            return false;
        }

        else if(email.charAt(email.length()-2)=='.' || email.charAt(email.length()-1)=='.'){
            return false;
        }

        else {
            String domain = email.substring(email.indexOf('@')+1);
            if(domain.indexOf('.') == -1){
                return false;
            }

            else if(domain.indexOf('.')==0){
                return false;
            }

            else if(domain.indexOf('@')!= -1){
                return false;
            }

            else if(domain.indexOf('-')!= -1){
                return false;
            }

            String extension = domain.substring(domain.indexOf('.')+1);
            if(extension.indexOf('.') != -1){
                return false;
            }
            return true;
        }
    }
}
