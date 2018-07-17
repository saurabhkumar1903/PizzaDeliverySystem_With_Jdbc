package com.pizza;
import java.util.*;
import java.io.*;

public class Shopkeeper {
	Pizza pizza;
	//ArrayList<Pizza> pizzalist=new ArrayList<>();
	
	Pizza AddPizza(int pid) throws IOException
	{
		return pizza;
	}
	void ViewOrder(ArrayList<Order> order)
	{
		System.out.println("All orders Details");
		System.out.println("Order Id  \tPizza list \t Name \t Date \t Amount \tDelivery Status");
		for (Order or:order)
		{
			System.out.print(or.getOid()+"\t \t");
			for(Pizza p:or.getPizza())
			{
				System.out.print(p.getName()+",");
			}
			System.out.print("\t"+or.getCustomer().getCName());
			System.out.println("\t\t"+or.getDate()+"\t"+or.getAmount()+or.getStatus());
			
		}
	}
	
	
}
