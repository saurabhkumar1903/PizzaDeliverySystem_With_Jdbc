package com.pizza;
import java.io.IOException;
import java.sql.SQLException;
public class Driver {


	public static void main(String args[])throws IOException, SQLException
	{
		WelcomePage welcomePage=new WelcomePage();
		welcomePage.display();
		welcomePage.closeAllConnection();

	}

}


