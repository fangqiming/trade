package com.us.trade.serivce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tigerbrokers.stock.openapi.client.constant.ApiServiceType;
import com.tigerbrokers.stock.openapi.client.https.client.TigerHttpClient;
import com.tigerbrokers.stock.openapi.client.https.request.TigerHttpRequest;
import com.tigerbrokers.stock.openapi.client.https.response.TigerHttpResponse;
import com.tigerbrokers.stock.openapi.client.struct.enums.SecType;
import com.tigerbrokers.stock.openapi.client.util.builder.AccountParamBuilder;
import com.tigerbrokers.stock.openapi.client.util.builder.TradeParamBuilder;
import com.us.trade.entity.bo.Order;
import com.us.trade.entity.bo.OrderInfo;
import com.us.trade.entity.configure.TigerConfig;
import com.us.trade.entity.constant.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单服务
 */
@Service
public class OrderService {

    @Autowired
    private TigerConfig tigerConfig;

    @Autowired
    private TigerHttpClient client;

    /**
     * 创建订单号，每次下达交易指令都需要创建订单号
     *
     * @return
     */
    public Integer getOrderId() {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.ORDER_NO);

        String bizContent = TradeParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .buildJson();

        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            return JSONObject.parseObject(response.getData()).getInteger("orderId");
        }
        return null;
    }

    /**
     * 创建订单
     * todo 需要消息通知与日志记录
     *
     * @param order 订单信息
     * @return 订单号
     */
    public Long createOrder(Order order) {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.PLACE_ORDER);
        String bizContent = createOrderParameter(order);
        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            return JSONObject.parseObject(response.getData()).getLong("orderId");
        }
        return null;
    }

    /**
     * 取消订单
     *
     * @param orderId 需要取消的订单号
     * @return 取消订单的订单号，可用来查询订单状态
     */
    public Long cancleOrder(Long orderId) {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.CANCEL_ORDER);
        String bizContent = TradeParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .id(orderId)
                .buildJson();
        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            return JSONObject.parseObject(response.getData()).getLong("id");
        }
        return null;
    }

    /**
     * 修改订单
     *
     * @param order 订单信息
     */
    public Long updateOrder(Order order) {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.MODIFY_ORDER);
        String bizContent = createOrderParameter(order);
        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            return JSONObject.parseObject(response.getData()).getLong("id");
        }
        return null;
    }

    /**
     * 获取指定的订单
     *
     * @param orderId 订单号
     */
    public OrderInfo getOrderById(Long orderId) {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.ORDERS);
        String bizContent = AccountParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .id(orderId)
                .buildJson();

        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            return JSON.parseObject(response.getData(), OrderInfo.class);
        }
        return null;
    }

    /**
     * 获取指定状态的订单
     *
     * @return
     */
    public List<OrderInfo> findOrderByStatus(OrderStatusEnum status) {
        TigerHttpRequest request = new TigerHttpRequest(status.getStatus());

        String bizContent = AccountParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .secType(SecType.STK)
                .buildJson();

        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            JSONArray items = JSON.parseObject(response.getData()).getJSONArray("items");
            return items.toJavaList(OrderInfo.class);
        }
        return null;
    }

    /**
     * 根据订单信息创建下单用参数
     *
     * @param order
     * @return
     */
    private String createOrderParameter(Order order) {
        return TradeParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .id(order.getId())
                .orderId(order.getOrderId())
                .symbol(order.getSymbol())
                .totalQuantity(order.getQuantity())
                .limitPrice(order.getLimitPrice())
                .orderType(order.getOrderType())
                .action(order.getActionType())
                .secType(order.getSecType())
                .currency(order.getCurrency())
                .outsideRth(order.getOutsideRth()).buildJson();
    }
}
