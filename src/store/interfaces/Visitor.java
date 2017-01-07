package store.interfaces;

import store.department.*;

public interface Visitor{
	public void visit(Department d);
	public void visit(BookDepartment d);
	public void visit(MusicDepartment d);
	public void visit(SoftwareDepartment d);
	public void visit(VideoDepartment d);
}
