package com.study.openapi.mapper;

import com.study.openapi.dto.StockDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
    void insertStocks(List<StockDTO> stocks);
}
