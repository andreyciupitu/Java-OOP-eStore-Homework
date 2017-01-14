package store;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import store.department.*;
import store.items.*;

public class Test {

	public static void main(String[] args) throws Exception{
		BufferedReader fin;
		PrintWriter fout;
		
		/* Obtain the store instance */
		Store store = Store.getInstance();
		
		/* Read the store data */
		store.loadDepartments("input\\store.txt");
		
		/* Read the Customer data */
		store.loadCustomers("input\\customers.txt");
		
		/* Read & handle the events */
		fin = new BufferedReader(new FileReader("input\\events.txt"));
		fout = new PrintWriter(new FileWriter("result.txt"));
		int count = Integer.parseInt(fin.readLine());
		for (int i = 0; i < count; i++){
			int depId, itemId;
			double price;
			String name, list;
			Customer client;
			Item element = null;
			StringTokenizer tokens = new StringTokenizer(fin.readLine(), ";");
			
			/* Decide which action to take */
			switch(tokens.nextToken()){
			
				/* Add an item to a Customer's ShoppingCart or WishList */
				case "addItem":
					itemId = Integer.parseInt(tokens.nextToken());
					
					/* Find the item in the store */
					element = store.getItem(itemId);
					list = tokens.nextToken();
					
					/* Find the Customer */
					client = store.getCustomers().get(tokens.nextToken());
					
					/* Add the Item to the list */
					if (list.equals("WishList"))
						client.addToWList(element.clone());
					else
						client.addToCart(element.clone());
					break;
					
				/* Deletes and item from a Customer's ShoppingCart or WishList */
				case "delItem":
					itemId = Integer.parseInt(tokens.nextToken());
				
					/* Find the item in the store */
					element = store.getItem(itemId);
				
					list = tokens.nextToken();
				
					/* Find the Customer */
					client = store.getCustomers().get(tokens.nextToken());
				
					/* Add the Item to the list */
					if (list.equals("WishList"))
						client.removeFromWList(element);
					else
						client.removeFromCart(element);
					break;
					
				/* Add an item to a Department */
				case "addProduct":
					depId = Integer.parseInt(tokens.nextToken());
					itemId = Integer.parseInt(tokens.nextToken());
					price = Double.parseDouble(tokens.nextToken());
					name = tokens.nextToken();
					store.getDepartment(depId).addItem(new Item(name, itemId, price, depId));
					break;
					
				/* Change an item's price */
				case "modifyProduct":
					depId = Integer.parseInt(tokens.nextToken());
					itemId = Integer.parseInt(tokens.nextToken());
					price = Double.parseDouble(tokens.nextToken());
					store.getDepartment(depId).modifyItem(itemId, price);
					break;
					
				/* Delete an item from the Store */
				case "delProduct":
					itemId = Integer.parseInt(tokens.nextToken());
					
					/* Find the right Department */
					for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
						if (d.getValue().getItems().get(itemId) != null)
							d.getValue().removeItem(itemId);
					break;
				
				/* Strategy pattern */
				case "getItem":
					client = store.getCustomers().get(tokens.nextToken());
					fout.println(client.getItem());
					break;
					
				/* Write the items in the Customer's List */
				case "getItems":
					list = tokens.nextToken();
					client = store.getCustomers().get(tokens.nextToken());
					if (list.equals("WishList"))
						fout.println(client.getWlist());
					else
						fout.println(client.getCart());
					break;
					
				/* Write the total price of the items in the List */
				case "getTotal":
					list = tokens.nextToken();
					client = store.getCustomers().get(tokens.nextToken());
					if (list.equals("WishList"))
						fout.printf("%.2f%n", client.getWlist().getTotalPrice());
					else
						fout.printf("%.2f%n", client.getCart().getTotalPrice());
					break;
					
				/* Apply discounts from the accept method */
				case "accept":
					depId = Integer.parseInt(tokens.nextToken());
					client = store.getCustomers().get(tokens.nextToken());
					client.getCart().visit(store.getDepartment(depId));
					break;
					
				/* Write the observers for the specified Department */
				case "getObservers":
					depId = Integer.parseInt(tokens.nextToken());
					fout.println(store.getDepartment(depId).getSubscribers());
					break;
				
				/* Write the Customer's notifications */
				case "getNotifications":
					client = store.getCustomers().get(tokens.nextToken());
					fout.println(client.getNotifications());
					break;
				default:
					break;
			}
		}
		fin.close();
		fout.close();
	}

}