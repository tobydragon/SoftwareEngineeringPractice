package edu.ithaca.dragon.bank;
 public class ATMUI(){
 	public static void public static void main(String[] args) {
 		Scanner reader=new Scanner(System.in);
 		CentralBank root=new CentralBank();
 		String[7] basicMenu=new String;

 		//Log in
 		String[2] tempCreds=new String{"",""};
 		while(confirmCredentials(tempCreds[0],tempCreds[1])){
 		System.out.println("Welcome to the ATM, enter username then password");
 		System.out.println("email:");
 		System.out.println("password:");
 	}
 		//basic Menu
 		startMenu[0]="View Accounts";
 		startMenu[1]="Check Balance";
 		startMenu[2]="Deposit";
 		startMenu[3]="Withdraw";
 		startMenu[4]="Transfer";
 		startMenu[5]="Get Teller";
 		startMenu[6]="Log Out";

 		boolean run=true;
 		while(run==true){
 			System.out.println("Hi "+tempCreds[0]+" Please enter corresponding command number");
	 		for(int i=0;i<sizeof(basicMenu);i++){
	 			System.out.println((i+1)+" = "+startMenu[i]);
	 		}
	 		int tempSelect=reader.nextInt();

	 		if(tempSelect==1){
	 			if(root.getAccountId(email,"savings")){
	 				System.out.println(root.getAccountId(email,"savings"))
	 			}
	 			if(root.getAccountId(email,"checking")){
	 				System.out.println(root.getAccountId(email,"checking"))
	 			}
	 		}
	 		else if(tempSelect==2){
	 			System.out.println("Enter ID");
	 			int id=reader.nextInt();
	 			if(root.findID(id)!=null){
	 				double bal=root.checkBalance(id);
	 				System.out.println(id);
	 			}
	 		}
 		}
 	}
 }
