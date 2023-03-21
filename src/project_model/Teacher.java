package project_model;

import java.util.Date;

public class Teacher {
	private String id, name;
	private Date birthDate;
	private int countSubject = 0;
	public Teacher(String id) {
		super();
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		Teacher that = (Teacher)obj;
		return this.id.equalsIgnoreCase(that.id);
	}
	@Override
	public String toString() {
		return "Giao Vien " + id;
	}
	
	public boolean signUpForTeaching() {
		countSubject++;
		return countSubject <= 5;
	}
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}
	
	
}
