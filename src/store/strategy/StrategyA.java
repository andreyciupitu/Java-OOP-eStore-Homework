package store.strategy;

import java.util.ListIterator;

import store.interfaces.Strategy;
import store.items.Item;
import store.items.WishList;

public class StrategyA implements Strategy{
	private static final double BIG = 10000000;
	
	/* Obtain the cheapest item in the WishList */
	public Item execute(WishList wlist){
		double min = BIG;
		Item minItem = null;
		
		/* Find the item with the lowest price */
		ListIterator<Item> it = wlist.listIterator();
		while (it.hasNext()){
			Item item = it.next();
			if (item.getPrice() < min){
				min = item.getPrice();
				minItem = item;
			}
		}
		return minItem;
	}

}
