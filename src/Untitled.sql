create database api;

use api;

CREATE TABLE stock_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    bas_dt DATE NOT NULL,
    srtn_cd VARCHAR(10) NOT NULL,
    isin_cd VARCHAR(20) NOT NULL,
    itms_nm VARCHAR(255) NOT NULL,
    mrkt_ctg VARCHAR(50) NOT NULL,
    clpr DECIMAL(18, 2) NOT NULL,
    vs DECIMAL(18, 2) NOT NULL,
    flt_rt DECIMAL(5, 2) NOT NULL,
    mkp DECIMAL(18, 2) NOT NULL,
    hipr DECIMAL(18, 2) NOT NULL,
    lopr DECIMAL(18, 2) NOT NULL,
    trqu BIGINT NOT NULL,
    tr_prc BIGINT NOT NULL,
    lstg_st_cnt BIGINT NOT NULL,
    mrkt_tot_amt BIGINT NOT NULL,
    UNIQUE KEY unique_stock (bas_dt, srtn_cd)
);
