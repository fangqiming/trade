package com.us.trade.serivce;

import com.tigerbrokers.stock.openapi.client.https.client.TigerHttpClient;
import com.tigerbrokers.stock.openapi.client.https.domain.quote.item.KlinePoint;
import com.tigerbrokers.stock.openapi.client.https.domain.quote.item.MarketItem;
import com.tigerbrokers.stock.openapi.client.https.domain.quote.item.RealTimeQuoteItem;
import com.tigerbrokers.stock.openapi.client.https.domain.quote.item.TimelineItem;
import com.tigerbrokers.stock.openapi.client.https.request.quote.QuoteKlineRequest;
import com.tigerbrokers.stock.openapi.client.https.request.quote.QuoteMarketRequest;
import com.tigerbrokers.stock.openapi.client.https.request.quote.QuoteRealTimeQuoteRequest;
import com.tigerbrokers.stock.openapi.client.https.request.quote.QuoteTimelineRequest;
import com.tigerbrokers.stock.openapi.client.https.response.quote.QuoteKlineResponse;
import com.tigerbrokers.stock.openapi.client.https.response.quote.QuoteMarketResponse;
import com.tigerbrokers.stock.openapi.client.https.response.quote.QuoteRealTimeQuoteResponse;
import com.tigerbrokers.stock.openapi.client.https.response.quote.QuoteTimelineResponse;
import com.tigerbrokers.stock.openapi.client.struct.enums.KType;
import com.tigerbrokers.stock.openapi.client.struct.enums.Market;
import com.tigerbrokers.stock.openapi.client.struct.enums.RightOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MarketService {

    @Autowired
    private TigerHttpClient client;

    /**
     * 获取市场状态
     *
     * @param market
     * @return
     */
    public MarketItem getMarketInfo(Market market) {
        QuoteMarketResponse response = client.execute(QuoteMarketRequest.newRequest(market));
        if (response.isSuccess()) {
            return response.getMarketItems().get(0);
        }
        log.warn("市场状态获取失败:{}", response.getMessage());
        return null;
    }

    /**
     * 当前时间美股是否开盘
     * Not Yet Open 未开盘
     * Trading 交易中
     * Closed Independence Day 休市
     *
     * @param market
     * @return 交易中则返回True
     */
    public Boolean isOpen(Market market) {
        MarketItem marketInfo = getMarketInfo(market);
        return "Trading".equals(marketInfo.getMarketStatus());
    }

    /**
     * 根据股票代码获取成本线数据
     *
     * @param symbol 股票代码
     * @param start  开始时间，毫秒（美国时间）为null，以当天的开盘时间为开始时间
     * @return
     */
    public TimelineItem getTimeline(String symbol, Long start) {
        QuoteTimelineResponse response = client.execute(QuoteTimelineRequest.newRequest(Arrays.asList(symbol), start));
        if (response.isSuccess()) {
            return response.getTimelineItems().get(0);
        }
        log.warn("{} 成本线数据获取失败:{}", symbol, response.getMessage());
        return null;
    }

    /**
     * 获取指定股票的实时数据，包括当前价，盘口等
     *
     * @param symbol 股票代码
     * @return
     */
    public RealTimeQuoteItem getRealTime(String symbol) {
        QuoteRealTimeQuoteResponse response = client.execute(QuoteRealTimeQuoteRequest.newRequest(Arrays.asList(symbol)));
        if (response.isSuccess()) {
            return response.getRealTimeQuoteItems().get(0);
        }
        log.warn("{} 实时数据获取失败:{}", symbol, response.getMessage());
        return null;
    }

    /**
     * 获取指定股票的周期K线数据
     *
     * @param symbol 股票代码
     * @param period 周期
     * @return
     */
    public List<KlinePoint> getKLine(String symbol, KType period) {
        return getKLine(symbol, period, -1L, -1L, 1000);
    }

    /**
     * 获取指定股票的周期K线数据
     *
     * @param symbol 股票代码
     * @param period 周期
     * @param start  开始时间（美国时间）毫秒
     * @param end    结束时间（美国时间）毫秒
     * @param limit  最多返回的数据条数
     * @return
     */
    public List<KlinePoint> getKLine(String symbol, KType period, Long start, Long end, Integer limit) {
        QuoteKlineResponse response = client.execute(QuoteKlineRequest
                .newRequest(Arrays.asList(symbol), period, start, end)
                .withLimit(limit)
                .withRight(RightOption.br));
        if (response.isSuccess()) {
            return response.getKlineItems().get(0).getItems();
        }
        log.warn("{} K线数据获取失败，周期为{}，错误信息为:{}", symbol, period.getValue(), response.getMessage());
        return null;
    }

}
