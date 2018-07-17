package com.pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataAccess {
	// JDBC driver name and database URL
	
	static int oid = 0;
	// Database credentials
	
	Connection connection = null;
	PreparedStatement insertIntoPizza, insertIntoOrder,insertIntoCustomer,cancelOrderStatement,viewCurrentOrderStatement,receiveStatement,viewMenuStatement,showorder,oidToCid,cidToName;

	public DataAccess() {

		try {
			// STEP 1: Register JDBC driver
			Class.forName(com.sql.DataBaseConnectivity.JDBC_DRIVER);

			// STEP 2: Open a connection
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(com.sql.DataBaseConnectivity.DB_URL, com.sql.DataBaseConnectivity.USER, com.sql.DataBaseConnectivity.PASS);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void closeAllConnection() throws SQLException
	{
		
		connection.close();
	}
	public void addPizza() throws IOException, SQLException {
		insertIntoPizza = connection.prepareStatement(com.sql.SqlStatement.insert_into_pizza);		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter name\ttype\tprice of pizza");
		String name, type;
		double price;
		name = br.readLine();
		type = br.readLine();
		price = Double.parseDouble(br.readLine());
		int resultOfAddPizza = 0;

		try {
			
			insertIntoPizza.setString(1, name);
			insertIntoPizza.setString(2, type);
			insertIntoPizza.setString(3, "Regular");
			insertIntoPizza.setString(4, "Pan Pizza");
			insertIntoPizza.setInt(5, 1);
			insertIntoPizza.setDouble(6, price);
			resultOfAddPizza = insertIntoPizza.executeUpdate();
			if (resultOfAddPizza != 0) {
				System.out.println("Pizza Added Successfully");
				//ViewMenu();
			} else
				System.out.println("Pizza Not Added!!!Error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertIntoPizza.close();

	}

	public void ViewMenu() throws SQLException {
		viewMenuStatement=connection.prepareStatement(com.sql.SqlStatement.viewMenu);
		ResultSet rs=viewMenuStatement.executeQuery();
		if (rs == null) {
			System.out.println("Menu Empty!!!");
			return;
		} else {
			System.out.println("Full menu:" + "");
			System.out.println("Id\tName\t\t\t\tType\tSize\tBase\tAmount");
			while (rs.next()) {

				System.out.println();
				int id = rs.getInt("pid");
				String name = rs.getString("pname");
				String type = rs.getString("ptype");
				String size = rs.getString("psize");
				String base = rs.getString("pbase");
				double amount = rs.getDouble("amount");
				System.out.println(id + "\t" + name + "\t\t\t\t" + type + "\t" + size + "\t" + base + "" + amount);
			}
		}
		//viewMenuStatement.close();
		//rs.close();

	}

	public ArrayList<Pizza> ViewMenu2() throws SQLException {
		
		ResultSet rs = viewMenuStatement.executeQuery();
	
		ArrayList<Pizza> Pizzamenu = new ArrayList<>();
		if (rs.next() == false) {
			System.out.println("Menu Empty!!!");
			return Pizzamenu;
	      } else {

	        do {
	        	Pizza singlePizza = new Pizza();
	           	int id = rs.getInt("pid");
				String name = rs.getString("pname");
				String type = rs.getString("ptype");
				String size = rs.getString("psize");
				String base = rs.getString("pbase");
				singlePizza.setPid(id);
				singlePizza.setName(name);
				singlePizza.setType(type);
				singlePizza.setSize(size);
				singlePizza.setBase(base);
				double amount = rs.getDouble("amount");
				singlePizza.setPrice(amount);
				Pizzamenu.add(singlePizza);
	        } while (rs.next());
	      }
		//viewMenuStatement.close();
		//rs.close();
		return Pizzamenu;
	}

	public void ViewCompleteOrder() throws SQLException {
		showorder=connection.prepareStatement(com.sql.SqlStatement.viewAllOrder);
		ResultSet rs = showorder.executeQuery();
		if (rs.next() == false) {
			System.out.println("No Orders To display!!!");
			return;
		}else {
			System.out.println("All orders Details");
			System.out.println("Order Id\tCustomerName\tPizzaIds\t\tPizzaName\t\tDate\t\t\tAmount\t\tOrder Status");
			System.out.println();
			
			do {
				int oid = rs.getInt("oid");
				rs.getInt("cid");
				String name = GetCustomerName(oid);

				String pidlist = rs.getString("pid(List)");
				String pnamelist = rs.getString("pname(List)");
				String orderDate=rs.getString("orderDate");
				
				double amount = rs.getDouble("amount");
				System.out.println(oid + "\t\t" + name + "\t\t" + pidlist + "\t\t\t" + pnamelist + "\t\t" +orderDate +"\t\t"+ amount
						+ "\t\t" + rs.getString("status"));
		
			}while(rs.next() );
		}
		showorder.close();
		rs.close();
		}

	int PlaceOrder() throws NumberFormatException, IOException, SQLException {

		Order order = new Order();
		insertIntoOrder = connection.prepareStatement(com.sql.SqlStatement.insert_into_order);		
		insertIntoCustomer=connection.prepareStatement(com.sql.SqlStatement.insert_into_customer);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter name    Address  and Email...");
		new Customer();
		String name = br.readLine();
		String address = br.readLine();
		String Email = br.readLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss MM/dd/YYYY");
		LocalDateTime now = LocalDateTime.now();
		order.setDate(dtf.format(now));
		ArrayList<Pizza> orderedpizza = new ArrayList<>() ;
		int lastid = 0;
		do {
			ViewMenu(); //view menu to customer
			System.out.println("\n\nYour Cart");
			System.out.println("Id \t Name \t Type \t Quantity \t Price \n\n ");
			if (orderedpizza == null)
			{
			System.out.println("Cart Empty");
			}
			else {
				for (Pizza op : orderedpizza) {
					
					System.out.println(op.getPid() + "\t" + op.getName() + "\t" + op.getType() + "\t" + "\t"
							+ op.getQuantity() + "\t" + op.getPrice());
				}
			}
			System.out.println("Enter your Pizza Id  Choice of base And Quantity Press -1 to Exit");

			int pidsel = Integer.parseInt(br.readLine());
			
			ArrayList<Pizza> pizzaMenu = ViewMenu2();
			
			if (pidsel != -1) {
				String pbase = br.readLine();
				int PQuantity = Integer.parseInt(br.readLine());
				for (Pizza p2 : pizzaMenu) {
					if (p2.getPid() == pidsel) {
						System.out.println("Pizza matched!!!");
						Pizza Single = new Pizza(p2);

						Single.setBase(pbase);
						Single.setQuantity(PQuantity);
						Single.setPrice(p2.getPrice() * PQuantity);
						orderedpizza.add(Single);
					}
				}
			} else {
				String orderedpizzanames = "";
				String orderedPizzaId = "";
				double orderedamount = 0.0;
				for (Pizza p : orderedpizza) {
					orderedPizzaId = p.getPid() + ",";
					orderedpizzanames = p.getName() + ",";
					orderedamount += p.getPrice();
				}
				try {
					
					lastid=GenerateNewOrderId();
					//System.out.println("lst order id is"+lastid);
					insertIntoOrder.setInt(1, lastid+1);
					insertIntoOrder.setString(2, orderedPizzaId);
					insertIntoOrder.setString(3, orderedpizzanames);
					insertIntoOrder.setString(4, dtf.format(now));
					insertIntoOrder.setDouble(5, orderedamount);
					insertIntoOrder.setString(6, "Order Accepted");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				insertIntoCustomer.setString(1, name);
				insertIntoCustomer.setString(2, address);
				insertIntoCustomer.setString(3, Email);
				insertIntoCustomer.executeUpdate();//create customer
				insertIntoOrder.executeUpdate();//create order
				return lastid+1;
			}

		} while (true);
		
		
	}

	void CancelOrder(int Oid) throws NumberFormatException, IOException, SQLException {
		cancelOrderStatement=connection.prepareStatement(com.sql.SqlStatement.cancel_order);
		
		try {
			
			cancelOrderStatement.setInt(1, Oid);
			cancelOrderStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("your order with Order Id " + Oid + " has been cancelleld");
		cancelOrderStatement.close();
	}

	void ViewOrder(int currentOrderOid) throws SQLException {
		viewCurrentOrderStatement=connection.prepareStatement(com.sql.SqlStatement.view_Current_order);												
		System.out.println("Order Details:");
		System.out.println("Order Id\tCustomer Id\tPizza Ids\t\tPizza Name\t\tDate\t\tAmount\t\tOrder Status");
		
		try {
			viewCurrentOrderStatement.setInt(1,currentOrderOid);
			ResultSet rs = viewCurrentOrderStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t\t" + rs.getInt(2) + "\t\t" + rs.getString(3) + "\t\t\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getDouble(6) + "\t\t" + rs.getString(7));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewCurrentOrderStatement.close();
	}

	void UpdateOrder(int currentOrderOid) {
		System.out.println("Current order details:");
		System.out.println("Id \t Name \t\t\t\t Type \t Quantity \t Price \n\n ");

		System.out.println();
	}

	void ReceiveOrder(int currentOrderOid) throws SQLException {
		receiveStatement=connection.prepareStatement(com.sql.SqlStatement.receive_Current_order);
		receiveStatement.setInt(1,currentOrderOid);
		try {
			receiveStatement.executeUpdate();
			System.out.println("Order Id "+currentOrderOid+" Received!");
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		receiveStatement.close();
	}

	String GetCustomerName(int Oid) throws SQLException {
		oidToCid=connection.prepareStatement(com.sql.SqlStatement.oidToCid);
		cidToName=connection.prepareStatement(com.sql.SqlStatement.CidToName);
		int cid = 0;
		String cname ="";
		try {
			oidToCid.setInt(1, Oid);
			ResultSet rs = oidToCid.executeQuery();
			while (rs.next()) {
				cid = rs.getInt("cid");
			}
			
			cidToName.setInt(1, cid);
			ResultSet rs2=cidToName.executeQuery();
			
			while (rs2.next()) {
				cname = rs2.getString("cname");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oidToCid.close();
		cidToName.close();
		return cname;
	}

	public void AccessOldOrder(int oldOrderId1) throws SQLException {
		System.out.println("Accessing old order");
		viewCurrentOrderStatement=connection.prepareStatement(com.sql.SqlStatement.view_Current_order);												
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cmchoice = oldOrderId1;
		do {
			//System.out.println("ïn do while loop");

			try {
				//System.out.println("in try");
				viewCurrentOrderStatement.setInt(1, oldOrderId1);
				ResultSet rs = viewCurrentOrderStatement.executeQuery();
				if (rs.next() == false) { 
					System.out.println("Wrong Order Id entered!! enter Again Press -1 to Exit");
					oldOrderId1=Integer.parseInt(br.readLine());
					cmchoice=oldOrderId1;
					} 
				else { 
					do { 
						int oldid2 = rs.getInt("oid");
						String cname=GetCustomerName(oldid2);
						System.out.println("Welcome " + cname);
						System.out.println("PRess 1 to cancel order");
						System.out.println("Press 2 to View order");
						System.out.println("Press 3 to update order");
						System.out.println("Press 4 to receive order");
						System.out.println("Press -1 to Exit");
						cmchoice = Integer.parseInt(br.readLine());
						switch (cmchoice) {

						case 1:
							CancelOrder(oldOrderId1);
							cmchoice = -1;
							break;
						case 2:
							ViewOrder(oldOrderId1);
							break;
						case 3:
							ViewOrder(oldOrderId1);
							break;
						case 4:
							ReceiveOrder(oldOrderId1);
							break;

						case 5:
							UpdateOrder(oldOrderId1);
							break;
						default:
							break;

						}
					} while (rs.next());

				}
			} catch (Exception e) {
				System.out.println("Exception caught"+e);

			}
		} while (cmchoice != -1);
		viewCurrentOrderStatement.close();
		
	}

	public int GenerateNewOrderId() throws SQLException
	{	
		showorder=connection.prepareStatement(com.sql.SqlStatement.viewAllOrder);
		int id = 0;
		try {
			
			ResultSet rs=showorder.executeQuery();
			while(rs.next())
			{
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		showorder.close();

		return id;
		
	}
}
