package edu.ithaca.dragon.bank;



public class ATMUITest {
    public static void main(String[] args) throws InsufficientFundsException {
        CentralBank testBank = new CentralBank("NewBank");
        ATMUI testAtmUI = new ATMUI(testBank);

        testBank.createAccount("123", 500.00,"password", false);

        //user with just checking
        testBank.createAccount("789", 2500.0,"comp345",false);

        //user with just savings
        testBank.createAccount("456", 2000.0,"password",true);

        testBank.freezeAccount("789");




        testAtmUI.logIn();
    }
}
