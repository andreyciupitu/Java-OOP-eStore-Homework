package store.strategy;

import java.util.ListIterator;

import store.interfaces.Strategy;
import store.items.Item;
import store.items.WishList;

public class StrategyA implements Strategy{
	
	public Item execute(WishList wlist){
		double min = 10000000;
		Item minItem = null;
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
