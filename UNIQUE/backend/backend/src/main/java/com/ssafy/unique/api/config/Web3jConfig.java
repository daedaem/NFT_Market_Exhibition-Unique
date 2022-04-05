package com.ssafy.unique.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Bean
    Web3j web3j() {
        Web3j web3j = Web3j.build(new HttpService("http://20.196.209.2:8545"));
        return web3j;
    }
}
