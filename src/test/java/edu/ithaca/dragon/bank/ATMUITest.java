package edu.ithaca.dragon.bank;

import java.util.Scanner;

public class ATMUITest {
    public static void main(String[] args) {
        CentralBank testBank = new CentralBank("NewBank");
        BasicAPI testAPI = testBank;
        ATMUI testAtmUI = new ATMUI();

        System.out.println("Please enter account id and password: \n");
        Scanner userIn = new Scanner(System.in);
        String input = userIn.nextLine();



    }
}
