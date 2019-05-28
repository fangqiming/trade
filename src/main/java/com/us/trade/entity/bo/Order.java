package com.us.trade.entity.bo;

import com.tigerbrokers.stock.openapi.client.struct.enums.ActionType;
import com.tigerbrokers.stock.openapi.client.struct.enums.Currency;
import com.tigerbrokers.stock.openapi.client.struct.enums.OrderType;
import com.tigerbrokers.stock.openapi.client.struct.enums.SecType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    /**
     * 创建订单时获取的创建的id
     */
    private Long id;

    /**
     * 订单号，指修改，取消订单时返回的订单号
     */
    private Integer orderId;

    /**
     * 交易的股票
     */
    private String symbol;

    /**
     * 交易的数量
     */
    private Integer quantity;

    /**
     * 当 order_type 为LMT,STP,STP_LMT时该参数必需
     * 针对买入，指最高能够接受的价格
     * 针对卖出，指最低能够接受的价格
     */
    private Double limitPrice;

    /**
     * 订单类型，市价交易单，限价单，止损单等
     */
    private OrderType orderType;

    /**
     * 交易类型
     */
    private ActionType actionType;

    /**
     * 合约类型 (STK 股票 OPT 美股期权 WAR 港股窝轮 IOPT 港股牛熊证)
     */
    private SecType secType = SecType.STK;

    /**
     * 交易货币默认为USD
     */
    private Currency currency = Currency.USD;

    /**
     * 允许盘前盘后交易，默认不允许
     */
    private Boolean outsideRth = false;

}
