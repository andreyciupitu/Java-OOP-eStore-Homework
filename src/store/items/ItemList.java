package store.items;

import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

public abstract class ItemList{
	private static class Node{
		private Item item;
		private Node next = null;

		public Node(Item item, Node next){
			this.item = item;
			this.next = next;
		}
		
		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	private class ItemIterator<E> implements ListIterator<E>{
		public void add(E arg0){
			
		}

		public boolean hasNext(){
			return false;
		}

		public boolean hasPrevious(){
			return false;
		}

		public E next(){
			return null;
		}

		public int nextIndex(){
			// TODO Auto-generated method stub
			return 0;
		}

		public E previous(){
			// TODO Auto-generated method stub
			return null;
		}

		public int previousIndex(){
			// TODO Auto-generated method stub
			return 0;
		}

		public void remove(){
			// TODO Auto-generated method stub
			
		}

		public void set(E arg0){
			// TODO Auto-generated method stub
			
		}
	}
	
	private Comparator comp;
	private Node head;
	private Node end;
	
	public ItemList(Comparator comp){
		this.comp = comp;
		this.head = null;
	}
	
	public boolean add(Item element){
		Node node = new Node(element, head.next, head);
		if (head == null)
			head = node;
		else
			head.setNext(node);
	}
	
	public boolean addAll(Collection<? extends Item> c){
		
	}
	
}
