package net.houssy.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    // static routing
    //@Bean
    //public RouteLocator routes(RouteLocatorBuilder builder) {
    //    return builder.routes()
    //            .route("r1",p->p.path("/customers/**").uri("http://localhost:8081"))
    //            .route("r2",p->p.path("/products/**").uri("http://localhost:8082"))
    //            .build();
    //}

    //dynamic routing
    @Bean
    public DiscoveryClientRouteDefinitionLocator getDiscoveryClientRouteDefinitionLocator(
            ReactiveDiscoveryClient reactiveDiscoveryClient,
            DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient, properties);
    }
}
