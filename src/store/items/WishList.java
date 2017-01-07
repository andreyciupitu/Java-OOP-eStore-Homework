package store.items;

import java.util.Comparator;

public class WishList extends ItemList{
	public WishList(){
		super(new Comparator<Item>(){

			public int compare(Item o1, Item o2) {
				return o1.getName().compareTo(o2.getName());
			}
			
		});
	}
}
