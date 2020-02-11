package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TellerTests {

    @Test
    public void userAccountTests() throws NonExistentAccountException{
        //tests opening/closing user accounts (not bank accounts)
        UserArrayList testList = new UserArrayList();
        UserAccount testAcct1 = new UserAccount("user1","pass1","a@b.com",1);
        UserAccount testAcct2 = new UserAccount("user2","pass2","b@c.com",2);
        UserAccount testAcct3 = new UserAccount("user3","pass3","c@d.com",3);

        Teller testTeller = new Teller(testList);

        testTeller.createUserAccount("user1","pass1","a@b.com",1);
        testTeller.closeUserAccount(1);
        assertThrows(NonExistentAccountException.class, () -> testTeller.closeUserAccount(1));
        //A little basic but not much else to cover with current plans
    }

}
