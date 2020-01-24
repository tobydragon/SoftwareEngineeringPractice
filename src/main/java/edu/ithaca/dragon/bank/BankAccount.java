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
    public void withdraw (double amount)  {
        balance -= amount;

    }


    public static boolean isEmailValid(String email){
        String prefix=email.substring(0,email.indexOf("@"));
        String domain=email.substring(email.indexOf("@")+1,(email.length()-(prefix.length()+1)));
        int prefixchar =(prefix.indexOf("-")+1);
        String domainend=domain.substring(domain.indexOf("."));
        if (email.indexOf('@') == -1){
            return false;
        }
        if(prefix.contains("_")){
            int a1 = prefix.indexOf("_");
            char a2 = prefix.charAt(a1+1);
            if(((int)a2>=48 &&(int)a2<58)||((int)a2>=65 &&(int)a2<91)||((int)a2>=97 &&(int)a2<123)){
                return true;
            }
            else{
                return false;
            }
        }
        if(prefix.contains("-")){
            int a1 = prefix.indexOf("-");
            char a2 = prefix.charAt(a1+1);
            if(((int)a2>=48 &&(int)a2<58)||((int)a2>=65 &&(int)a2<91)||((int)a2>=97 &&(int)a2<123)){
                return true;
            }
            else{
                return false;
            }
        }
        if(prefix.contains(".")){
            int a1 = prefix.indexOf(".");
            char a2 = prefix.charAt(a1+1);
            if(((int)a2>=48 &&(int)a2<58)||((int)a2>=65 &&(int)a2<91)||((int)a2>=97 &&(int)a2<123)){
                return true;
            }
            else{
                return false;
            }
        }

        if(domainend==".com"){
            return true;

        }
        else if(domainend==".cc"){
            return true;
        }
        else if(domainend==".org"){
            return true;
        }
        else { return false;
        }
        }

    }
        for(int i=0;i<prefix.length()-1;i=i+1){
            char x1 = prefix.charAt(i);
            if(((int)x1>=48 &&(int)x1<58)||((int)x1>=65 &&(int)x1<91)||((int)x1>=97 &&(int)x1<123)){
        }
        if((prefixchar==;){


        }

        if(){}
        else {
            return true;
        }
    }
}
