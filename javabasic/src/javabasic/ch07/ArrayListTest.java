package javabasic.ch07;

import java.util.ArrayList;
/*
 * ArrayList는 java.util 패키지에 구현되어있습니다.
 * 프로그램 작성시 java.lang 패키지에 있는 클래스를 제외하고
 * import 를 작성하여 클래스가 어디에 존재하는지 적어야 합니다.
 * ==> 같은 패키지에 속한 클래스는 import를 작성하지 않아도 됩니다.
 */

public class ArrayListTest {

	// ArrayList를 선언하고 생성하는 방법
	private ArrayList<String> listName1 = new ArrayList<String>();
	private ArrayList<String> listName2 = new ArrayList<>();
	
	public static void main(String[] args) {
		ArrayListTest listTest = new ArrayListTest();
		
		// 데이터 추가
		listTest.listName1.add("1번이름리스트추가-1");
		listTest.listName1.add("1번이름리스트추가-2");
		listTest.listName1.add("1번이름리스트추가-3");
		listTest.listName1.add("1번이름리스트추가-4");
		listTest.listName1.add("1번이름리스트추가-5");
		listTest.listName2.add("2번이름리스트추가-1");
		listTest.listName2.add("2번이름리스트추가-2");
		listTest.listName2.add("2번이름리스트추가-3");
		listTest.listName2.add("2번이름리스트추가-4");
		listTest.listName2.add("2번이름리스트추가-5");
		
		System.out.println(listTest.listName1);
		System.out.println(listTest.listName2);
		
		System.out.println("===== get(), remove() =====");
		// get, remove
		for (int i = 0 ; i < listTest.listName1.size() ; ++i) {
			System.out.println(listTest.listName1.get(i));
		}
		/* remove() 메서드는 값을 꺼내기 때문에 사용한 후 ArrayList 내의
		 * 저장된 데이터의 개수가 줄어듭니다.
		 * */
		System.out.println("======= listName2 의 remove ============");
		int removeSize = listTest.listName2.size();//5
		for (int i = 0 ; i < removeSize ; ++i) {
			System.out.println(listTest.listName2.remove(0));
			// 1번째 실행후 size() => 4
			System.out.println("size : " + listTest.listName2.size());
			System.out.println(listTest.listName2);
			// -> 0번을 꺼내서 기본의 1번
			// 2번째 실행후 size() => 3
			// 3번째 실행후 size() => 2
		}
	//	System.out.println("======= 실행후 결과 ======");
	//	System.out.println(listTest.listName1);
	//	System.out.println(listTest.listName2);
	}
}






