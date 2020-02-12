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

        assertThrows(NonExistentAccountException.class, () -> testList.findAccount(123));
        //Making sure all were actually added
        assertEquals(testList.findAccount(1).getUserID(), testAcct1.getUserID());
        assertEquals(testList.findAccount("user1").getUsername(), testAcct1.getUsername());
        assertEquals(testList.findAccount(2).getUserID(), testAcct2.getUserID());
        assertEquals(testList.findAccount("user2").getUsername(), testAcct2.getUsername());
        assertEquals(testList.findAccount(3).getUserID(), testAcct3.getUserID());
        assertEquals(testList.findAccount("user3").getUsername(), testAcct3.getUsername());

        UserAccount testAcct4 = new UserAccount("user4","pass4","d@e.com",4);

        testList.removeAccount(testAcct1);
        assertThrows(NonExistentAccountException.class, () -> testList.findAccount(1));
        assertThrows(NonExistentAccountException.class, () -> testList.removeAccount(testAcct4));


    }

    @Test
    void getTotalAccountsTest(){
        UserArrayList testList = new UserArrayList();
        for(int i = 0; i < 5; i++){
            UserAccount newAccount = new UserAccount("user" + Integer.toString(i),"pass" + Integer.toString(i),Integer.toString(i)+"@" + Integer.toString(i) + ".com",i);
            testList.addAccount(newAccount);
            assertEquals(i + 1, testList.getTotalNumberAccounts());
        }
        for(int i = 5; i > 0; i--){
            testList.removeAccount(testList.findAccount(i-1));
            assertEquals(i-1, testList.getTotalNumberAccounts());
        }
    }

    @Test
    void assignValidIDTest(){
        UserArrayList testList = new UserArrayList();
        for(int i = 0; testList.getTotalNumberAccounts() < 5; i=i+2){
            UserAccount newAccount = new UserAccount("user" + Integer.toString(i),"pass" + Integer.toString(i),Integer.toString(i)+"@" + Integer.toString(i) + ".com",i);
            testList.validAddAccount(newAccount);
        }
        for(int i = 0; i < 5; i++) {
            assertEquals(i, testList.findAccount(i).getUserID());
        }
    }
}
