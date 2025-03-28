package javabasic.ch05;
/* package 는 JAVA파일(클래스)이 존재하는 경로(폴더)를 의미합니다
 * java project에서는 src 가 루트폴더가 됩니다.
 * 패키지는 제일 윗줄에 있어야 합니다.
 */


/* public 으로 지정된 클래스는 파일에 한개만 존재합니다.
 * 
 */
public class Student {

	// 멤버변수(클래스 블럭에 선언)
	private int studentId;
	private String studentName;
	
	// static 변수
	public static int serialNum = 1000;
	
	// 메서드
	public void setStudentId(int studentId) {
		this.studentId = studentId;
		//super : 부모클래스를 의미합니다.
		// super. 는 재정의한 메서드(Override) 가 있을때
		// 재정의한 메서드 말고 부모클래스가 구현한 메서드를
		// 사용하고 싶을때 super. 로 처리합니다.
	}
	public int getStudentId() {
		return studentId;
	}
	
	public int add(int num1, int num2) {
		int sum;// 지역변수(메서드 내부에 선언)
		sum = num1 + num2;
		return sum;
	}
	
	public void info() {
		System.out.println("studentId = " + studentId
			+ ", studentName = " + studentName);
	}
	
	/* 메서드의 4가지 유형
	 * 1. 리턴도 없고, 매개변수도 없는 메서드
	 * 	public void 함수이름() {}
	 * 2. 리턴이 없고, 매개변수는 있는 메서드
	 *  public void 함수이름(매개변수) {}
	 * 3. 리턴은 있고, 매개변수는 없는 메서드
	 *  public 리턴자료형 함수이름() {return 리턴자료형의데이터;}
	 * 4. 리턴도 있고, 매개변수도 있는 메서드
	 *  public 리턴자료형 함수이름(매개변수) {return 리턴자료형의데이터;}
	 * 매개변수는 여러개를 사용할수 있고 ','를 사용하여 구분합니다.
	 * 리턴은 데이터가 반드시 1개 입니다.
	 */
	
	public static void main(String[] args) {
		Student student1 = new Student();
		Student student2 = new Student();
		Student student3 = new Student();
		student1.setStudentId(1);
		// 생성된 student1 을 인스턴스라고 부릅니다.
		System.out.println(student1.getStudentId());
		System.out.println(student1.studentId);
		
		//student1.serialNum = 1001;
		//student2.serialNum = 1002;
		//student3.serialNum = 1003;
		Student.serialNum = 1005;// static 변수의 실제 사용하는 방법
		System.out.println(student1.serialNum);
		System.out.println(student2.serialNum);
		System.out.println(student3.serialNum);
	}

	/* 생성자 (일반클래스에는 생성자가 있습니다.)
	 * 클래스내에 개발자가 구현한 생성자가 없다면 자바컴파일러가
	 * 기본생성자를 자동으로 만들어 줍니다.
	 * 기본생성자: public 클래스이름() {}
	 * 개발자 구현한 생성자가 있다면 자바컴파일러는 기본생성자를 만들지 않습니다.
	 * 
	 */
	// 생성자
	// - 기본생성자
	public Student() {
	
	}
	
	// - 멤버변수를 초기값을 주는 생성자
	public Student(int studentId, String studentName) {
		this.studentId = studentId;
		this.studentName = studentName;
	}
}






