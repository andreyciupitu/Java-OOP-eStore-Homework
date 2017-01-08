package store.items;

import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.Map;

import store.department.*;
import store.interfaces.Visitor;

public class ShoppingCart extends ItemList implements Visitor{
	private Double budget;
	private Double moneyLeft;
	
	public ShoppingCart(Double budget){
		super(new Comparator<Item>(){

			public int compare(Item arg0, Item arg1){
				if (arg0.getPrice() == arg1.getPrice())
					return arg0.getName().compareTo(arg1.getName());
				return arg0.getPrice().compareTo(arg1.getPrice());
			}
			
		});
		this.budget = budget;
		this.moneyLeft = budget;
	}
	
	/* Adds an item if it doesn't exceed the budget */
	public boolean add(Item element){
		if (element.getPrice() > moneyLeft)
			return false;
		moneyLeft -= element.getPrice();
		return super.add(element);
	}
	
	/* Adds all elements from the collection, if it
	 *  doesn't exceed the budget */
	public boolean addAll(Collection<? extends Item> c){
		double total = 0;
		for (Item e : c)
			total += e.getPrice();
		if (total > budget)
			return false;
		return super.addAll(c);
	}
	
	/* Removes an item at a specified INDEX and restores the money */
	public Item remove(int index){
		Item item = super.remove(index);
		if (item != null)
			moneyLeft += item.getPrice();
		return item;
	}
	
	/* Removes an item and restores the money */
	public boolean remove(Item item){
		boolean success = super.remove(item);
		if (success)
			moneyLeft += item.getPrice();
		return success;
	}
	
	public void visit(Department d){
		d.accept(this);
	}

	/* Applies BookDepartment discount */
	public void visit(BookDepartment d){
		ListIterator<Item> it = listIterator();
		while (it.hasNext()){
			Item item = it.next();
			if (item.getDepartmentId().equals(d.getId())){
				item.setPrice(item.getPrice() * 0.9);
				it.set(item);
			}
		}
		moneyLeft = budget - getTotalPrice();
	}

	/* Applies the Music Department discount */
	public void visit(MusicDepartment d){
		ListIterator<Item> it = listIterator();
		double discount = 0;
		
		/* Finds the total price of the Music items
		 * in the Shopping Cart */
		while (it.hasNext()){
			Item item = it.next();
			if (item.getDepartmentId().equals(d.getId()))
				discount += item.getPrice();
		}
		
		/* Applies discount and calculates remaining budget */
		budget += 0.1 * discount;
		moneyLeft = budget - getTotalPrice();
	}

	/* Adds the Software discount, if applicable */
	public void visit(SoftwareDepartment d){
		double min = 10000000;
		
		/*Finds the cheapest Software item */
		for (Map.Entry<Integer, Item> entry : d.getItems().entrySet())
			if (entry.getValue().getPrice() < min)
				min = entry.getValue().getPrice();
		
		/* Applies discount if the criteria is met */
		if (min > moneyLeft){
			ListIterator<Item> it = listIterator();
			while (it.hasNext()){
				Item item = it.next();
				if(item.getDepartmentId().equals(d.getId())){
					item.setPrice(item.getPrice() * 0.8);
					it.set(item);
				}
			}
		}
		moneyLeft = budget - getTotalPrice();
	}

	/* Applies the VideoDepartment discount */
	public void visit(VideoDepartment d){
		double max = -1;
		double discount = 0;
		
		/*Finds the most expensive Video item */
		for (Map.Entry<Integer, Item> entry : d.getItems().entrySet())
			if (entry.getValue().getPrice() > max)
				max = entry.getValue().getPrice();
		
		/* Finds the total price of the Video items
		 * in the Shopping Cart */
		ListIterator<Item> it = listIterator();
		while (it.hasNext()){
			Item item = it.next();
			if (item.getDepartmentId().equals(d.getId()))
				discount += item.getPrice();
		}
		
		/* Applies discount if the criteria is met */
		if (discount > max){
			it = listIterator();
			while (it.hasNext()){
				Item item = it.next();
				if(item.getDepartmentId().equals(d.getId())){
					item.setPrice(item.getPrice() * 0.85);
					it.set(item);
				}
			}
		}
		
		/* Calculates the remaining budget */
		budget *= 1.05;
		moneyLeft = budget - getTotalPrice();	
	}
}
