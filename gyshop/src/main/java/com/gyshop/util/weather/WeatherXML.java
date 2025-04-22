package com.gyshop.util.weather;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
				"https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"
				+ "?serviceKey=" + serviceKey	//서비스key(받은인증키)
				+ "&numOfRows=60"	// 한페이지결과수 (10개 카테고리 * 6시간) - 60
				+ "&pageNo=1"		// 페이지번호 (default:1)
				+ "&dataType=XML"	// 요청자료형식 (default:XML) - XML or JSON
				+ "&base_date=" + t.format(DateTimeFormatter.ofPattern("yyyyMMdd")) // 발표날짜
				+ "&base_time=" + t.format(DateTimeFormatter.ofPattern("HHmm"))		// 발표시각
				+ "&nx=" + x		// 예보지점 x값
				+ "&ny=" + y		// 예보지점 y값
				);
			
			System.out.println("url : " + url);
			con = (HttpURLConnection) url.openConnection();
			
			// org.w3c.dom.Document - HTML문서
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(con.getInputStream());
			// Document class는 HTML요소와 관련된 작업을 도와주는 역할
			
			boolean ok = false;	//<resultCode>00</resultCode> 여부확인저장변수
			
			Element e;
			NodeList ns = doc.getElementsByTagName("header");
			if (ns.getLength() > 0) {
				e = (Element) ns.item(0);
				if ("00".equals(e.getElementsByTagName("resultCode").item(0).getTextContent())) {
					ok = true;
				}
				else {
					err = e.getElementsByTagName("resultMsg").item(0).getTextContent();
				}
			}
			
			if (ok) {
				System.out.println("WeatherXML resultCode ok");
				
				String fd = null, ft = null; // 가장빠른 예보시각
				String pty = null;	//강수형태
				String sky = null;	//하늘상태
				String cat = null;	//category
				String val = null;	//fcstValue
				
				ns = doc.getElementsByTagName("item");
				for (int i = 0 ; i < ns.getLength() ; ++i) {
					e = (Element) ns.item(i);
					
					if (ft == null) {
						fd = e.getElementsByTagName("fcstDate").item(0).getTextContent();
						ft = e.getElementsByTagName("fcstTime").item(0).getTextContent();
					}
					else if (!fd.equals(e.getElementsByTagName("fcstDate").item(0).getTextContent())
							|| !ft.equals(e.getElementsByTagName("fcstTime").item(0).getTextContent())) {
						continue;
					}
					
					// cat : 자료구분코드, val : 예보값
					cat = e.getElementsByTagName("category").item(0).getTextContent();
					val = e.getElementsByTagName("fcstValue").item(0).getTextContent();
					
					if ("PTY".equals(cat)) pty = val;// 강수형태
					else if ("SKY".equals(cat)) sky = val;//하늘상태
					else if ("T1H".equals(cat)) v[3] = val;//기온
					else if ("REH".equals(cat)) v[4] = val;//습도
					
					v[0] = fd;//예보날짜
					v[1] = ft;//예보시각
					
					if ("0".equals(pty)) {
						// 강수형태가 없으면, 하늘상태로 판단
						if ("1".equals(sky)) v[2] = "맑음";
						else if ("3".equals(sky)) v[2] = "구름많음";
						else if ("4".equals(sky)) v[2] = "흐림";
					}
					else if ("1".equals(pty)) v[2] = "비";
					else if ("2".equals(pty)) v[2] = "비/눈";
					else if ("3".equals(pty)) v[2] = "눈";
					else if ("5".equals(pty)) v[2] = "빗방울";
					else if ("6".equals(pty)) v[2] = "빗방울/눈날림";
					else if ("7".equals(pty)) v[2] = "눈날림";
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			err = e.getMessage();
		} finally {
			// 연결닫기
			if (con != null) con.disconnect();
		}
		
		System.out.println("v[0] : " + v[0]);
		System.out.println("v[1] : " + v[1]);
		System.out.println("v[2] : " + v[2]);
		System.out.println("v[3] : " + v[3]);
		System.out.println("v[4] : " + v[4]);
		
		
		// 결과리턴
		return err;
	}
}
