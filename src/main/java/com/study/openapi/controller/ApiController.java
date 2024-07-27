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
    public String getData(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) throws Exception {
//        http://localhost:8080/api/get?page=1&size=50 이런식으로 유기적으로 페이징 할 수 있음
        int start = (page - 1) * size + 1;
        int end = page * size;

        String url = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
        url += "?serviceKey=" + serviceKey;
        url += "&numOfRows=" + size;
        url += "&pageNo=" + page;
        url += "&resultType=json";

        URL requestURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        conn.setRequestMethod("GET");

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
