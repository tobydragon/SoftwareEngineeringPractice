package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserArrayListTest {
    @Test
    public void addFindRemoveTests() throws NonExistentAccountException{
       //Testing add, find, and remove at the same time

        UserArrayList testList = new UserArrayList();
        UserAccount testAcct1 = new UserAccount("user1","pass1","a@b.com",1);
        UserAccount testAcct2 = new UserAccount("user2","pass2","b@c.com",2);
        UserAccount testAcct3 = new UserAccount("user3","pass3","c@d.com",3);

        testList.addAccount(testAcct1);
        testList.addAccount(testAcct2);
        testList.addAccount(testAcct3);

        assertThrows(IllegalArgumentException.class, () -> testList.findAccount(123));
        //Making sure all were actually added
        assertEquals(testList.findAccount(1).getUserID(), testAcct1.getUserID());
        assertEquals(testList.findAccount(2).getUserID(), testAcct2.getUserID());
        assertEquals(testList.findAccount(3).getUserID(), testAcct3.getUserID());

        UserAccount testAcct4 = new UserAccount("user4","pass4","d@e.com",4);

        testList.removeAccount(testAcct1);
        assertThrows(IllegalArgumentException.class, () -> testList.findAccount(1));

        assertThrows(IllegalArgumentException.class, () -> testList.removeAccount(testAcct4));


    }
}
