package com.gyshop.util.weather;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

public class WeatherXML {

	public static String getWeatherXML(int x, int y, String[] v) {
		// 결과 저장변수 (error메시지)
		String err = null;
		// 인증키
		String serviceKey = "VGFUlyZTKsjMvCI9wyuXrhLiaxQ%2F6zLulVaPyvsOUcXZaTodcDCvKOJZ6at27B2AHy%2FYGOWDKJpZC7hXQpUB2g%3D%3D";
		// 연결객체선언
		HttpURLConnection con = null;
		
		try {
			LocalDateTime t = LocalDateTime.now().minusMinutes(30);
			// 현재시각에서 30분전
			URL url = new URL(
				"https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			err = e.getMessage();
		} finally {
			// 연결닫기
			if (con != null) con.disconnect();
		}
		
		
		// 결과리턴
		return err;
	}
}
