package com.study.openapi.service;

import com.study.openapi.dto.StockDTO;
import com.study.openapi.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void saveStocks(List<StockDTO> stocks) {
        stockMapper.insertStocks(stocks);
    }
}
