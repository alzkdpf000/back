package com.example.back.service.member;

import com.example.back.common.enumeration.Provider;
import com.example.back.dto.member.MemberDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;

@Service
@Slf4j
public class KakaoService {
    //    카카오 로그인
    public String getKakaoAccessToken(String code) {
        String accessToken = null;
        String requestURI = "https://kauth.kakao.com/oauth/token";

        URL url = null;
        try {
            url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            BufferedWriter bufferedWriter = null;

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            stringBuilder.append("grant_type=authorization_code");
            stringBuilder.append("&client_id=b89acf62a1fdb8335aaec795cdc5912a");
            stringBuilder.append("&redirect_uri=http://localhost:10000");
            stringBuilder.append("&code=").append(code);

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;

                    JsonElement jsonElement = JsonParser.parseString(result);
                    accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();

                    bufferedReader.close();
                }
            }
            return accessToken;

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<MemberDTO> getKakaoInfo(String token){
        String requestURI = "https://kapi.kakao.com/v2/user/me";
        MemberDTO memberDTO = null;

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer" + token);

            if (connection.getResponseCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                String result = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                JsonElement jsonElement = JsonParser.parseString(result);
                JsonElement kakaoAccount = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject();
                JsonElement profile = kakaoAccount.getAsJsonObject().get("profile");

                memberDTO = new MemberDTO();
                memberDTO.setKakaoEmail(kakaoAccount.getAsJsonObject().get("email").getAsString());
                memberDTO.setProvider(Provider.KAKAO);
                bufferedReader.close();
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(memberDTO);

    }

//        카카오 로그아웃
    public void logout (String token){
        String requestURI = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(requestURI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer" + token);

            if (connection.getResponseCode() == 200) {
                log.info("로그아웃 성공");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
