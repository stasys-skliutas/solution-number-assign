package dev.stasys.numberassignservice.externalapi;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FeignClientConfig {
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(3000, 1000, 5);
    }
}
