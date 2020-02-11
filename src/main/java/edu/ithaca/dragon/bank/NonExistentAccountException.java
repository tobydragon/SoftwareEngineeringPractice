package edu.ithaca.dragon.bank;

public class NonExistentAccountException extends Exception{
    public NonExistentAccountException(String s){
        super(s);
    }
}
