package com.gyshop.util.exe;

import java.util.Arrays;

import com.gyshop.main.service.Service;

// 서비스 실행과 로그를 출력하는 클래스
public class Execute {

	public static Object execute(Service service, Object obj)
		throws Exception {
		// 처리결과를 저장하는 변수
		Object result = null;
		
		System.out.println("<<< ------------ 실행 로그 출력 -------------- >>>");
		System.out.println("-----------------------------------------------");
		
		// 시작시간저장 - nano seconds
		Long start = System.nanoTime();
		
		// 실행객체(Service) 정보를 출력
		System.out.println("실행 객체 이름 : " + service.getClass().getName());
		
		// 서비스로 넘어가는 데이터 출력
		System.out.println("서비스로 넘어가는 데이터 : " +
			((obj instanceof Object[])?Arrays.toString((Object[])obj):obj)
			);
		
		// 서비스 실행
		result = service.service(obj);
		
		// 실행후 결과 데이터 출력
		System.out.println("서비스 실행 후 결과 데이터 : " + result);
		
		// 종료시간
		Long end = System.nanoTime();
		// 실행시간
		System.out.println("실행 시간 : " + (end - start));
		
		// 결과를 리턴
		return result;
	}
}
