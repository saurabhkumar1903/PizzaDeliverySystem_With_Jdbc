package com.pizza;

import java.sql.SQLException;

public class ThreadTest extends Thread{
	private int orderId;
	private String status;
	private DataAccess access;
	ThreadTest(DataAccess access,int orderId, String status)
	{
		this.access=access;
		this.orderId=orderId;
		this.status=status;
		start();
	}
	public void run()
	{
		try {
			System.out.println("calling thread");
			access.updateOrderStatus(orderId,status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
