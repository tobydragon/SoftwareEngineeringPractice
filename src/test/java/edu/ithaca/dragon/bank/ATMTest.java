package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ATMTest {
    @Test
    void depositTest() throws Exception {
        //Equivalence class: valid amount-positive int
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("Mari", "love12");
        c1.createAccount("Mari", 100);

        ATM atm =new ATM(c1);

       atm.deposit("Mari",100);
        assertEquals(200, atm.checkBalance("Mari"));

        atm.deposit("Mari",1);
        assertEquals(201, atm.checkBalance("Mari"));

        atm.deposit("Mari",5000);
        assertEquals(5201, atm.checkBalance("Mari"));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () ->atm.deposit("Mari",-1));

        //Equivalence Class-IllegalArgumentException negative amount
        assertThrows(IllegalArgumentException.class, () -> atm.deposit("Mari",-10));

        //Equivalence Class-IllegalArgumentException  more than 2 decimal places
        assertThrows(IllegalArgumentException.class, () -> atm.deposit("Mari",1.908));





    }

    @Test
    void checkBalanceTest() throws Exception {
        //Equivalence Class-valid balance


        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("Mari", "love12");
        c1.createAccount("Mari", 100);

        ATM atm =new ATM(c1);

        assertEquals(100, atm.checkBalance("Mari"));

        //Equivalence Class- 0 starting balance
        c1.addCustomer("Ami", "yes101");
        c1.createAccount("Ami", 0);

        assertEquals(0, atm.checkBalance("Ami"));

        //Equivalence Class- balance >1000
        c1.addCustomer("Houry", "101NP");
        c1.createAccount("Houry", 5000);

        assertEquals(5000, atm.checkBalance("Houry"));




    }
}
