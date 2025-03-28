package javabasic.ch06;

public class Variable {

	// 멤버변수
	private int numMember;
	// static변수(클래스변수)
	private static int numStatic;
	
	public void number() {
		// 지역변수
		int numLocal1;// number() 안에서만 사용할 수 있습니다.
		// 멤버변수: 사용할 수 있습니다.
		// 클래스변수: 사용할 수 있습니다.
	}
	
	// static 메서드로 다른 곳에서 사용할때 클래스이름으로 접근해서
	// 사용합니다.
	public static void numberStatic() {
		// 지역변수
		int numLocal2;// numberStatic() 안에서만 사용할 수 있습니다.
		// 멤버변수: 사용할 수 없습니다.
		// 클래스변수: 사용할 수 있습니다.
	}
}
