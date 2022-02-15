package com.kutayyaman.microservices.apigateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get")
                .filters(f -> f
                        .addRequestHeader("MyHeader", "MyURI")
                        .addRequestHeader("Param", "MyValue"))
                .uri("http://httpbin.org:80");
        return builder.routes()
                .route(routeFunction)
                /*.route(p -> p.path("/menuservice/**")
                        .uri("lb://menuservice"))
                .route(p -> p.path("/restaurantservice/**")
                        .uri("lb://restaurantservice"))
                .route(p -> (Buildable<Route>) p.path("/restaurantservice-new/**")
                        .filters(f -> f.rewritePath(
                           "/restaurantservice-new/(?<segment>.*)",
                           "/restaurantservice/${segment}"
                        )))*/
                .build();
                /*
                yukardaki yorum satirini aktif edip kullanmak istersek application.properties dosyasında
                spring.cloud.gateway.discovery.locator.enabled=true
                spring.cloud.gateway.discovery.locator.lowerCaseServiceId=true
                 bu iki yeri yorum satirini almaliyiz
                 */
    }
}
