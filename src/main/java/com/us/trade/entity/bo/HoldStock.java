package com.us.trade.entity.bo;

import lombok.Data;

@Data
public class HoldStock {
    /**
     * 账户
     */
    private String account;
    /**
     * 平均成本
     */
    private Double averageCost;
    /**
     * 合约ID
     */
    private Long contractId;
    /**
     * 货币单位 USD 美元
     */
    private String currency;
    /**
     * 最新价格
     */
    private Double latestPrice;
    /**
     * 股票代码(美股用于消除歧义，同symbol； 港股用于识别窝轮和牛熊证)
     */
    private String localSymbol;
    /**
     * 市值
     */
    private Double marketValue;
    /**
     * 每手数量
     */
    private Double multiplier;
    /**
     * 持仓数量
     */
    private Integer position;
    /**
     * 收盘价
     */
    private Double preClose;
    /**
     * 平仓盈亏
     */
    private Double realizedPnl;
    /**
     * 交易类型
     */
    private String secType;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 股票ID
     */
    private String stockId;
    /**
     * 股票代码
     */
    private String symbol;
    /**
     * 浮动盈亏
     */
    private Double unrealizedPnl;
}
