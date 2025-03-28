package javabasic.ch08;

public class ClassExtends extends ParentsClass {

	@Override
	public void info() {
		System.out.println("자녀클래스입니다.");
	}
	
	// 부모클래스의 info()를 사용하고 싶다면 함수를 정의합니다.
	public void infoParent() {
		super.info();
	}
	
	public static void main(String[] args) {
		ParentsClass test = new ClassExtends();
		/* 부모클래스로 선언하고 자녀클래스의 생성자로 메모리 할당시....
		 * 1. 자녀클래스를 사용하고자할 때 선언을 부모클래스로 할 수 있습니다.
		 * 2. 사용할 수 있는 것은 부모클래스에 선언된 멤버변수, 메서드만 사용가능합니다.
		 * (밖에서 보기에는 부모클래스로 봅니다 - 겉모습은 부모클래스)
		 *   ==> 부모클래스 안에 있는 것만 쓸 수 있다는 얘기입니다.
		 * 3. 부모클래스에 있는 함수를 자녀클래스가 override 했을때 처리되는
		 * 내용은 자녀클래스의 메서드(함수)로 처리됩니다.
		 * 4. 자녀클래스에만 존재하는 변수또는 함수(메서드)를 사용하고 싶을때
		 * 	 ==> 다운캐스팅을 하면 됩니다.   
		 */
		
		// 사용할 수 있는 것은 선언한 클래서에 정의되어있는 것을
		// 사용할 수 있고
		// 구현되는 내용은 생성자를 기준으로 처리됩니다.
		test.info();
		((ClassExtends)test).infoParent();
	}
}
