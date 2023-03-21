package project_model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subject {
	private static int count = 0;
	private String name;
	//Id tượng trưng cho vị trí đỉnh của môn học trong đề thị hamilton
	private int id;
	private LocalDate dateExam;
	private Set<Teacher> listTeacher;
	
	public Subject(String name) {
		super();
		this.name = name;
		id = count++;
		
		this.listTeacher = new HashSet<>();
	}
	
	public boolean addTeacher(Teacher teacher) {
		if(teacher.signUpForTeaching()) {
			listTeacher.add(teacher);
			return true;
		}
		
		return false;
	}
	
	public boolean equalsTeacher(Subject sub) {
		for(Teacher teacher : sub.listTeacher) {
			if(this.listTeacher.contains(teacher)) {
				return true;
			}
		}
		
		return false;
	}
	
	public LocalDate getDateExam() {
		return dateExam;
	}

	public void setDateExam(LocalDate dateExam) {
		this.dateExam = dateExam;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Môn " + name + " thuộc đỉnh " + id;
	}
	
	
}
