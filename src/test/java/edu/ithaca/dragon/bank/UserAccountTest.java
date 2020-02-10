package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountTest {
    //These tests are very simple right now because UserAccount pretty much just holds data
    @Test
    void constructorTest(){

        UserAccount testUser = new UserAccount("JohnSmith","password123","a@b.com",1234);

        assertEquals("JohnSmith",testUser.getUsername());
        assertEquals("password123",testUser.getPassword());
        assertEquals("a@b.com",testUser.getEmail());
        assertEquals(1234,testUser.getUserID());

    }

    void setGetTests(){
        UserAccount testUser = new UserAccount("JohnSmith","password123","a@b.com",1234);

        testUser.changeUsername("JaneDoe");
        assertEquals("JaneDoe",testUser.getUsername());

        testUser.changeEmail("b@c.com");
        assertEquals("b@c.com",testUser.getEmail());

        testUser.changePassword("newPassword");
        assertEquals("newPassword",testUser.getPassword());

        testUser.changeUserID(1234);
        assertEquals(1234,testUser.getUserID());

        assertEquals("UserAccount{username = JaneDoe, password = newPassword, email = b@c.com, userID = 1234}", testUser.toString());

    }
}
