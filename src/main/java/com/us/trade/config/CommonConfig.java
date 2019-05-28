package com.us.trade.config;

import com.tigerbrokers.stock.openapi.client.https.client.TigerHttpClient;
import com.us.trade.entity.configure.TigerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Autowired
    private TigerConfig tigerConfig;

    @Bean
    public TigerHttpClient tigerHttpClient() {
        return new TigerHttpClient(tigerConfig.getServerUrl(), tigerConfig.getTigerId(),
                tigerConfig.getPrivateKey(), tigerConfig.getPublicKey());
    }
}
