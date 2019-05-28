package com.us.trade.entity.bo;

import lombok.Data;

@Data
public class OrderInfo {
    /**
     * 全局唯一的订单号
     */
    private Long id;
    /**
     * 账户信息
     */
    private String account;
    /**
     * 交易方向
     */
    private String action;
    /**
     * 止损单辅助价格-跟踪额
     */
    private Double auxPrice;
    /**
     * 平均成交价格
     */
    private Double avgFillPrice;
    /**
     * 货币
     */
    private String currency;
    /**
     * 成交数量
     */
    private Integer filledQuantity;
    /**
     * 最后执行价
     */
    private Double lastFillPrice;
    /**
     * 现价单价格
     */
    private Double limitPrice;
    /**
     * 交易市场
     */
    private String market;
    /**
     * 每手股数
     */
    private Double multiplier;
    /**
     * 用户的自增单号，非全局唯一
     */
    private Long orderId;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 是否允许盘前、盘后
     */
    private Boolean outsideRth;
    /**
     * 实际盈亏
     */
    private Double realizedPnl;
    /**
     * 交易类型
     */
    private String secType;
    /**
     * 订单状态
     * Invalid	-2	非法状态
     * Initial	-1	订单初始状态
     * PendingCancel	3	待取消
     * Cancelled	4	已取消
     * Submitted	5	订单已经提交
     * Filled	6	完全成交
     * Inactive	7	已失效
     * PendingSubmit	8	待提交
     */
    private String status;
    /**
     * 止损单orderId
     */
    private Long stopLossOrderId;
    /**
     * 止损单价格
     */
    private Double stopLossPrice;
    /**
     * 止损单是否允许盘前、盘后交易
     */
    private Boolean stopLossRth;
    /**
     * 止盈单orderId
     */
    private Long profitTakerOrderId;
    /**
     * 止盈单价格
     */
    private Double profitTakerPrice;
    /**
     * 止盈单是否允许盘前、盘后交易
     */
    private Boolean profitTakerRth;
    /**
     * 股票代码
     */
    private String symbol;
    /**
     * DAY/GTC
     */
    private String timeInForce;
    /**
     * 总数
     */
    private Integer totalQuantity;
}
