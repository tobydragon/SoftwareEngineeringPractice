package edu.ithaca.dragon.bank;

public class Teller(){

  public Teller(Account[])

  void tellerMenu(){

    System.out.println("1): View Accounts")
    System.out.println("2): Add Account")
    System.out.println("1): Remove Account")
    Scanner reader=new Scanner(System.in);
    int selection;
    selection=reader.nextInt();
    if (selection==1){
      for(int i=0;i<accounts.length;i++){
        System.out.println(account[i].acctId +": "+account[i].type+": "+account[i].getBalance());
      }
    }
    else if(selection==2){
      AdvancedAPI.createAccount();
  }
    else if(selection==3){
      System.out.println("what account ID");
      AdvancedAPI.deleteAccount(acctId);
    }
    else if(selection==4)
  }



}
