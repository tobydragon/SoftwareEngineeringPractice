package edu.ithaca.dragon.bank;



public class ATMUITest {
    public static void main(String[] args) throws InsufficientFundsException {
        CentralBank testBank = new CentralBank("NewBank");
        ATMUI testAtmUI = new ATMUI(testBank);

        testBank.createAccount("tester1", 1000.0,"password", false);

        //user with just checking
        testBank.createAccount("tester2", 2500.0,"password",false);

        //user with just savings
        testBank.createAccount("tester3", 2000.0,"password",true);


        testAtmUI.logIn();
    }
}
