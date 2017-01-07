package store;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import store.department.*;
import store.items.*;

public class Test {

	public static void main(String[] args) throws Exception{
		Store store = Store.getInstance();
		BufferedReader fin = new BufferedReader(new FileReader("input\\store.txt"));
		String line;
		
		store.setName(fin.readLine());
		while ((line = fin.readLine()) != null){
			StringTokenizer tokens = new StringTokenizer(line, ";"); 
			HashMap<Integer, Item> items = new HashMap<Integer, Item>();
			String name = tokens.nextToken();
			int departmentId = Integer.parseInt(tokens.nextToken());
			Department.DepartmentBuilder builder = new Department.DepartmentBuilder(name, departmentId);
			line = fin.readLine();
			Integer count = new Integer(line);
			for (int i = 0; i < count; i++){
				line = fin.readLine();
				tokens = new StringTokenizer(line, ";");
				Item item = new Item(tokens.nextToken(), new Integer(tokens.nextToken()),
						new Double(tokens.nextToken()), departmentId);
				items.put(item.getId(), item);
			}
			builder.initItems(items);
			switch (name){
				case "BookDepartment":
					store.addDepartment(builder.build(DepartmentFactory.DepartmentType.BOOK));
					break;
				case "MusicDepartment":
					store.addDepartment(builder.build(DepartmentFactory.DepartmentType.MUSIC));
					break;
				case "SoftwareDepartment":
					store.addDepartment(builder.build(DepartmentFactory.DepartmentType.SOFTWARE));
					break;
				case "VideoDepartment":
					store.addDepartment(builder.build(DepartmentFactory.DepartmentType.VIDEO));
					break;
				default:
					break;
			}
		}
		fin.close();
		
		fin = new BufferedReader(new FileReader("input\\customers.txt"));
		int count = Integer.parseInt(fin.readLine());
		for (int i = 0; i < count; i++){
			line = fin.readLine();
			StringTokenizer tokens = new StringTokenizer(line, ";");
			store.enter(new Customer(tokens.nextToken(), new Double(tokens.nextToken())));
		}
		fin.close();
		
		fin = new BufferedReader(new FileReader("input\\events.txt"));
		PrintWriter fout = new PrintWriter(new FileWriter("results.txt"));
		count = Integer.parseInt(fin.readLine());
		for (int i = 0; i < count; i++){
			int depId, itemId;
			double price;
			String name, list;
			Customer client;
			Item element = null;
			StringTokenizer tokens = new StringTokenizer(fin.readLine(), ";");
			String task = tokens.nextToken();
			System.out.println(task);
			switch(task){
				case "addItem":
					itemId = Integer.parseInt(tokens.nextToken());
					for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
						if (d.getValue().getItems().get(itemId) != null)
							element = d.getValue().getItems().get(itemId);
					list = tokens.nextToken();
					client = store.getCustomers().get(tokens.nextToken());
					if (list.equals("WishList")){
						client.getWlist().add(element);
						store.getDepartment(element.getDepartmentId()).addObserver(client);
					}
					else
						client.getCart().add(element);
					break;
				case "delItem":
					break;
				case "addProduct":
					depId = Integer.parseInt(tokens.nextToken());
					itemId = Integer.parseInt(tokens.nextToken());
					price = Double.parseDouble(tokens.nextToken());
					name = tokens.nextToken();
					store.getDepartment(depId).addItem(new Item(name, itemId, price, depId));
					break;
				case "modifyProduct":
					depId = Integer.parseInt(tokens.nextToken());
					itemId = Integer.parseInt(tokens.nextToken());
					price = Double.parseDouble(tokens.nextToken());
					store.getDepartment(depId).modifyItem(itemId, price);
					break;
				case "delProduct":
					itemId = Integer.parseInt(tokens.nextToken());
					for (Map.Entry<Integer, Department> d : store.getDepartments().entrySet())
						if (d.getValue().getItems().get(itemId) != null)
							d.getValue().removeItem(itemId);
					break;
				case "getItem":
					client = store.getCustomers().get(tokens.nextToken());
					break;
				case "getItems":
					list = tokens.nextToken();
					client = store.getCustomers().get(tokens.nextToken());
					if (list.equals("WishList"))
						fout.println(client.getWlist());
					else
						fout.println(client.getCart());
					break;
				case "getTotal":
					list = tokens.nextToken();
					client = store.getCustomers().get(tokens.nextToken());
					if (list.equals("WishList"))
						fout.println(client.getWlist().getTotalPrice());
					else
						fout.println(client.getCart().getTotalPrice());
					break;
				case "accept":
					depId = Integer.parseInt(tokens.nextToken());
					client = store.getCustomers().get(tokens.nextToken());
					client.getCart().visit(store.getDepartment(depId));
					break;
				case "getObservers":
					depId = Integer.parseInt(tokens.nextToken());
					fout.println(store.getDepartment(depId).getSubscribers());
					break;
				case "getNotifications":
					client = store.getCustomers().get(tokens.nextToken());
					fout.print(client.getNotifications());
					break;
			}
		}
		fin.close();
		fout.close();
	}

}
