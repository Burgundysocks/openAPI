package com.study.openapi.service;

import com.study.openapi.dto.StockDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StockService {
    void saveStocks(List<StockDTO> stocks);
}
