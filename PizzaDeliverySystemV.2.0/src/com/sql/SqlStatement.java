package com.sql;

public class SqlStatement {
	public static String insert_into_pizza="INSERT INTO `saurabh`.`pizza` (`pname`, `ptype`, `psize`, `pbase`, `quantity`, `amount`) values(?,?,?,?,?,?)";
	public static String insert_into_order="INSERT INTO `saurabh`.`order` (`cid`, `pid(List)`, `pname(List)`, `orderDate`, `amount`, `status`) values(?,?,?,?,?,?)";
	public static String insert_into_customer="INSERT INTO `saurabh`.`customer` (`cname`, `caddress`, `cemail`) VALUES (?,?,?)";
	public static String cancel_order="UPDATE saurabh.order SET status='Cancelled' where oid=?";
	public static String view_Current_order="select * from saurabh.order where oid=?";
	public static String receive_Current_order="UPDATE saurabh.order SET status='Delivered' where oid=?";
	public static String viewMenu="select * from saurabh.pizza";
	public static String viewAllOrder="Select * from saurabh.order";
	public static String oidToCid="Select cid from saurabh.order where oid=?";
	public static String CidToName="select cname from saurabh.customer where cid=?";








}
