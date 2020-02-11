package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerCollectionTest {
    @Test
    void addCustomerTest(){
        CustomerCollection c1 = new CustomerCollection();
        assertEquals(0, c1.getNumCustomers());
        c1.addCustomer("bob", "password");
        assertEquals(1, c1.getNumCustomers());
        c1.addCustomer("bb", "password");
        assertEquals(2, c1.getNumCustomers());

        assertThrows(IllegalArgumentException.class, ()-> c1.addCustomer("bob", "password"));
    }

    @Test
    void addAccountTest() throws Exception{
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("bob", "password");
        c1.createAccount("bob", 100);
        assertEquals(100, c1.getBalance("bob"));
        c1.addCustomer("bb", "password");
        assertThrows(IllegalArgumentException.class, ()->c1.createAccount("bb", -100));
        assertThrows(IllegalArgumentException.class, ()->c1.createAccount("bb", 100.001));
        assertThrows(Exception.class, ()->c1.createAccount("bob", 100));
        assertThrows(IllegalArgumentException.class, ()->c1.createAccount("b", 100));
    }

    @Test
    void getBalanceTest() throws Exception{
        CustomerCollection c1 = new CustomerCollection();
        c1.addCustomer("bob", "password");
        c1.createAccount("bob", 100);
        assertEquals(100, c1.getBalance("bob"));
        c1.addCustomer("bb", "password");
        c1.createAccount("bb", 200);
        assertEquals(200, c1.getBalance("bb"));
        c1.addCustomer("bbb", "password");

        assertThrows(IllegalArgumentException.class, ()-> c1.getBalance("b"));
        assertThrows(IllegalArgumentException.class, ()-> c1.getBalance("bbb"));
    }
}
