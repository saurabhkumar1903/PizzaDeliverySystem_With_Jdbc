package com.pizza;

import java.util.ArrayList;

public class Order {

		private int Oid=0;
		private ArrayList<Pizza> pizzalist;
		private Customer customer;
		private String date;
		private double amount;
		private String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String b) {
			this.status = b;
		}
		public int getOid() {
			return Oid;
		}
		public void setOid(int oid) {
			Oid = oid;
		}
		public ArrayList<Pizza> getPizzalist() {
			return pizzalist;
		}
		public void setPizzalist(ArrayList<Pizza> pizzalist) {
			this.pizzalist = pizzalist;
		}
		public Customer getCustomer() {
			return customer;
		}
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		
		
		public ArrayList<Pizza> getPizza() {
			return pizzalist;
		}
		public void setPizza(ArrayList<Pizza> pizza) {
			this.pizzalist = pizza;
		}
		public Order(int Oid,ArrayList<Pizza> pizza, String date, double amount) {
			super();
			this.Oid=Oid;
			this.pizzalist = pizza;
			this.date = date;
			this.amount = amount;
		
		}
		public Order() {
			// TODO Auto-generated constructor stub
		}
		
		
}
