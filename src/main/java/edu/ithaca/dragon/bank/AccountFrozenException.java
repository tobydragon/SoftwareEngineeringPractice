package edu.ithaca.dragon.bank;

public class AccountFrozenException extends Exception{

    public AccountFrozenException(String s){
        super(s);
    }

}