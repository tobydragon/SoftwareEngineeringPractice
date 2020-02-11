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
}
