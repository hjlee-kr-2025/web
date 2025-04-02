package javabasic.ch13;

public class TestMyNumber {

	// 함수의 인터페이스를 람다식으로 만들때 하나의 명령문만 있어야 합니다.
	public static void main(String[] args) {
		MyNumber max = (x, y) -> (x >= y) ? x : y;
		System.out.println(max.getMax(10, 20));
	}
}
