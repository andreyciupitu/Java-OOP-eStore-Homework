package store.strategy;

import store.interfaces.Strategy;
import store.items.Item;
import store.items.WishList;

public class StrategyB implements Strategy{

	public Item execute(WishList wlist){
		return wlist.getHead().getNext().getItem();
	}

}
