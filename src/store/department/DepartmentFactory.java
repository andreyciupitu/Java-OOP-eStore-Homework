package store.department;

/* Factory Pattern for creating departments */
public class DepartmentFactory{
	public static DepartmentFactory factory = null;
	
	private DepartmentFactory(){};
	
	public static DepartmentFactory getInstance(){
		if (factory == null)
			factory = new DepartmentFactory();
		return factory;
	}
	
	public enum DepartmentType{
		BOOK, MUSIC, SOFTWARE, VIDEO
	}
	
	public Department createDepartment(Department.DepartmentBuilder builder, DepartmentType type){
		switch(type){
			case BOOK:
				return new BookDepartment(builder);
			case MUSIC:
				return new MusicDepartment(builder);
			case SOFTWARE:
				return new SoftwareDepartment(builder);
			case VIDEO:
				return new VideoDepartment(builder);
			default:
				return null;
		}
	}
}
