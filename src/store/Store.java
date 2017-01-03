package store;

import java.util.ArrayList;
import store.department.*;

public class Store{
	private static Store store = null;
	private String name;
	private ArrayList<Customer> customers;
	private ArrayList<Department> departments;
	
	private Store(String name){
		this.name = name;
		this.departments = null;
		this.customers = null;
	}
	
	/* Obtain the Store instance */
	public static Store getInstance(String name){
		if (store == null)
			store = new Store(name);
		return store;
	}
	
	/* Adds a new customer to the store */
	public void enter(Customer c){
		customers.add(c);
	}
	
	/* Removes a customer from the store */
	public void exit(Customer c){
		customers.remove(c);
	}

	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public ArrayList<Department> getDepartments(){
		return departments;
	}
	
	public void addDepartment(Department d){
		departments.add(d);
	}
	
	public Department getDepartment(Integer x){
		for (Department d : departments)
			if (d.getID() == x)
				return d;
		return null;
	}	
}

