package arraylist;

public class StudentTest {

	public static void main(String[] args) {
		
		Student sLee = new Student(101, "Lee");
		
		sLee.addSubject("korean", 100);
		sLee.addSubject("math", 97);
		sLee.showStudentInfo();
		System.out.println();
		
		Student sKim = new Student(102, "Kim");
		sKim.addSubject("korean", 88);
		sKim.addSubject("math", 90);
		sKim.showStudentInfo();
		
	}

}

