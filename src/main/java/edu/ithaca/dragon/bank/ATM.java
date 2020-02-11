package edu.ithaca.dragon

public class ATM.java() implements AdvancedAPI{

  public static void main(String[] args) {
    public  Scanner r= new Scanner(System.in)
      Int tempID;
      String pass;
      System.out.println("Welcome to JATM - Please enter atm number and password or type 0 to access a back teller.");
      System.out.println("Account ID");
      tempID=r.nextInt();
      System.out.println("Account Password");
      pass=r.nextInt();
      if confirmCredentials(tempID,pass){
          #menu for api
          int menu;
          System.out.println("1). Check Balance");
          System.out.println("2). Withdraw");
          System.out.println("3). Deposit");
          System.out.println("4). Transfer");
          System.out.println("5). TransferHistory");
          System.out.println("6). getAccountID");
          menu=r.nextInt();
          if(menu==1){
            checkBalance(tempID);
          }
          else if(menu==2){
            withdraw(tempID,amount);
          }
          else if(menu==3){
            deposit(tempID,amount);
          }
          else if(menu==4){
            transfer(src,dst,amount);          }
            else if(menu==5){
              withdraw(tempID)
            }
            else if(menu==6){
              getAccountId(Email,accountType)
            }

      }

  }
}
