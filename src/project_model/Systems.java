package project_model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Systems {
	private String name;
	private Set<Subject> listSubjects;
	private int[][] matrix;
	private Map<LocalDate, Subject> lichThi;

	public Systems(String name, Set<Subject> listSubjects) {
		this.name = name;
		this.listSubjects = listSubjects;
		matrix = new int[listSubjects.size()][listSubjects.size()];
		lichThi = new TreeMap<>();
	}

	public void addSubject(Subject subject) {
		listSubjects.add(subject);
		matrix = new int[listSubjects.size()][listSubjects.size()];
	}

	/**
	 * Phương thức đăng ký lịch thi cho môn học Nếu mà trong hệ thống chưa có môn
	 * nào đăng kí lịch thi thì môn đó sẽ được đăng kí thẳng nào ngày đã cấp.
	 * 
	 * Nếu trong hệ thống đã có môn đăng kí lịch thi hoặc ngày muốn đãng kí đăng
	 * được môn khách đăng kí trước đó thì sẽ không cho đăng kí.
	 * 
	 * Hệ thống sẽ kiểm tra các môn học đã đăng kí thi. Nếu môn học đó có cùng giáo
	 * viên giảng dậy với môn học mới thì phải xem xét đến ngày đăng kí thi của môn
	 * mới. Nếu ngày thi của môn mới quá xát với môn đã đăng kí trước đó (cùng giáo
	 * viên và 2 ngày thi này cách nhau bé hơn 1 ngày) thì không cho đăng kí. Ngược
	 * lại thì cho.
	 * 
	 * 
	 * @param subject
	 * @param date
	 * @return
	 */
	public boolean registerForTheTest(Subject subject, LocalDate date) {
		if (!listSubjects.contains(subject)) {
			return false;
		}

		if (lichThi.isEmpty()) {
			lichThi.put(date, subject);
			subject.setDateExam(date);

			return true;
		} else {
			if (lichThi.containsKey(date)) {
				return false;
			}

			for (LocalDate d : lichThi.keySet()) {
				// lấy môn học từ ngày.
				if (lichThi.get(d).equalsTeacher(subject)) {
					// ChronoUnit.DAYS.between(d, date): Kiểm tra xem khoảng cách 2 ngày này là bao
					// nhiều (kết quả là số âm hoặc là dương)
					if (Math.abs(ChronoUnit.DAYS.between(d, date)) <= 1) {
						return false;
					}
				}
			}

			lichThi.put(date, subject);
			subject.setDateExam(date);

			// Khi đã đăng kí đến môn cuối thì sẽ tạo ma trận Hamilton.
			if (lichThi.size() == listSubjects.size()) {
				createMatrix();
			}

			return true;
		}
	}

	/**
	 * Khởi tạo ma trận
	 */
	public void createMatrix() {
		int i = 0;
		for (; i < lichThi.size() - 1; i++) {
			matrix[getSubjectInLichThiByIndex(i).getId()][getSubjectInLichThiByIndex(i + 1).getId()] = 1;
			matrix[getSubjectInLichThiByIndex(i + 1).getId()][getSubjectInLichThiByIndex(i).getId()] = 1;
		}

		matrix[getSubjectInLichThiByIndex(i).getId()][getSubjectInLichThiByIndex(0).getId()] = 1;
		matrix[getSubjectInLichThiByIndex(0).getId()][getSubjectInLichThiByIndex(i).getId()] = 1;

		printMatrix();
	}

	/**
	 * Vì ta đã tạo danh sách ngày thi trên hệ thống là Map, không thế lấy theo
	 * index được nên tôi sẽ tạo hàm lấy theo index
	 * 
	 * @param index
	 * @return
	 */
	public Subject getSubjectInLichThiByIndex(int index) {
		for (LocalDate date : lichThi.keySet()) {
			if (index == 0) {
				return lichThi.get(date);
			} else {
				index--;
			}
		}

		return null;
	}

	/**
	 * Hàm in ma trận
	 */
	public void printMatrix() {
		for (int[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

	public static void main(String[] args) {
		Teacher t1 = new Teacher("t1");
		Teacher t2 = new Teacher("t2");
		Teacher t3 = new Teacher("t3");
		Teacher t4 = new Teacher("t4");
		Teacher t5 = new Teacher("t5");

		Subject m1 = new Subject("m1");
		Subject m2 = new Subject("m2");
		Subject m3 = new Subject("m3");
		Subject m4 = new Subject("m4");
		Subject m5 = new Subject("m5");
		Subject m6 = new Subject("m6");
		Subject m7 = new Subject("m7");
		
		m1.addTeacher(t1);
		m1.addTeacher(t2);
		
		m2.addTeacher(t1);
		m2.addTeacher(t4);
		
		m3.addTeacher(t2);
		m3.addTeacher(t5);
		
		m4.addTeacher(t3);
		m4.addTeacher(t5);
		
		m5.addTeacher(t2);
		m5.addTeacher(t4);
		
		m6.addTeacher(t1);
		m6.addTeacher(t4);
		
		m7.addTeacher(t3);
		m7.addTeacher(t5);

		Set<Subject> listSubjects = new HashSet<>();
		listSubjects.add(m1);
		listSubjects.add(m2);
		listSubjects.add(m3);
		listSubjects.add(m4);
		listSubjects.add(m5);
		listSubjects.add(m6);
		listSubjects.add(m7);

		Systems test = new Systems("Hệ thống thử", listSubjects);

		System.out.println(test.registerForTheTest(m1, LocalDate.of(2023, 1, 1)));
		System.out.println(test.registerForTheTest(m2, LocalDate.of(2023, 1, 3)));
		System.out.println(test.registerForTheTest(m3, LocalDate.of(2023, 1, 4)));
		System.out.println(test.registerForTheTest(m4, LocalDate.of(2023, 1, 2)));
		System.out.println(test.registerForTheTest(m5, LocalDate.of(2023, 1, 6)));
		System.out.println(test.registerForTheTest(m6, LocalDate.of(2023, 1, 8)));
		System.out.println(test.registerForTheTest(m7, LocalDate.of(2023, 1, 9)));
	}
}
