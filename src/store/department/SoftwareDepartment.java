package store.department;

import store.items.ShoppingCart;

public class SoftwareDepartment extends Department{
	public SoftwareDepartment(DepartmentBuilder builder){
		super(builder);
	}

	public void accept(ShoppingCart cart){
		cart.visit(this);
	}
}
