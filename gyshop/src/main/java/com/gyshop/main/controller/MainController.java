package com.gyshop.main.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyshop.main.vo.WeatherVO;
import com.gyshop.util.exe.Execute;
import com.gyshop.util.page.PageObject;
import com.gyshop.util.weather.WeatherXML;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

public class MainController {

	public String execute(HttpServletRequest request) {
		System.out.println("MainController.execute() -----");
		// 이동할 페이지 주소를 담을 변수 선언 & 초기화
		String jsp = null;
		
		// 결과담을 변수 선언 및 초기화
		Object result = null;
		
		// 메뉴를 담을 변수선언하고 uri경로를 담는다.
		String uri = request.getRequestURI();
		
		try {
			switch(uri) {
			case "/main/main.do":
				System.out.println("MAIN 처리 -----");
				// 일반게시판 리스트를 DB에서 가져옵니다.
				PageObject pageObject = new PageObject();
				
				// 한페이지데이터(5개) 를 가져오도록 세팅
				pageObject.setPerPageNum(5L);
				// 서비스실행
				result = Execute.execute(Init.get("/board/list.do"), pageObject);
				// BoardListService()가 실행되었습니다.
				request.setAttribute("boardList", result);
				
				// 공지사항 리스트를 DB에서 가져옵니다.
				// 위에 있는 pageObject를 그대로 사용합니다.
				// pageObject를 여기서 다시 사용하더라도 일반게시판에는 영향을
				// 주지 않습니다.
				// 한페이지에 5개를 사용할 것이어서 setPerPageNum도 그래도 둡니다.
				// 서비스실행
				result = Execute.execute(Init.get("/notice/list.do"), pageObject);
				// NoticeListService()가 실행되었습니다.
				request.setAttribute("noticeList", result);
				
				// 이미지(Gallery) 리스트를 DB에서 가져옵니다.
				// 3개의 데이터
				pageObject.setPerPageNum(3L);
				// 서비스실행
				result = Execute.execute(Init.get("/image/list.do"), pageObject);
				// ImageListService()가 실행됩니다.
				request.setAttribute("imageList", result);
				
				// 날씨정보
				String[] wData = new String[5];
				String err = WeatherXML.getWeatherXML(58, 127, wData);
				WeatherVO weatherVO = null;
				System.out.println("err: " + err);
				if (err == null) {
					weatherVO = new WeatherVO();
					
					weatherVO.setRegion("화전동");
					weatherVO.setDate(wData[0].substring(0,4) + "-"
							+ wData[0].substring(4, 6) + "-"
							+ wData[0].substring(6));
					// yyyy-MM-dd ==> 형태로 날짜 저장
					weatherVO.setTime(wData[1].substring(0, 2) + ":"
							+ wData[1].substring(2));
					weatherVO.setWeather(wData[2]);
					weatherVO.setTemperature(wData[3]);
					weatherVO.setHumidity(wData[4]);
					
					request.setAttribute("weatherVO", weatherVO);
				}
				
				jsp = "main/main";
				// "/WEB-INF/views/main/main.jsp"
				break;
				
			case "/main/movie.do":
				jsp = "rest/restService";
				String targetDt = request.getParameter("targetDt")==null?"20250101":
					request.getParameter("targetDt");
				String itemPerPage = request.getParameter("itemPerPage")==null?"10":
					request.getParameter("itemPerPage");
				String openStartDt = request.getParameter("openStartDt")==null?"2025":
					request.getParameter("openStartDt");

				String key = "8587a970816e9487ea6d5205da108798";

				KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

				//String[] movieTypeCdArr = {"드라마", "어드벤처",
//						"애니메이션", "액션", "공포(호러)", "다큐멘터리", "스릴러",
//						"멜로/로맨스", "SF", "기타", "공연", "범죄", "뮤지컬"};
				//String[] movieTypeCdArr = {"220101", "220199"};
				//service.getMovieList(isJson, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr)
				String movieListStr = service.getMovieList(true, null, itemPerPage, null, null, openStartDt, null, null, null, null, null);

				ObjectMapper mapper = new ObjectMapper();
				HashMap<String, Object> movieListMap = mapper.readValue(movieListStr, HashMap.class);

				request.setAttribute("movieListMap", movieListMap);

				break;
			
			default:
				request.setAttribute("uri", uri);
				jsp = "error/404";
				// "/WEB-INF/views/error/404.jsp"
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		// 이동할 페이지 리턴
		return jsp;
	}
}
