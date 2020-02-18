package edu.ithaca.dragon.bank;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ATMUI {
    enum State{
        NOTLOGGED,
        LOGGEDIN,
        FROZEN
    }

    BasicAPI basAPI;
    State currState;

    public ATMUI(BasicAPI apiIN){
        this.basAPI = apiIN;
        this.currState = State.NOTLOGGED;
    }

    public boolean acctFrozenCheck(String acctID){
        if(this.basAPI.findAccountWithId(acctID).getAcctFrozen()){
            this.currState = State.FROZEN;
            System.out.println("CUST SERVICE MESSAGE: Your Account is FROZEN, no action can be taken. Please contact" +
                    "CUST SERVICE: 1-888 -ACC-HELP");
            this.currState = State.NOTLOGGED;
            System.out.println("You've been logged out. Program Terminated");
            return true;
        }
        return  false;
    }




    public void run() throws IOException, IllegalAccessException {
        System.out.println("Welcome to the ATM...");
        //prompt credentials
        // if correct -- set State to logged in or frozen
        // if state is logged in -- select from menu

        String acctID;
        String password;


        Scanner in = new Scanner (System.in);
        System.out.println("Please enter account ID: ");
        acctID = in.nextLine();

        System.out.println("Please enter Password: ");
        password = in.nextLine();


        System.out.println("ID: " + acctID + "Password: " + password); //CE manaul test remove later

        //check credentials
        //if( this.basAPI.confirmCredentials(acctID,password)){
        if(true){
            this.currState = State.LOGGEDIN;
            System.out.println("Successfully Logged In");
        }


        while(this.currState != State.LOGGEDIN) {
            System.out.println("You've entered invalid username or password ");

            System.out.println("Please enter valid Account ID: ");
            acctID = in.nextLine();

            System.out.println("Please enter valid Password: ");
            password = in.nextLine();

            try{
                if( this.basAPI.confirmCredentials(acctID,password)){
                    this.currState = State.LOGGEDIN;
                }
            }
            catch(IllegalArgumentException i){

                //restart while loop
            }

        }

        if(this.basAPI.findAccountWithId(acctID).getAcctFrozen()){
            this.currState = State.FROZEN;
            System.out.println("CUST SERVICE MESSAGE: Your Account is FROZEN, no action can be taken. Please contact" +
                    "CUST SERVICE: 1-888 -ACC-HELP");
            this.currState = State.NOTLOGGED;
            System.out.println("You've been logged out. Program Terminated");
        }

        char opt;
        ArrayList<Character> set = new ArrayList<>();
        set.add('D');
        set.add('W');
        set.add('T');
        set.add('X');


        while(this.currState != State.FROZEN || this.currState != State.NOTLOGGED){
            System.out.println("To deposit enter 'D', to withdraw enter 'W', to transfer enter 'T' , " +
                    "to log-out enter 'X':  ");
            opt = (char) System.in.read();
            //deposit
            if(opt == 'D'){
                if(acctFrozenCheck(acctID)){
                    break;
                }
                System.out.println("Please enter deposit amount (ie. 1.00): ");
                double amt = in.nextDouble();
                try{
                this.basAPI.findAccountWithId(acctID).deposit(amt);
                    System.out.println("Withdraw Complete" );
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );
                }
                catch(IllegalArgumentException i ){
                    System.out.println("Enter valid deposit amount(ie. 15.01): ");
                    amt = in.nextDouble();
                    this.basAPI.findAccountWithId(acctID).deposit(amt);
                    System.out.println("Deposit Complete" );
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );
                }

            }
            if(opt == 'W'){
                if(acctFrozenCheck(acctID)){
                    break;
                }
                System.out.println("Please enter withdraw amount (ie. 1.00): ");
                double amt = in.nextDouble();
                try{
                    this.basAPI.findAccountWithId(acctID).withdraw(amt);
                    System.out.println("Withdraw Complete" );
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );

                }
                catch(IllegalArgumentException i ){
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );
                    System.out.println("Please enter valid withdraw amount (ie. 1.00): ");
                    amt = in.nextDouble();
                    this.basAPI.findAccountWithId(acctID).withdraw(amt);
                    System.out.println("Withdraw Complete" );

                }

            }
            if(opt == 'T'){
                if(acctFrozenCheck(acctID)){
                    break;
                }
                String IDin;
                System.out.println("Enter account ID of the account you'd like to transfer to:  ");
                IDin = in.next();

                try{

                    this.basAPI.findAccountWithId(IDin);
                }
                catch(NullPointerException | IllegalArgumentException i){
                    System.out.println("ERROR: Account not found -- Enter VALID account ID of the account you'd like to transfer to:  ");
                    IDin = in.next();
                    this.basAPI.findAccountWithId(IDin);
                }


                BankAccount acctID2 = this.basAPI.findAccountWithId(IDin);
                System.out.println("Please enter transfer amount (ie. 1.00): ");
                double amt = in.nextDouble();
                try{
                    this.basAPI.findAccountWithId(acctID).transfer(amt,acctID2);
                    System.out.println("Transfer Complete" );
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );
                }
                catch(IllegalArgumentException i ){
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );
                    System.out.println("Please enter valid withdraw amount (ie. 1.00): ");
                    amt = in.nextDouble();
                    this.basAPI.findAccountWithId(acctID).transfer(amt,acctID2);
                    System.out.println("Transfer Complete" );
                    System.out.println("Your account balance is " + this.basAPI.findAccountWithId(acctID).getBalance() );

                }


            }

            if(opt == 'X'){
                if(acctFrozenCheck(acctID)){
                    break;
                }
                System.out.println("Your final balance is: " + this.basAPI.findAccountWithId(acctID).getBalance() );
                System.out.println("--LOGGED OUT--");
                this.currState = State.NOTLOGGED;
                break;
            }

            if(!set.contains(opt)){
                System.out.println("You've made an invalid entry. System is case sensative." );
                System.out.println("To deposit enter 'D', to withdraw enter 'W', to transfer enter 'T' , " +
                        "to log-out enter 'X':  ");
                opt = (char) System.in.read();


            }

        }

        System.out.println("ATM Terminated");





    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        CentralBank c1 = new CentralBank();
        c1.createAccount("abc",200,"a@b.com","123");
        c1.createAccount("BA2",200, "b@p.com", "barns");
        System.out.println(c1.accountList.toString());

        BasicAPI ap = c1;
        ATMUI run = new ATMUI(ap);
        run.run();


    }


}
