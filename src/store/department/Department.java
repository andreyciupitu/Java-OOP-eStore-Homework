package store.department;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import store.*;
import store.items.*;
import store.interfaces.*;

public abstract class Department implements Subject{
	private final String name;
	private final Integer id;
	private HashMap<Integer, Item> items;
	private ArrayList<Customer> buyers;
	private ArrayList<Observer> subscribers;
	
	/* Builder Pattern */
	public static class DepartmentBuilder{
		private final String name;
		private final Integer id;
		private HashMap<Integer, Item> items;
		private ArrayList<Customer> buyers;
		private ArrayList<Observer> subscribers;
		
		public DepartmentBuilder(String name, int id){
			this.name = name;
			this.id = id;
			this.items = new HashMap<Integer, Item>();
			this.buyers = new ArrayList<Customer>();
			this.subscribers = new ArrayList<Observer>(); 
		}
		
		public DepartmentBuilder initItems(HashMap<Integer, Item> items){
			this.items = items;
			return this;
		}
		
		public DepartmentBuilder initBuyers(ArrayList<Customer> buyers){
			this.buyers = buyers;
			return this;
		}
		
		public DepartmentBuilder initSubscribers(ArrayList<Observer> subscribers){
			this.subscribers = subscribers;
			return this;
		}
		
		public Department build(DepartmentFactory.DepartmentType type){
			DepartmentFactory factory = DepartmentFactory.getInstance();
			return factory.createDepartment(this, type);
		}
	}
	
	public Department(DepartmentBuilder builder){
		this.name = builder.name;
		this.id = builder.id;
		this.items = builder.items;
		this.buyers = builder.buyers;
		this.subscribers = builder.subscribers; 
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
	
	public HashMap<Integer, Item> getItems(){
		return items;
	}

	/* Equals method */
	public boolean equals(Object o){
		if (!(o instanceof Department))
			return false;
		Department d = (Department)o;
		return this.name.equals(d.getName()) && this.id.equals(d.getId());
	}
	
	/* Adds an item to the department */
	public void addItem(Item item){
		items.put(item.getId(), item);
		notifyAllObservers(new Notification(new Date(), Notification.NotificationType.ADD, item.getId(), id));
	}
	
	/* Removes an item from the department */
	public void removeItem(int itemId){
		items.remove(itemId);
		notifyAllObservers(new Notification(new Date(), Notification.NotificationType.REMOVE, itemId, id));
	}
	
	/* Modifies and item in the department */
	public void modifyItem(int itemId, double price){
		items.get(itemId).setPrice(price);
		notifyAllObservers(new Notification(new Date(), Notification.NotificationType.MODIFY, itemId, id));
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
		ArrayList<Observer> clone = new ArrayList<Observer>(subscribers);
		for (Observer o : clone)
			o.update(n);
	}

	/* Accept a visitor */
	public abstract void accept(ShoppingCart cart);
}
