package edu.ithaca.dragon.bank;

public class AtmUITest {

    public static void main(String[] args) throws AccountDoesNotExistException, AccountAlreadyExistsException{
        BasicAPI atm = CentralBankTest.createTestBank();
        AtmUI ui = new AtmUI(atm);
        ui.run();
    }

    /**
     * Testing Script
     * should open asking for login
     * enter account id: "false@bank.com"
     * should ask for password
     * enter "password1"
     * should say bad credentials and try again
     * enter account id: "first@bank.com"
     * should ask for password
     * enter "password"
     * should say bad credentials and try again
     * enter account id: "first@bank.com"
     * should ask for password
     * enter "password1"
     * should display balance of 100
     * should say possible commands: withdraw deposit transfer logout
     * enter "rob bank"
     * should say invalid command and prompt again
     * enter "deposit"
     * should ask you for amount
     * enter "100.9999"
     * should say invalid amount and ask again
     * enter "-1"
     * should say invalid amount and ask again
     * enter "100.50"
     * should accept and bring you back to homepage - balance of 200.50
     * should again ask for commands
     * enter "withdraw"
     * should ask for amount
     * enter "nope"
     * should say invalid input and ask again
     * enter "50"
     * should accept and go back to home - balance of 150.50
     * should ask again for commands
     * enter "transfer"
     * should ask for account id
     * enter "haha@wrong.com"
     * should accept and ask for amount
     * enter "0.010101"
     * should say invalid and ask again
     * enter "5"
     * should say invalid and ask again
     * enter "second@bank.com"
     * should accept and ask for amount
     * enter "0.010101"
     * should say invalid and ask again
     * enter "5"
     * should accept and return to home - balance of 145.50
     * should ask for commands again
     * enter "logout"
     * should bring back to login and ask for account id
     * enter "second@bank.com"
     * should ask for password
     * enter "password2"
     * should accept and display balance of 205.00
     * should ask for commands
     * enter "transfer"
     * should ask for account id
     * enter "third@bank.com"
     * should accept and ask for amount
     * enter "10.0"
     * should reject and say that account is frozen and asks user to select command again
     * enter "transfer"
     * enter "second@bank.com"
     * should accept and ask for amount
     * enter "10.0"
     * should say that you can't transfer to yourself, idiot, and sends user back to commands screen
     * enter "first@bank.com"
     * should accept and ask for an amount
     * enter "210.00"
     * should say insufficient funds and send user back to commands screen
     * should return to first screen - balance of 205.00 and ask for commands
     * enter "deposit"
     * should ask for amount
     * enter "500.00"
     * should accept and return to asking for command, balance of 705.00
     * enter "withdraw"
     * should ask for amount
     * enter "600.01"
     * should say that exceeds maximum withdrawal and asks user to select command again
     * enter "withdraw"
     * enter "205"
     * should accept and return to balance of 500 (changed from 700), ask for commands
     * enter "logout"
     * should return to login
     * enter "third@bank.com"
     * should ask for password
     * enter "password3"
     * should show screen that says account is frozen call customer service
     * should also have a line for commands
     * enter "withdraw"
     * enter "10"
     * should say that you can't do that while the account is frozen, asks user to select command again
     * enter "transfer"
     * enter "first@bank.com"
     * enter "10"
     * should say that you can't do that while the account is frozen, asks user to select command again
     * enter "deposit"
     * enter "10"
     * should say that you can't do that while the account is frozen, asks user to select command again
     * enter "logout"
     * should return to login option
     */


}
