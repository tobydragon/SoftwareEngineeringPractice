<<<<<<< HEAD
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
<<<<<<< HEAD

=======
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1
import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
<<<<<<< HEAD
    void checkBalanceTest() {
        CentralBank centralBank  = new CentralBank("1", 200.0);
        assertEquals(200.0, centralBank.checkBalance("1"));

        CentralBank centralBank1 = new CentralBank("2", 500.0);
        assertEquals(500.0, centralBank1.checkBalance("2"));

    }


=======
    void checkBalanceTest(){
        CentralBank account1 = new CentralBank("1234", 600);

        assertEquals(600, account1.checkBalance("1234"));

        CentralBank account2 = new CentralBank("5678", 400);

        assertEquals(400, account2.checkBalance("5678"));
    }
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1
}
||||||| merged common ancestors
=======
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CentralBankTest {

    @Test
    void checkBalanceTest(){
        CentralBank account1 = new CentralBank("1234", 600);

        assertEquals(600, account1.checkBalance("1234"));

        CentralBank account2 = new CentralBank("5678", 400);

        assertEquals(400, account2.checkBalance("5678"));
    }
}
>>>>>>> d7f2e37a069825de396abfc726be296abe5170d1
