package com.fundly.project.controller;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@Data
public class HolidayAPIExplorer { //해당 년도의 모든
    private static String serviceKey = "wk%2BAcUH2N7kZQGXC0U5AZ0c2ySsNNT2G6OcWbbuua6wRKq5DJTbpqDH9xKD%2BYMK5MSdG3HfdLrBJjLDDhXQd4g%3D%3D";
    public static Map<String, Object> getHolidays(String solYear) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(solYear, "UTF-8")); /*연*/
        //urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(Integer.parseInt(solMonth)<10?"0"+solMonth:solMonth, "UTF-8")); /*월*/
        //자릿수를 꼭 맞춰줘야 제대로된 응답을 받을 수 있음. 근데 연단위로 통째로 가져올거면 굳이 달을 입력해야할까.
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); //json으로 응답받기
        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String json = sb.toString();
        System.out.println(json);

        //stringified json을 map으로 변환하기
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> holidayMap = null;
        try{
            holidayMap = mapper.readValue(json, Map.class);
        }catch(JacksonException e){
            e.printStackTrace();
        }
        return holidayMap;

    }
}

