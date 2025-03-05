package com.shopdb.util;

import java.util.Arrays;

import com.shopdb.board.service.Service;

public class Execute {

	// 서비스를 실행시키고, 로그(개발자와 관리자)를 출력하는 메서드
	public static Object execute(Service service,
		Object obj) throws Exception {
		// 결과를 저장할 변수 선언
		Object result = null;
		
		System.out.println("<<<---- 실행 로그 출력 ---->>>");
		System.out.println("---------------------------");
		
		// 시작 시간 저장 (ns)
		Long start = System.nanoTime();
		
		// 실행 객체 정보를 출력
		// Service interface 객체를 class 객체로 바꿔줍니다.
		// ----> service.getClass()
		// 클래스 이름과 패키지를 가져오는 메서드
		// ----> .getName()
		System.out.println("실행 객체 이름 : "
			+ service.getClass().getName());
		
		// 넘어오는 데이터 정보 출력
		// 서비스로 넘어가는 데이터
		// 배열인지 물어봅니다. "obj instanceof Object[]"
		// ===> obj 변수가 Object[] 배열이면 true
		// 배열이면 Arrays.toString() 사용합니다.
		// 배열안의 데이터를 하나하나 보여주기 위해서
		System.out.println("서비스로 넘어 가는 데이터 : "
			+ ((obj instanceof Object[])?Arrays.toString((Object[])obj):obj));
		
		// service 실행
		result = service.service(obj);
		
		// 결과 데이터 출력
		System.out.println("결과 데이터 : " + result);
		
		// 실행시간
		Long end = System.nanoTime();
		System.out.println("실행 시간 : " + (end - start));
		System.out.println("--------------------------------");
		
		return result;// 처리결과 또는 데이터 리턴
	}
}
