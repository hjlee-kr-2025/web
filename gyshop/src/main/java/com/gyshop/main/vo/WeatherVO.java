package com.gyshop.main.vo;

import lombok.Data;

@Data
public class WeatherVO {

	private String date;
	private String time;
	private String weather;
	private String temperature;
	private String humidity;
	private String region;
}
