package store.items;

public class Item{
	private String name;
	private Integer id;
	private Double price;
	
	public Item(){
		this.setName("");
		this.setId(0);
		this.setPrice(0.0);
	}
	
	public Item(String name, int id, double price){
		this.setName(name);
		this.setId(id);
		this.setPrice(price);
	}

	/* GETTERS & SETTERS */
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Double getPrice(){
		return price;
	}

	public void setPrice(Double price){
		this.price = price;
	}
}
