package store;

import java.util.ArrayList;
import store.department.*;
import store.items.*;

public class Store{
	private static Store store = null;
	private String name;
	private ArrayList<Customer> customers;
	private ArrayList<Department> departments;
	
	private Store(){
		this.name = "";
		this.customers = new ArrayList<Customer>();
		this.departments = new ArrayList<Department>();
	}
	
	/* GETTERS AND SETTERS */
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

	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public ArrayList<Department> getDepartments(){
		return departments;
	}
	
	/* Adds a new customer to the store */
	public void enter(Customer c){
		customers.add(c);
	}
	
	/* Removes a customer from the store */
	public void exit(Customer c){
		customers.remove(c);
	}
	
	/* Adds a new department */
	public void addDepartment(Department d){
		departments.add(d);
	}
	
	/* Finds a department by ID */
	public Department getDepartment(Integer x){
		for (Department d : departments)
			if (d.getId() == x)
				return d;
		return null;
	}
	
	/* Creates a ShoppingCart */
	public ShoppingCart getShoppingCart(Double x){
		return new ShoppingCart(x);
	}
}

