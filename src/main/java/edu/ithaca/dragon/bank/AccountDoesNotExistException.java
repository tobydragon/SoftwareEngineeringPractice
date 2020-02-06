package edu.ithaca.dragon.bank;

public class AccountDoesNotExistException extends Exception{

    public AccountDoesNotExistException(String s){
        super(s);
    }

}
