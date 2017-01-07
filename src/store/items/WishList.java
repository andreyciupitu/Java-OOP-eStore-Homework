package store.items;

import java.util.Comparator;

import store.interfaces.Strategy;

public class WishList extends ItemList{
	private Strategy strategy;
	private Item latestAdded = null;

	public WishList(Strategy strategy){
		super(new Comparator<Item>(){

			public int compare(Item o1, Item o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
		this.strategy = strategy;
	}

	/* GETTERS & SETTERS */
	public Strategy getStrategy(){
		return strategy;
	}

	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	public Item getLatestAdded(){
		return latestAdded;
	}

	/* Strategy Pattern */
	public Item executeStrategy(){
		return strategy.execute(this);
	}
	
	public boolean add(Item element){
		latestAdded = element;
		return super.add(element);
	}
}
