package com.study.openapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    private Long id;
    private LocalDate basDt;
    private String srtnCd;
    private String isinCd;
    private String itmsNm;
    private String mrktCtg;
    private BigDecimal clpr;
    private BigDecimal vs;
    private BigDecimal fltRt;
    private BigDecimal mkp;
    private BigDecimal hipr;
    private BigDecimal lopr;
    private Long trqu;
    private Long trPrc;
    private Long lstgStCnt;
    private Long mrktTotAmt;
}
