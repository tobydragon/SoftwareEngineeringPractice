package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200.34, "password");//middle balance equivalence class
        assertEquals(200.34, bankAccount.getBalance());

        BankAccount bankAccount2 = new BankAccount("a@h.com", 5.10, "password");//low balance equivalence class
        assertEquals(5.10, bankAccount2.getBalance());

        BankAccount bankAccount3 = new BankAccount("e@h.com", 1000.00, "password");//high balance equivalence class
        assertEquals(1000.00, bankAccount3.getBalance());

        BankAccount bankAccount4 = new BankAccount("g@u.com", 0.00, "password");// 0 balance equivalence class
        assertEquals(0.00, bankAccount4.getBalance());
    }

    @Test
    void depositTest() throws InsufficientFundsException {
        BankAccount testAccount = new BankAccount("g@u.com", 1637.72, "password");

        //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(487.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(48.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(4.735));

        //negative number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-487.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-48.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-4.735));

        //negative number no decimal
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-1));//border for negative amount 1 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-10));//border for negative amount 2 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.deposit(-100));//border for negative amount 3 digit

        //Positive number no decimals
        BankAccount testAccount2 = new BankAccount("h@j.com", 5500.50, "password");

        testAccount2.deposit(5.00); //single digit equivalence class
        assertEquals(5505.50, testAccount2.getBalance());
        testAccount2.withdraw(5.00);

        testAccount2.deposit(50.00); //double digit equivalence class
        assertEquals(5550.50, testAccount2.getBalance());
        testAccount2.withdraw(50.00);

        testAccount2.deposit(500.00);//triple digit equivalence class
        assertEquals(6000.50, testAccount2.getBalance());
        testAccount2.withdraw(500.00);

        testAccount2.deposit(5000.00);//4 digit equivalence class
        assertEquals(10500.50, testAccount2.getBalance());

        //Positive number with decimals
        BankAccount testAccount3 = new BankAccount("a@u.com", 3500.50, "password");

        testAccount3.deposit(9.35);//1 digit equivalence class
        assertEquals(3509.85, testAccount3.getBalance(), 1e-8);
        testAccount3.withdraw(9.35);

        testAccount3.deposit(87.29);// 2 digit equivalence class
        assertEquals(3587.79, testAccount3.getBalance(), 1e-8);
        testAccount3.withdraw(87.29);

        testAccount3.deposit(416.12);//3 digit equivalence class
        assertEquals(3916.62, testAccount3.getBalance(), 1e-8);
        testAccount3.withdraw(416.12);

        testAccount3.deposit(2945.67);
        assertEquals(6446.17, testAccount3.getBalance(), 1e-8);
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        BankAccount testAccount = new BankAccount("g@u.com", 1637.72, "password");
        BankAccount transferHere = new BankAccount("d@k.com", 150.00, "password");

        //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(487.735, transferHere));//border for 3 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(48.735, transferHere));//border for 2 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(4.735, transferHere));//border for 1 digit

        //negative number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-487.735, transferHere));//border for negative 3 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-48.735, transferHere));//border for negative 2 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-4.735, transferHere));//border for negative 1 digit

        //negative number no decimal
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-1, transferHere));//border for negative amount 1 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-10, transferHere));//border for negative amount 2 digit
        assertThrows(IllegalArgumentException.class, ()-> testAccount.transfer(-100, transferHere));//border for negative amount 3 digit

        //Positive number no decimals
        BankAccount testAccount2 = new BankAccount("h@j.com", 5500.50, "password");
        BankAccount transferAccount2 = new BankAccount("t@n.com", 200, "password");

        testAccount2.transfer(5.00, transferAccount2); //single digit equivalence class
        assertEquals(205, transferAccount2.getBalance());
        transferAccount2.withdraw(5.00);
        testAccount.deposit(5.00);

        testAccount2.transfer(50.00, transferAccount2); //double digit equivalence class
        assertEquals(250, transferAccount2.getBalance());
        transferAccount2.withdraw(50.00);
        testAccount2.deposit(50.00);

        testAccount2.transfer(500.00, transferAccount2);//triple digit equivalence class
        assertEquals(700, transferAccount2.getBalance());
        transferAccount2.withdraw(500.00);
        testAccount2.deposit(500.00);

        testAccount2.transfer(5000.00, transferAccount2);//4 digit equivalence class
        assertEquals(5200, transferAccount2.getBalance());

        //Positive number with decimals
        BankAccount testAccount3 = new BankAccount("a@u.com", 3500.50, "password");
        BankAccount transferAccount3 = new BankAccount("b@u.com", 1800.89, "password");

        testAccount3.transfer(9.35, transferAccount3);//1 digit equivalence class
        assertEquals(1810.24, transferAccount3.getBalance(), 1e-8);
        transferAccount3.withdraw(9.35);

        testAccount3.transfer(87.29, transferAccount3);// 2 digit equivalence class
        assertEquals(1888.18, transferAccount3.getBalance(), 1e-8);
        transferAccount3.withdraw(87.29);

        testAccount3.transfer(416.12, transferAccount3);//3 digit equivalence class
        assertEquals(2217.01, transferAccount3.getBalance(), 1e-8);
        transferAccount3.withdraw(416.12);

        testAccount3.transfer(2945.67, transferAccount3);// 4 digit equivalence class
        assertEquals(4746.56, transferAccount3.getBalance(), 1e-8);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@f.com", 200.73, "password");
        //assertEquals(200.73, bankAccount.getBalance(), 1e-8);//equivalence for amount greater than balance
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.withdraw(400));

        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));//equivalence for negative amount

        bankAccount.withdraw(50);
        assertEquals(150.73, bankAccount.getBalance(), 1e-8);//equivalence for double digit amount

        BankAccount bankAccount2 = new BankAccount("a@h.com", 300.27, "password");
        assertThrows(InsufficientFundsException.class, ()-> bankAccount2.withdraw(301));//border for amount greater than balance

        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-1));//border for negative amount

        bankAccount2.withdraw(150);
        assertEquals(150.27, bankAccount2.getBalance(), 1e-8);//equivalence for triple digit (middle) amount


        bankAccount2.withdraw(0);
        assertEquals(150.27, bankAccount2.getBalance(), 1e-8);

        BankAccount testAccount = new BankAccount("d@a.com", 1000.00, "password");
        //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(487.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(48.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(4.735));

        //negative number three decimals
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(-487.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(-48.735));
        assertThrows(IllegalArgumentException.class, ()-> testAccount.withdraw(-4.735));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200, "password");

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        assertEquals("password", bankAccount.getPassword());


        //positive number three decimals
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", 100.763, "password"));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", 10.763, "password"));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", 1.763, "password"));
        //negative number three decimals
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", -100.763, "password"));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", -10.763, "password"));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("h@j.com", -1.763, "password"));
    }

    @Test
    void isAmountValidTest(){

        //Negative number
        assertFalse(BankAccount.isAmountValid(-5.276));
        assertFalse(BankAccount.isAmountValid(-50.673));
        assertFalse(BankAccount.isAmountValid(-100.736));
        assertFalse(BankAccount.isAmountValid(-1672.976));

        //Positive Number three decimal places
        assertFalse(BankAccount.isAmountValid(7.505));
        assertFalse(BankAccount.isAmountValid(35.825));
        assertFalse(BankAccount.isAmountValid(206.537));
        assertFalse(BankAccount.isAmountValid(1598.001));

        //Positive number two decimal places (Test that program also recognizes correct user input
        assertTrue(BankAccount.isAmountValid(4.50));
        assertTrue(BankAccount.isAmountValid(97.38));
        assertTrue(BankAccount.isAmountValid(427.94));
        assertTrue(BankAccount.isAmountValid(2374.62));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid("correct@test.com")); //Equivalence for simple correct email address
        assertFalse(BankAccount.isEmailValid("incorrect@test"));

        //Email with/without "#" character
        assertFalse(BankAccount.isEmailValid("incor#rect@test.com")); //border
        assertFalse(BankAccount.isEmailValid("incorrect@te#st.com"));//border

        //Email with "-, ., _" prefixes
        assertTrue(BankAccount.isEmailValid("cor-rect@test.com")); //Equivalence for correctly used "-" in email
        assertTrue(BankAccount.isEmailValid("cor_rect@test.com")); //Equivalence for correctly used "_" in email
        assertTrue(BankAccount.isEmailValid("cor.rect@test.com")); //Equivalence for correctly used "." in email
        assertFalse(BankAccount.isEmailValid("incorrect-@test.com")); //Equivalence for "-" with no letter/number following
        assertFalse(BankAccount.isEmailValid("incorrect_@test.com"));//Equivalence for "_" with no letter/number following
        assertFalse(BankAccount.isEmailValid("incorrect.@test.com"));//Equivalence for "." with no letter/number following
        assertFalse(BankAccount.isEmailValid(".incorrect@test.com"));//Border for "." at beginning
        assertFalse(BankAccount.isEmailValid("-incorrect@test.com"));//Border for "-" at beginning
        assertFalse(BankAccount.isEmailValid("_incorrect@test.com"));//Border for "_" at beginning
        assertFalse(BankAccount.isEmailValid("incorrect@test.com."));//Border for "." at end
        assertFalse(BankAccount.isEmailValid("incorrect@test.com-"));//Border for "-" at end
        assertFalse(BankAccount.isEmailValid("incorrect@test.com_"));//Border for "_" at end
        assertFalse(BankAccount.isEmailValid("incor..rect@test.com"));//Equivalence for repeated "."
        assertFalse(BankAccount.isEmailValid("incor--rect@test.com"));//Equivalence for repeated "-"
        assertFalse(BankAccount.isEmailValid("incor__rect@test.com"));//Equivalence for repeated "_"

        //Email with "-" domain
        assertTrue(BankAccount.isEmailValid("correct@te-st.com")); //Equivalence for correctly used "-" in domain
        assertFalse(BankAccount.isEmailValid("incorrect@test.c")); //Equivalence for last portion being less than 2 letters
        assertFalse(BankAccount.isEmailValid("incorrect@test..com")); //Equivalence for two "." in ".com"


    }

}