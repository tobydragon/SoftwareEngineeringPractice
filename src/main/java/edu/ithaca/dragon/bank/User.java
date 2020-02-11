package edu.ithaca.dragon.bank;

public class User {
    private atmNum;
    private String email;
    private String password;
    private BankAccount accounts[];


String getEmail(){
  return email;
}
String getPassword(){
  return password;
}
void setEmail(String e){
  email=e;
}
void setPassword(String pwd){
  password=pwd;
}
#Teller Access Only
void accessTeller(){

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

}

}
