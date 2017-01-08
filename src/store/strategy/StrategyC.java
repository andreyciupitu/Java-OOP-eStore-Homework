package store.strategy;

import store.interfaces.Strategy;
import store.items.Item;
import store.items.WishList;

public class StrategyC implements Strategy{

	/* Obtain the item last added to the WishList */
	public Item execute(WishList wlist){
		return wlist.getLatestAdded();
	}

}
