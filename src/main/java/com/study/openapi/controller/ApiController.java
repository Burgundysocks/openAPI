package com.study.openapi.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("/api")
public class ApiController {
    final static String serviceKey = "9P3ZStYv6PIf0Wrl0Q1iUW4XbRNhDvmQza1aiD9AgGH1yjZb6H07V5p0iUN4ePSt1FUXthV0nJyg%2FTwtyOXXGA%3D%3D";

    @GetMapping(value = "get", produces = "application/json;charset=utf-8")
    public String getData() throws Exception {
        String url = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        url += "?serviceKey=" + serviceKey;
        url += "&numOfRows=10&pageNo=1";
        url += "&resultType=json";
        URL requestURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
    }
}
