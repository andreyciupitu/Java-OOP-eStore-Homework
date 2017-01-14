package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractListModel;

import store.items.Item;

public class ListModel extends AbstractListModel<String>{
	private ArrayList<Item> elements;
	
	public static class AlphabeticSorter implements Comparator<Item>{

		public int compare(Item arg0, Item arg1){
			return arg0.getName().compareTo(arg1.getName());
		}
		
	}
	
	public static class AscendingPriceSorter implements Comparator<Item>{

		public int compare(Item o1, Item o2){
			return o1.getPrice().compareTo(o2.getPrice());
		}
		
	}
	
	public static class DescendingPriceSorter implements Comparator<Item>{

		public int compare(Item o1, Item o2){
			return -o1.getPrice().compareTo(o2.getPrice());
		}
		
	}
	
	public ListModel(){
		elements = new ArrayList<Item>();
	}
	
	public boolean contains(Item item){
		return elements.contains(item);
	}
	
	public boolean addElement(Item arg0){
		boolean result = elements.add(arg0);
		this.fireIntervalAdded(this, 0, getSize());
		return result;
	}
	
	public Item getItem(int index){
		return elements.get(index);
	}
	
	public boolean removeElement(Item arg0){
		boolean result = elements.remove(arg0);
		this.fireIntervalRemoved(this, 0, getSize());
		return result;
	}
	
	public Item removeElement(int index){
		Item item = elements.remove(index);
		this.fireIntervalRemoved(this, 0, getSize());
		return item;
	}

	public boolean modifyElement(Item arg0, double price){
		if (elements.contains(arg0)){
			elements.get(elements.indexOf(arg0)).setPrice(price);
			this.fireContentsChanged(this, 0, getSize());
			return true;
		}
		return false;
	}
	
	public String getElementAt(int arg0){
		return elements.get(arg0).getName();
	}
	
	public int getSize(){
		return elements.size();
	}

	public String toString(){
		return elements.toString();
	}
	
	public void sort(Comparator<Item> comp){
		Collections.sort(elements, comp);
		this.fireContentsChanged(this, 0, getSize());
	}
}
