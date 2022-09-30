package com.lguplus.fleta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Swagger2Config {

    @Bean
    public OpenAPI contentsAPI() {
        return new OpenAPI()
            .info(new Info().title("Contents Service API")
                .description("Contents 서비스가 제공하는 API")
                .version("v1.0.0")
                .description("Contents Service API"));
    }
    //
    //    @Bean
    //    public GroupedOpenApi groupFullOpenApi() {
    //        String[] packagesToScan = {"com.lguplus.fleta"};
    //        return GroupedOpenApi.builder()
    //            .packagesToScan(packagesToScan)
    //            .group("")
    //            .build();
    //    }
}
