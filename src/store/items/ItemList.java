package store.items;

import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

public abstract class ItemList{
	
	/* Inner class that represents a Node of the list */
	public static class Node<T>{
		private T item;
		private Node<T> next;
		private Node<T> prev;
		
		public Node(){
			this.item = null;
			this.prev = null;
			this.next = null;
		}
		
		public Node(T item, Node<T> next, Node<T> prev){
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
		
		/* GETTERS & SETTERS */
		public T getItem() {
			return item;
		}

		public void setItem(T item) {
			this.item = item;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

		public String toString(){
			return item.toString();
		}
	}
	
	/* ListIterator implementation */
	public class ItemIterator<E> implements ListIterator<E>{
		private Node<E> aux;
		private int index;
		
		public ItemIterator(Node<E> aux, int index){
			this.aux = aux;
			this.index = index;
		}

		public void add(E arg0){
			if (!(arg0 instanceof Item))
				return;
			Item item = (Item) arg0;
			ItemList.this.add(item);
		}

		public boolean hasNext(){
			if (aux.getNext() != end)
				return true;
			return false;
		}

		public boolean hasPrevious(){
			if (aux.getPrev() != head)
				return true;
			return false;
		}

		public E next(){
			if (hasNext()){
				index++;
				aux = aux.getNext();
				return aux.getItem();
			}
			return null;
		}

		public int nextIndex(){
			if (hasNext())
				return index + 1;
			return length;
		}

		public E previous(){
			if (hasPrevious()){
				index--;
				aux = aux.getPrev();
				return aux.getItem();
			}
			return null;
		}

		public int previousIndex(){
			if (hasPrevious())
				return index - 1;
			return -1;
		}

		public void remove(){
			aux.getNext().setPrev(aux.getPrev());
			aux.getPrev().setNext(aux.getNext());
		}

		public void set(E arg0){
			length--;
			remove();
			add(arg0);
		}
	}
	
	private Comparator<Item> comp;
	private Node<Item> head;
	private Node<Item> end;
	private int length;
	
	public ItemList(Comparator<Item> comp){
		this.comp = comp;
		this.length = 0;
		this.head = new Node<Item>();
		this.end = new Node<Item>();
		head.setNext(end);
		end.setPrev(head);
	}

	/* GETTERS & SETTERS */
	public Node<Item> getHead() {
		return head;
	}

	public Node<Item> getEnd() {
		return end;
	}

	public int getLength() {
		return length;
	}
	
	/* Adds an item to the list, in correct position */
	public boolean add(Item element){
		Node<Item> aux = head;
		length++;
		while ((aux.getNext() != end) && (comp.compare(aux.getNext().getItem(), element) < 0)){
			aux = aux.getNext();
		}
		Node<Item> node = new Node<>(element, aux.next, aux);
		aux.getNext().setPrev(node);
		aux.setNext(node);
		return true;
	}
	
	/* Adds all elements from the collection to the list */
	public boolean addAll(Collection<? extends Item> c){
		for (Item e : c)
			add(e);
		return true;
	}
	
	/* Returns the item at the INDEX position */
	public Item getItem(int index){
		Node<Item> node = getNode(index);
		if (node == null)
			return null;
		return node.getItem();
	}
	
	/* Returns the node at the INDEX position */
	public Node<Item> getNode(int index){
		Node<Item> aux = head.getNext();
		if ((index > length) || (index < 0))
			return null;
		for (int i = 0; i < index; i++)
			aux = aux.getNext();
		return aux;
	}
	
	/* Returns the position(index) of an item */
	public int indexOf(Item item){
		Node<Item> aux = head.getNext();
		int index = 0;
		while (aux.getNext() != end){
			if (aux.getItem().equals(item))
				return index;
			index++;
			aux = aux.getNext();
		}
		return -1;
	}

	/* Returns the position(index) of a node */
	public int indexOf(Node<Item> node){
		return indexOf(node.getItem());
	}
	
	/* Checks if the list contains an item */
	public boolean contains(Item item){
		ListIterator<Item> it = listIterator();
		while (it.hasNext())
			if (it.next().equals(item))
				return true;
		return false;
	}
	
	/* Checks if the list contains a node */
	public boolean contains(Node<Item> node){
		return contains(node.getItem());
	}

	/* Removes and returns the item
	 *  at the INDEX position */
	public Item remove(int index){
		Node<Item> aux = head.getNext();
		if ((index > length) || (index < 0))
			return null;
		length--;
		for (int i = 0; i < index; i++)
			aux = aux.getNext();
		aux.getNext().setPrev(aux.getPrev());
		aux.getPrev().setNext(aux.getNext());
		return aux.getItem();
	}
	
	/* Removes an Item from the list */
	public boolean remove(Item item){
		if (item == null)
			return false;
		ListIterator<Item> it = listIterator();
		while (it.hasNext())
			if (it.next().getId().equals(item.getId())){
				it.remove();
				length--;
				return true;
			}
		return false;
	}
	
	/* Removes all the elements of a collection
	 *  from the list. Returns false if it 
	 *  can't delete an element */
	public boolean removeAll(Collection<? extends Item> c){
		for (Item e : c){
			if (!remove(e))
				return false;
		}
		return true;
	}
	
	/* Checks if the list is empty */
	public boolean isEmpty(){
		return (head.getNext() == null);
	}
	
	/* Returns a ListIterator for the list */
	public ListIterator<Item> listIterator(){
		return new ItemIterator<Item>(head, -1);
	}
	
	/* Returns a ListIterator, starting at INDEX */ 
	public ListIterator<Item> listIterator(int index){
		Node<Item> aux = head.getNext();
		if (index > length)
			return null;
		for (int i = 0; i < index; i++)
			aux = aux.getNext();
		return new ItemIterator<Item>(aux, index);
	}
	
	/* Sets the item at the INDEX position to newItem */
	public void set(int index, Item newItem){
		ListIterator<Item> it = listIterator();
		while(it.hasNext())
			if (it.nextIndex() == index){
				it.next();
				it.set(newItem);
				return;
			}
			else
				it.next();
	}
	
	/* Finds an item by ID */
	public int findItem(int itemId){
		ListIterator<Item> it = listIterator();
		while (it.hasNext()){
			if (it.next().getId().equals(itemId))
				return it.nextIndex() - 1;
		}
		return -1;
	}
	
	/* Calculates and returns the total price of the
	 * items in the list.*/
	public Double getTotalPrice(){
		ListIterator<Item> it = listIterator();
		Double total = new Double(0.0);
		while(it.hasNext())
			total += it.next().getPrice();
		return total;
	}
	
	/* toString method */
	public String toString(){
		ListIterator<Item> it = listIterator();
		StringBuilder s = new StringBuilder("[");
		if (length == 0)
			return s.append("]").toString();
		while (it.hasNext())
			if (it.nextIndex() == length - 1)
				s.append(it.next() + "]");
			else
				s.append(it.next() + ", ");
		return s.toString();
	}
}
