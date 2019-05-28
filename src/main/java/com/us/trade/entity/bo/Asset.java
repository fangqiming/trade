package com.us.trade.entity.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Asset {

    /**
     * 账户信息
     */
    private String account;

    /**
     * 净清算值
     */
    private BigDecimal netLiquidation;

    /**
     * 可用金额
     */
    private BigDecimal availableFunds;

    /**
     * 持仓市值
     */
    private BigDecimal grossPositionValue;

    /**
     * 股票市值
     */
    private BigDecimal stockMarketValue;
}
