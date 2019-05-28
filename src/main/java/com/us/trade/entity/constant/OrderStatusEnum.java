package com.us.trade.entity.constant;

import com.tigerbrokers.stock.openapi.client.constant.ApiServiceType;
import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    ALL(ApiServiceType.ORDERS, "全部订单"),
    FINISH(ApiServiceType.FILLED_ORDERS, "已完成订单"),
    ACTIVE(ApiServiceType.ACTIVE_ORDERS, "待完成订单"),
    INACTIVE(ApiServiceType.INACTIVE_ORDERS, "已撤销订单");


    private String status;
    private String msg;

    OrderStatusEnum(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
