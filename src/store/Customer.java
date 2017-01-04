package store;

import java.util.ArrayList;
import store.items.*;

public class Customer implements Observer{
	private String name;
	private ShoppingCart cart;
	private WishList wlist;
	private ArrayList<Notification> notifications;
	private Store store = Store.getInstance();
	
	public Customer(String name, Double budget){
		this.name = name;
		this.cart = store.getShoppingCart(budget);
		this.wlist = new WishList();
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
	
	/* From the Observer interface */
	public void update(Notification n){
		notifications.add(n);
	}
}