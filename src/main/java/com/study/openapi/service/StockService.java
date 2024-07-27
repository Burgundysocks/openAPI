package com.study.openapi.service;

import com.study.openapi.dto.StockDTO;

import java.util.List;

public interface StockService {
    void saveStocks(List<StockDTO> stocks);
}
