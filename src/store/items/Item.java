package store.items;

public class Item{
	private String name;
	private Integer id;
	private Double price;
	private Integer departmentId;
	
	public Item(){
		this.setName("");
		this.setId(0);
		this.setPrice(0.0);
		this.setDepartmentId(-1);
	}
	
	public Item(String name, int id, double price, Integer departmentId){
		this.setName(name);
		this.setId(id);
		this.setPrice(price);
		this.setDepartmentId(departmentId);
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
	
	public Integer getDepartmentId(){
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId){
		this.departmentId = departmentId;
	}

	/* Equals method */
	public boolean equals(Object obj){
		if (!(obj instanceof Object))
			return false;
		Item item = (Item)obj;
		return (name.equals(item.name) && id.equals(item.id) && price.equals(item.price));
	}
	
	/* toString method */
	public String toString(){
		return name + ";" + id.toString() + ";" + price.toString();
	}
}
