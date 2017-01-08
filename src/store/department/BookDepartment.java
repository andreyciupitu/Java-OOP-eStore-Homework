package store.department;

import store.items.ShoppingCart;

public class BookDepartment extends Department{
	public BookDepartment(DepartmentBuilder builder){
		super(builder);
	}

	public void accept(ShoppingCart cart){
		cart.visit(this);
	}
}
