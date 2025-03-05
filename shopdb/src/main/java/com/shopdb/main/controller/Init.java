package com.shopdb.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.shopdb.board.service.Service;
import com.shopdb.main.dao.DAO;

public class Init {

	// Service를 생성해서 저장하는 객체 - <URI, Service>
	private static Map<String, Service> serviceMap
		= new HashMap<>();
	// dao를 생성해서 저장하는 객체 - <className, dao>
	private static Map<String, DAO> daoMap
		= new HashMap<>();
	
	// Init 클래스를 만든이유
	// 자바에서 클래스를 사용하기 위해서는 주소할당(생성)이 필요합니다.
	// new 생성자()를 사용해서 메모리를 할당합니다.
	// new 할때마다 메모리 공간이 할당되어서 서버프로그램에서는 한번만
	// 할당하고 공유해서 사용합니다.
	// 한번만 new 한값을 key값에 저장해 두고 사용합니다.
	
	// 한번만 초기화 하는 메서드 - static 메서드 사용
	static {
		
	}
	
}
