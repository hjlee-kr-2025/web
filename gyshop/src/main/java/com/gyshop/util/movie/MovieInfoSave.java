package com.gyshop.util.movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyshop.main.controller.Init;
import com.gyshop.movie.vo.MovieVO;
import com.gyshop.util.exe.Execute;

import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

public class MovieInfoSave {

	private final int intervalSeconds;
	private final String openStartDt;
	private Timer timer;
	
	public MovieInfoSave(int intervalSeconds, String openStartDt) {
		this.intervalSeconds = intervalSeconds;
		this.openStartDt = openStartDt;
	}
	
	public void start() {
		String itemPerPage = "100";
		String openStartDt =
			(this.openStartDt == null)?"2025":this.openStartDt;
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String key = "8587a970816e9487ea6d5205da108798";
				
				List<MovieVO> list = new ArrayList<MovieVO>();

				try {
					
					KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
					
					//String[] movieTypeCdArr = {"드라마", "어드벤처",
//						"애니메이션", "액션", "공포(호러)", "다큐멘터리", "스릴러",
//						"멜로/로맨스", "SF", "기타", "공연", "범죄", "뮤지컬"};
					//String[] movieTypeCdArr = {"220101", "220199"};
					//service.getMovieList(isJson, curPage, itemPerPage, movieNm, directorNm, openStartDt, openEndDt, prdtStartYear, prdtEndYear, repNationCd, movieTypeCdArr)
					String movieListStr = service.getMovieList(true, null, itemPerPage, null, null, openStartDt, null, null, null, null, null);
					
					ObjectMapper mapper = new ObjectMapper();
					HashMap<String, Object> movieListMap = mapper.readValue(movieListStr, HashMap.class);
					
					Map<String, Object> movieListResult = (Map<String, Object>) movieListMap.get("movieListResult");
					
					List<Map<String, Object>> movieList = (List<Map<String, Object>>) movieListResult.get("movieList");
					
					for (Map<String, Object> movie : movieList) {
						MovieVO vo = new MovieVO();
						vo.setMovieCd((String)movie.get("movieCd"));
						vo.setMovieNm((String)movie.get("movieNm"));
						vo.setOpenDt((String)movie.get("openDt"));
						vo.setTypeNm((String)movie.get("typeNm"));
						vo.setPrdtStatNm((String)movie.get("prdtStatNm"));
						vo.setRepNationNm((String)movie.get("repNationNm"));
						vo.setRepGenreNm((String)movie.get("repGenreNm"));
						List<Map<String, Object>> peopleList 
							= (List<Map<String, Object>>)movie.get("directors");
						
						//System.out.println(peopleList);
						//peopleNm
						if (peopleList.size() != 0) {
							vo.setPeopleNm((String)(peopleList.get(0).get("peopleNm")));
						}
						list.add(vo);

					}
					
					System.out.println(list);
					
					System.out.println(movieListMap);
					
					Execute.execute(Init.get("/movie/writeList"), list);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}, 0, intervalSeconds*1000);
	}
	
	public void stop() {
		if (timer != null) {
			timer.cancel();
		}
	}
	
}
