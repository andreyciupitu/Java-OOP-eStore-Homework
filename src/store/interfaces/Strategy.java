package store.interfaces;

import store.items.Item;
import store.items.WishList;

public interface Strategy{
	public Item execute(WishList wlist);
}
