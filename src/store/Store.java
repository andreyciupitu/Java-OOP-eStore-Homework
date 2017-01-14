package store;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import store.department.*;
import store.items.*;
import store.strategy.StrategyFactory;

public class Store{
	private static Store store = null;
	private String name;
	private HashMap<String, Customer> customers;
	private HashMap<Integer, Department> departments;
	
	private Store(){
		this.name = "";
		this.customers = new HashMap<String, Customer>();
		this.departments = new HashMap<Integer, Department>();
	}
	
	/* GETTERS & SETTERS */
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/* Obtain the Store instance */
	public static Store getInstance(){
		if (store == null)
			store = new Store();
		return store;
	}

	public HashMap<String, Customer> getCustomers(){
		return customers;
	}
	
	public HashMap<Integer, Department> getDepartments(){
		return departments;
	}
	
	/* Adds a new customer to the store */
	public void enter(Customer c){
		customers.put(c.getName(), c);
	}
	
	/* Removes a customer from the store */
	public void exit(Customer c){
		customers.remove(c);
	}
	
	/* Adds a new department */
	public void addDepartment(Department d){
		departments.put(d.getId(), d);
	}
	
	/* Finds a department by ID */
	public Department getDepartment(Integer x){
		return departments.get(x);
	}
	
	/* Finds an item in the store by ID */
	public Item getItem(int itemId){
		for (Map.Entry<Integer, Department> d : departments.entrySet())
			if (d.getValue().getItems().get(itemId) != null)
				return d.getValue().getItems().get(itemId);
		return null;
	}
	
	/* Creates a ShoppingCart */
	public ShoppingCart getShoppingCart(Double x){
		return new ShoppingCart(x);
	}
	
	/* Read the store data from file */
	public void loadDepartments(String path){
		String line;
		BufferedReader fin = null;
		
		/* Read the store data */
		try{
			fin = new BufferedReader(new FileReader(path));
		}
		catch(FileNotFoundException e){
			System.out.println("Can't open the file.");
			e.printStackTrace();
		}
		
		try{
			/* Read the store name */
			store.setName(fin.readLine());
		
			/* Read the data & build the departments */
			while ((line = fin.readLine()) != null){
				StringTokenizer tokens = new StringTokenizer(line, ";"); 
				HashMap<Integer, Item> items = new HashMap<Integer, Item>();
				
				/* Read department name & ID */
				String name = tokens.nextToken();
				int departmentId = Integer.parseInt(tokens.nextToken());
				Department.DepartmentBuilder builder = new Department.DepartmentBuilder(name, departmentId);
			
				/* Read the items belonging in this department */
				int count = Integer.parseInt(fin.readLine());
				for (int i = 0; i < count; i++){
					tokens = new StringTokenizer(fin.readLine(), ";");
					Item item = new Item(tokens.nextToken(), new Integer(tokens.nextToken()),
							new Double(tokens.nextToken()), departmentId);
					items.put(item.getId(), item);
				}
				builder.initItems(items);
			
				/* Build the department */
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
		}
		catch(IOException e){
			System.out.println("Error encountered while reading from file.");
			e.printStackTrace();
		}
		finally{
			try{
				if (fin != null)
					fin.close();
			}
			catch(IOException e){
				System.out.println("Can't close the file.");
				e.printStackTrace();
			}
		}
	}
	
	/* Read the customer data from file */
	public void loadCustomers(String path){
		String line;
		BufferedReader fin = null;
		try{
			fin	= new BufferedReader(new FileReader(path));
		}
		catch (FileNotFoundException e){
			System.out.println("Can't open file.");
		}
		try{
			int count = Integer.parseInt(fin.readLine());
			for (int i = 0; i < count; i++){
				line = fin.readLine();
				StringTokenizer tokens = new StringTokenizer(line, ";");
				String name = tokens.nextToken();
				double budget = Double.parseDouble(tokens.nextToken());
				StrategyFactory factory = StrategyFactory.getInstance();
				store.enter(new Customer(name, budget, factory.createStrategy(tokens.nextToken())));
			}
		}
		catch (IOException e){
			System.out.println("Error encountered while reading from file.");
		}
		finally{
			try{
				if (fin != null)
					fin.close();
			}
			catch (IOException e){
				System.out.println("Can't close the file.");
				e.printStackTrace();
			}
		}
	}
}

