package edu.ithaca.dragon.bank;

public class ATMUITest {
    public static void main(String[] args) {
        CentralBank testBank = new CentralBank("NewBank");
        BasicAPI testAPI = testBank;
        ATMUI testAtmUI = new ATMUI();
    }
}
