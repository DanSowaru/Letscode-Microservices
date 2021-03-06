package com.letscode.apisales.config;


import com.letscode.apisales.handler.SaleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SaleHandler saleHandler) {
        return RouterFunctions.route().POST("/venda", saleHandler::createSale).build();
    }
}
