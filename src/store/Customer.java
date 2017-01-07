package store;

import java.util.ArrayList;
import java.util.ListIterator;

import store.items.*;
import store.department.Department;
import store.interfaces.Observer;
import store.interfaces.Strategy;

public class Customer implements Observer{
	private String name;
	private ShoppingCart cart;
	private WishList wlist;
	private ArrayList<Notification> notifications;
	private Store store = Store.getInstance();
	
	public Customer(String name, Double budget, Strategy strategy){
		this.name = name;
		this.cart = store.getShoppingCart(budget);
		this.wlist = new WishList(strategy);
		this.notifications = new ArrayList<Notification>();
	}
	
	/* GETTERS & SETTERS */
	public String getName(){
		return name;
	}

	public ShoppingCart getCart(){
		return cart;
	}

	public WishList getWlist(){
		return wlist;
	}

	public ArrayList<Notification> getNotifications(){
		return notifications;
	}
	
	/* Add an element to WishList */
	public void addToWList(Item item){
		Store store = Store.getInstance();
		Department d = store.getDepartment(item.getDepartmentId());
		if (!d.getSubscribers().contains(this))
			d.addObserver(this);
		wlist.add(item);
	}
	
	/* Add an element to the ShoppingCart */
	public boolean addToCart(Item item){
		return cart.add(item);
	}
	
	/* Removes an item from the WishList */
	public boolean removeFromWList(Item item){
		if (item == null)
			return false;
		
		/* Checks if the Customer can still observe the department */
		int count = 0;
		ListIterator<Item> it = wlist.listIterator();
		while (it.hasNext())
			if (it.next().getDepartmentId().equals(item.getDepartmentId()))
				count++;
		if (count <= 1)
			Store.getInstance().getDepartment(item.getDepartmentId()).removeObserver(this);
		
		/* Removes the item */
		return wlist.remove(item);
	}
	
	/* Removes an item from the ShoppingCart */
	public boolean removeFromCart(Item item){
		return cart.remove(item);
	}
	
	/* From the Observer interface */
	public void update(Notification n){
		notifications.add(n);
		Notification.NotificationType type = n.getType();
		switch(type){
			case ADD:
				break;
			case REMOVE:
				removeFromWList(wlist.getItem(wlist.findItem(n.getItemId())));
				removeFromCart(cart.getItem(cart.findItem(n.getItemId())));
				break;
			case MODIFY:
				Store store = Store.getInstance();
				Item changedItem = store.getDepartment(n.getDepartmentId()).getItems().get(n.getItemId());
				wlist.set(wlist.findItem(n.getItemId()), changedItem.clone());
				cart.set(cart.findItem(n.getItemId()), changedItem.clone());
				break;
		}
	}
	
	/* Get item from WishList according to strategy */
	public Item getItem(){
		Item item = wlist.executeStrategy();
		removeFromWList(item);
		addToCart(item);
		return item;
	}
	
	/* toString method */
	public String toString(){
		return name;
	}
}