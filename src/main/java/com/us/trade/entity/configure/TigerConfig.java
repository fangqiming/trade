package com.us.trade.entity.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tiger")
public class TigerConfig {

    private String serverUrl;
    private String account;
    private String standardAccount;
    private String paperAccount;
    private String tigerId;
    private String privateKey;
    private String publicKey;
}
