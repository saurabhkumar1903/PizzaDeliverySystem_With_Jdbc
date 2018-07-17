package com.pizza;

public class Pizza {
	private int Pid =0;
	private String Name;
	private String Type;
	private String Size;
	
	private String Base;
	private int Quantity;
	private Double Price;
	
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public String getBase() {
		return Base;
	}
	public void setBase(String base) {
		Base = base;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public Pizza(int pid,String name, String type, String size, String base, Double price) {
		super();
		this.Pid=pid;
		Name = name;
		Type = type;
		Size = size;
		Price = price;
		Base = base;
		Quantity=1;
	}
	public Pizza(int pid,String name, String type, double price) {
		super();
		this.Pid=pid;
		this.Name = name;
		this.Type = type;
		this.Price = price;
		this.Size="Regular";
		this.Base="cheese";
		this.Quantity=1;
		System.out.println("in pizza constructor");
	}
	public Pizza() {
		// TODO Auto-generated constructor stub
	}
	public Pizza(Pizza p2) {
		// TODO Auto-generated constructor stub
		
		super();
		this.Pid=p2.getPid();
		this.Name = p2.getName();
		this.Type = p2.getType();
		this.Price = p2.getPrice();
		this.Size="Regular";
		this.Base="cheese";
		this.Quantity=1;
	}
	
}
