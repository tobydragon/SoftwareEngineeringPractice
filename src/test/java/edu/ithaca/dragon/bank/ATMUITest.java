package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class ATMUITest {

    @Test
    public static void main(String[] args){
        CentralBank testBank = new CentralBank();
        AdvancedAPI setupTeller = testBank;
        SetUpBank(setupTeller);
        ATMUI testUI = new ATMUI(testBank);

        Scanner sc = new Scanner(System.in);
        String output;

        do{
            String input = sc.nextLine();
            output = testUI.parse(input);
            if(output != "quit")
                System.out.println(output);
        }while(!output.equals("quit"));
    }

    private static void SetUpBank(AdvancedAPI setupTeller) {

    }
}
