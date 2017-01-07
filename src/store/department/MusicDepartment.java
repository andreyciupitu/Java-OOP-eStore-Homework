package store.department;

import store.items.ShoppingCart;

public class MusicDepartment extends Department{
	public MusicDepartment(DepartmentBuilder builder){
		super(builder);
	}

	public void accept(ShoppingCart cart){
		cart.visit(this);
	}
}
