package store;

import java.util.ArrayList;
import java.util.HashMap;

import store.department.*;
import store.items.*;

public class Store{
	private static Store store = null;
	private String name;
	private HashMap<String, Customer> customers;
	private HashMap<Integer, Department> departments;
	
	private Store(){
		this.name = "";
		this.customers = new HashMap<String, Customer>();
		this.departments = new HashMap<Integer, Department>();
	}
	
	/* GETTERS & SETTERS */
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/* Obtain the Store instance */
	public static Store getInstance(){
		if (store == null)
			store = new Store();
		return store;
	}

	public HashMap<String, Customer> getCustomers(){
		return customers;
	}
	
	public HashMap<Integer, Department> getDepartments(){
		return departments;
	}
	
	/* Adds a new customer to the store */
	public void enter(Customer c){
		customers.put(c.getName(), c);
	}
	
	/* Removes a customer from the store */
	public void exit(Customer c){
		customers.remove(c);
	}
	
	/* Adds a new department */
	public void addDepartment(Department d){
		departments.put(d.getId(), d);
	}
	
	/* Finds a department by ID */
	public Department getDepartment(Integer x){
		return departments.get(x);
	}
	
	/* Creates a ShoppingCart */
	public ShoppingCart getShoppingCart(Double x){
		return new ShoppingCart(x);
	}
}

