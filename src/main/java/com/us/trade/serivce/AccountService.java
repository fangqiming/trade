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
import com.us.trade.entity.bo.Asset;
import com.us.trade.entity.bo.HoldStock;
import com.us.trade.entity.configure.TigerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AccountService {

    @Autowired
    private TigerConfig tigerConfig;

    @Autowired
    private TigerHttpClient client;

    /**
     * 获取当前持仓
     *
     * @return
     */
    public List<HoldStock> getHoldStock() {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.POSITIONS);
        String bizContent = AccountParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .secType(SecType.STK)
                .buildJson();

        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            JSONArray responseArray = JSON.parseObject(response.getData()).getJSONArray("items");
            return responseArray.toJavaList(HoldStock.class);
        }
        return null;
    }

    /**
     * 获取账户资产信息
     */
    public Asset getAccount() {
        TigerHttpRequest request = new TigerHttpRequest(ApiServiceType.ASSETS);
        String bizContent = AccountParamBuilder.instance()
                .account(tigerConfig.getAccount())
                .buildJson();
        request.setBizContent(bizContent);
        TigerHttpResponse response = client.execute(request);
        if (response.isSuccess()) {
            JSONObject data = JSON.parseObject(response.getData());
            return JSON.parseObject(data.getJSONArray("items").getJSONObject(0).toJSONString(), Asset.class);
        }
        return null;
    }




}
