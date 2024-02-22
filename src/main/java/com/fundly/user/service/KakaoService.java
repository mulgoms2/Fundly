package com.fundly.user.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@PropertySource(value ="/WEB-INF/config/Oauth.properties")
public class KakaoService {

    @Value("${kakaoRestAPI_Key}")
    private String kakaoRestAPI_Key;

    @Value("${kakaoSecret}")
    private String kakaoSecret;

    @Value("${kakaoRedirect_uri}")
    private String kakaoRedirect_uri;

    private final static String kakaoauth_Uri = "https://kauth.kakao.com";
    private final static String kakaoApi_Uri = "https://kapi.kakao.com";

    public String getAccessToken(String code) throws IOException {

        String access_token = "";
        String refresh_token = "";

        // 인가코드로 토큰받기
        URL url = new URL(kakaoauth_Uri + "/oauth/token");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//        log.info("들어왔다.");
        try {
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true); // 데이터 기록 알려주기

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+kakaoRestAPI_Key);
            sb.append("&redirect_uri=" + kakaoRedirect_uri);
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            //urlconnection 응답코드
            int responseCode = urlConnection.getResponseCode();
//            log.info("\n\nresponseCode = " + responseCode + "\n\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("\n\nresult = " + result+ "\n\n");

            // json parsing
            JSONParser parser = new JSONParser();
            JSONObject elem = (JSONObject) parser.parse(result);

            access_token = elem.get("access_token").toString();
            refresh_token = elem.get("refresh_token").toString();

            log.info("\n\nrefresh_token = "+ refresh_token + "\n\n");
            log.info("\n\naccess_token = "+ access_token + "\n\n");

            br.close();
            bw.close();

        } catch (IOException e) {
//            e.printStackTrace();

        } catch (ParseException e) {
//            e.printStackTrace();
        }

        return access_token;
    }

    public Map<String, Object> getUserInfo(String access_token) throws IOException {

        Map<String, Object> result = new HashMap<>();
        try {
            URL url = new URL(kakaoApi_Uri + "/v2/user/me");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
            urlConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            int responseCode = urlConnection.getResponseCode();
            log.info("\n\nresponseCode = " + responseCode + "\n\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }

            log.info("\n\nres = " + res + "\n\n");

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(res);
            JSONObject kakao_account = (JSONObject) obj.get("kakao_account");
            JSONObject properties = (JSONObject) obj.get("properties");

            log.info("\n\n\n kakao_account = " + kakao_account +"\n");
            log.info("\n\n\n properties = " + properties+"\n");

            String id = obj.get("id").toString();
            String email = kakao_account.get("email").toString();
            String nickname = properties.get("nickname").toString();
            String name = kakao_account.get("name").toString();

            result.put("id", id);
            result.put("email", email);
            result.put("nickname", nickname);
            result.put("name", name);

            br.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> getservice_terms(String access_token) throws IOException {

        Map<String, Object> result = new HashMap<>();
        try {
            URL url = new URL(kakaoApi_Uri + "/v2/user/service_terms");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();
            log.info("\n\nresponseCode = " + responseCode + "\n\n");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }
            log.info("\n\nres = " + res + "\n\n");

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(res);
            log.error("obj == " +obj);
//            JSONObject service_terms = (JSONObject) obj.get("service_terms");


//            String jsonString = "{\"id\":3355810633,\"service_terms\":[{\"tag\":\"user_age_check\",\"required\":true,\"agreed\":true,\"revocable\":false,\"agreed_at\":\"2024-02-21T12:47:30Z\"},{\"tag\":\"site_term_agree_yn\",\"required\":true,\"agreed\":true,\"revocable\":false,\"agreed_at\":\"2024-02-21T12:47:30Z\"},{\"tag\":\"p_Info_agree_yn\",\"required\":true,\"agreed\":true,\"revocable\":false,\"agreed_at\":\"2024-02-21T12:47:30Z\"},{\"tag\":\"m_info_rcv_agree_yn\",\"required\":false,\"agreed\":true,\"revocable\":true,\"agreed_at\":\"2024-02-21T12:47:30Z\"}]}";

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(res, JsonObject.class);
            JsonArray serviceTermsArray = jsonObject.getAsJsonArray("service_terms");

            for (JsonElement element : serviceTermsArray) {
                JsonObject serviceTermObject = element.getAsJsonObject();
                String tag = serviceTermObject.get("tag").getAsString();
                boolean agreed = serviceTermObject.get("agreed").getAsBoolean();
                result.put(tag,agreed);
//                System.out.println("Tag: " + tag + ", Agreed: " + agreed);
            }


//            log.info("\n\n\n service_terms = " + service_terms);

//            String id = obj.get("id").toString();
//
//            result.put("id", id);

            br.close();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }



}
