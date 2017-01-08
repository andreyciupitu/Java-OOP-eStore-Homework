package store.department;

import store.items.ShoppingCart;

public class VideoDepartment extends Department{
	public VideoDepartment(DepartmentBuilder builder){
		super(builder);
	}

	public void accept(ShoppingCart cart){
		cart.visit(this);
	}
}
