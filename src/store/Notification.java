package store;

import java.util.Date;

public class Notification{
	public enum NotificationType{
		ADD, REMOVE, MODIFY
	}
	
	private final Date date;
	private final Integer itemId;
	private final Integer departmentId;
	private final NotificationType type;
	
	public Notification(Date date, NotificationType type, int itemId, int departmentId){
		this.date = date;
		this.type = type;
		this.departmentId = departmentId;
		this.itemId = itemId;
	}

	public Date getDate(){
		return date;
	}
	
	public NotificationType getType(){
		return type;
	}

	public Integer getDepartmentId(){
		return departmentId;
	}

	public Integer getItemId(){
		return itemId;
	}
	
	public String toString(){
		return type.toString() + ";" + itemId + ";" + departmentId;
	}
}
