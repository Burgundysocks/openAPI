package com.study.openapi.controller;

import com.study.openapi.dto.StockDTO;
import com.study.openapi.service.StockService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    final static String serviceKey = "9P3ZStYv6PIf0Wrl0Q1iUW4XbRNhDvmQza1aiD9AgGH1yjZb6H07V5p0iUN4ePSt1FUXthV0nJyg%2FTwtyOXXGA%3D%3D";

    @Autowired
    private StockService stockService;

    @GetMapping(value = "get", produces = "application/json;charset=utf-8")
    public String getData(
            @RequestParam(value = "size", defaultValue = "100") int size
    ) throws Exception {
        int totalDataCount = 10000; // 만 개의 데이터
        int totalPages = (int) Math.ceil((double) totalDataCount / size);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        for (int page = 1; page <= totalPages; page++) {
            List<StockDTO> stockList = new ArrayList<>();

            String url = "https://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo";
            url += "?serviceKey=" + serviceKey;
            url += "&numOfRows=" + size;
            url += "&pageNo=" + page;
            url += "&resultType=json";

            URL requestURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Error: Failed to fetch data for page " + page + ". HTTP response code: " + responseCode);
                continue;
            }

            InputStream is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonObject = new JSONObject(result.toString());
            JSONObject response = jsonObject.getJSONObject("response");
            JSONObject body = response.getJSONObject("body");
            JSONArray items = body.getJSONObject("items").getJSONArray("item");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);

                StockDTO stock = new StockDTO();
                stock.setBasDt(LocalDate.parse(item.getString("basDt"), formatter));
                stock.setSrtnCd(item.getString("srtnCd"));
                stock.setIsinCd(item.getString("isinCd"));
                stock.setItmsNm(item.getString("itmsNm"));
                stock.setMrktCtg(item.getString("mrktCtg"));
                stock.setClpr(new BigDecimal(item.getString("clpr")));
                stock.setVs(new BigDecimal(item.getString("vs")));
                stock.setFltRt(new BigDecimal(item.getString("fltRt")));
                stock.setMkp(new BigDecimal(item.getString("mkp")));
                stock.setHipr(new BigDecimal(item.getString("hipr")));
                stock.setLopr(new BigDecimal(item.getString("lopr")));
                stock.setTrqu(item.getLong("trqu"));
                stock.setTrPrc(item.getLong("trPrc"));
                stock.setLstgStCnt(item.getLong("lstgStCnt"));
                stock.setMrktTotAmt(item.getLong("mrktTotAmt"));

                stockList.add(stock);
            }

            // Save to database
            stockService.saveStocks(stockList);

            // 로그 추가
            System.out.println("Page " + page + ": " + stockList.size() + " records retrieved and saved.");
        }

        return "Data retrieved and saved successfully.";
    }
}
