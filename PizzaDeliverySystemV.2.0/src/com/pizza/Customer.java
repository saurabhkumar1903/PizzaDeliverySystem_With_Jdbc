package com.pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
public class Customer {
	private static int CId=0;
	private String CName;
	private String CAddress;
	private String CEmail;
	int d=0;
	public Customer(String cName, String cAddress, String cEmail) {
		super();
		CName = cName;
		CAddress = cAddress;
		CEmail = cEmail;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
		
		
	}

	public static int getCId() {
		return CId;
	}

	public static void setCId(int cId) {
		CId = cId;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public String getCAddress() {
		return CAddress;
	}

	public void setCAddress(String cAddress) {
		CAddress = cAddress;
	}

	public String getCEmail() {
		return CEmail;
	}

	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}
	
	
	Order PlaceOrder(ArrayList<Pizza> pizza) throws NumberFormatException, IOException
	{
		
		Order order=new Order();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter name    Address  and Email...");
		Customer c=new Customer();
		c.setCName(br.readLine());
		c.setCAddress(br.readLine());
		c.setCEmail(br.readLine());
	
		d=d+1;
		order.setOid(d);
		order.setCustomer(c);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/YYYY ");
		LocalDateTime now = LocalDateTime.now();
		order.setDate(dtf.format(now));
		
		ArrayList<Pizza> orderedpizza=new ArrayList<>();
		do {
			System.out.println("full menu:");
			System.out.println("Id \t Name \t Type \t Price");
			
			for(Pizza p :pizza)
				{
				System.out.println(p.getPid() + "\t" + p.getName()+"\t"+p.getType()+"\t"+p.getPrice());
				}
			System.out.println("\n\nYour Cart");
			System.out.println("Id \t Name \t Type \t Quantity \t Price \n\n ");
			for(Pizza op:orderedpizza )
			{
				if(orderedpizza==null)
					System.out.println("Cart Empty");
				System.out.println(op.getPid()+"\t"+op.getName()+"\t"+op.getType()+"\t"+"\t"+op.getQuantity()+"\t"+op.getPrice());
			}
			System.out.println("Enter your Pizza Id  Choice of base And Quantity Press -1 to Exit");
			
			int pidsel=Integer.parseInt(br.readLine());
			if(pidsel!=-1)
				{	String pbase=br.readLine();
					int PQuantity=Integer.parseInt(br.readLine());
					for(Pizza p2:pizza)
					{
						if(p2.getPid()==pidsel)
							{
							Pizza Single=new Pizza(p2);
							
							Single.setBase(pbase);
							Single.setQuantity(PQuantity);
							Single.setPrice(p2.getPrice()*PQuantity);
							orderedpizza.add(Single);
							}
					}
				}
			else
			{
				order.setPizzalist(orderedpizza);
				return order;
			}
				
			
		}while(true);
			
			
		
	}
	
	void CancelOrder(ArrayList<Order> orderDataBase, Order currentOrder) throws NumberFormatException, IOException
	{
		orderDataBase.remove(currentOrder);
		
		System.out.println("your order with Order Id"+ currentOrder.getOid()+"has been cancelleld");
		}
	void ViewOrder(Order currentOrder)
	{
		System.out.println("Order Details:");
		System.out.println("Id \t Name \t Type \t Quantity \t Price \n\n ");
		for(Pizza op:currentOrder.getPizzalist() )
		{
			System.out.println(op.getPid()+"\t"+op.getName()+"\t"+op.getType()+"\t"+"\t"+op.getQuantity()+"\t"+op.getPrice());
		}
		
		
	}
	void UpdateOrder(Order currentOrder)
	{
		System.out.println("Current order details:");
		System.out.println("Id \t Name \t Type \t Quantity \t Price \n\n ");
		for(Pizza op:currentOrder.getPizzalist() )
		{
			System.out.println(op.getPid()+"\t"+op.getName()+"\t"+op.getType()+"\t"+"\t"+op.getQuantity()+"\t"+op.getPrice());
		}
		System.out.println();
	}
	void ReceiveOrder(Order currentOrder)
	{
		currentOrder.setStatus("true");
	}
}
