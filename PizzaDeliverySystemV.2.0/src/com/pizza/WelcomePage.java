package com.pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class WelcomePage {
	DataAccess dataAccess=new DataAccess();
	public void display() throws NumberFormatException, IOException, SQLException
	{
	int ch=1;
	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	do 
	{	// IN DO MAIN MENU

		System.out.println("Welcome to Pizza parlour!");
		System.out.println("\nPress 1 for shopkeeper \nPress 2 for Customer \nPress -1 to Exit");
		ch=Integer.parseInt(br.readLine());
		switch(ch)
		{
		case 1://in shopkeeper menu
			System.out.println("Welcome Shopkeeper");
			do
			{
				System.out.println("Press 1 to Add Pizza");
				System.out.println("Prss 2 to View Order");
				System.out.println("Press 0 to Return to Main Menu");
				ch=Integer.parseInt(br.readLine());
				switch(ch)
				{
				case 1:
					dataAccess.addPizza();

					break;
				case 2:
					dataAccess.ViewCompleteOrder();
					break; 
				case 0:
					break;
				default:
					break;
				}	
			}while(ch!=0);
			break; //break from shopkeeper menu

		case 2: //in customer menu
			System.out.println("Welcome Customer:");
			System.out.println("Enter your OrderId for old order Else press 0 for New order:");
			int OldOrderId=Integer.parseInt(br.readLine());
			if(OldOrderId==0) //new customer menu does not have a customer id
			{
				int cmchoice;
				do {
					new Customer();
					System.out.println("\nPress 1 to Place Order:");
					System.out.println("Press -1 to Exit");
					cmchoice=Integer.parseInt(br.readLine());
					switch(cmchoice) 
					{
					case 1:

						int oidplaced=dataAccess.PlaceOrder();
						System.out.println("Pizza order Confirmed");
						System.out.println("Your order Id is "+oidplaced);						
						break;
					default:
						break;
					}
				}while(cmchoice!=-1);//end of dowhile

			}//clsoe of if new or old customer
			else//in old customer menu does have a valid customer id and order id
				dataAccess.AccessOldOrder(OldOrderId);

			break;//end of case 2
		case -1:
			
		default :
			break;
		}//end of switch case

	}while(ch!=-1);


}

	public void closeAllConnection() {
		// TODO Auto-generated method stub
		try {
			dataAccess.closeAllConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
	