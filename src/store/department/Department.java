package store.department;

import java.util.ArrayList;
import store.*;
import store.items.*;

public abstract class Department implements Subject{
	private String name;
	private Integer id;
	private ArrayList<Item> items;
	private ArrayList<Customer> buyers;
	private ArrayList<Observer> subscribers;
	
	public Department(String name, int id){
		this.name = name;
		this.id = id;
		this.items = new ArrayList<Item>();
		this.buyers = new ArrayList<Customer>();
		this.subscribers = new ArrayList<Observer>(); 
	}
	
	/* GETTERS & SETTERS */
	public String getName(){
		return name;
	}

	public Integer getId(){
		return id;
	}

	public ArrayList<Customer> getCustomers(){
		return buyers;
	}

	public ArrayList<Observer> getSubscribers(){
		return subscribers;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}

	/* Adds a Customer to the Department */
	public void enter(Customer c){
		buyers.add(c);
	}
	
	/* Removes a Customer from the Department */
	public void exit(Customer c){
		buyers.remove(c);
	}

	/* Methods from the Subject interface */
	public void addObserver(Observer o){
		subscribers.add(o);
	}

	public void removeObserver(Observer o){
		subscribers.remove(o);
	}

	public void notifyAllObservers(Notification n){
		for (Observer o : subscribers)
			o.update(n);
	}

	/* Accept a visitor */
	public abstract void accept(ShoppingCart cart);
}
